package sphinx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.sap.conn.jco.JCoException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;
import guiSAP.ButtonPickOutListener;
import guiSAP.SAPLEXAGUI;
import guiSAP.ViewOrderDetails;
import javaConnector.Order;
import javaConnector.SAPConnection;

public class Sphinx {

	Text sphinxlabel;
	Text orderid;
	Table ordertable;
	Button pickoutButton;

	private Collection<Order> list = new ArrayList<>();
	private String resultString = "";
	private String selectedOrderID = "";
	private boolean selectOrderNow = false;
	private boolean listenbool = false;

	// Necessary
	private LiveSpeechRecognizer recognizer;

	// Logger
	private Logger logger = Logger.getLogger(getClass().getName());

	/**
	 * This String contains the Result that is coming back from SpeechRecognizer
	 */
	private String speechRecognitionResult;
	// public String number = "";
	public boolean recordNumber = false;

	// -----------------Lock Variables-----------------------------

	/**
	 * This variable is used to ignore the results of speech recognition cause
	 * actually it can't be stopped...
	 * 
	 * <br>
	 * Check this link for more information: <a href=
	 * "https://sourceforge.net/p/cmusphinx/discussion/sphinx4/thread/3875fc39/">https://sourceforge.net/p/cmusphinx/discussion/sphinx4/thread/3875fc39/</a>
	 */
	private boolean ignoreSpeechRecognitionResults = false;

	/**
	 * Checks if the speech recognise is already running
	 */
	private boolean speechRecognizerThreadRunning = false;

	/**
	 * Checks if the resources Thread is already running
	 */
	private boolean resourcesThreadRunning;

	// ---

	/**
	 * This executor service is used in order the playerState events to be executed
	 * in an order
	 */
	private ExecutorService eventsExecutorService = Executors.newFixedThreadPool(2);

	// ------------------------------------------------------------------------------------

	/**
	 * Constructor
	 */

	public Sphinx(Text t, Text t2, Table tab) {
		
		this.sphinxlabel = t;
		this.orderid = t2;
		this.ordertable = tab;
		sphinxlabel.setText("Say \"Hana\" to enter an order id");

		// Loading Message
		logger.log(Level.INFO, "Loading Speech Recognizer...\n");

		// Configuration
		Configuration configuration = new Configuration();

		// Load model from the jar
		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");

		// ====================================================================================
		// =====================READ
		// THIS!!!===============================================
		// Uncomment this line of code if you want the recognizer to recognize every
		// word of the language
		// you are using , here it is English for example
		// ====================================================================================
		// configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

		// ====================================================================================
		// =====================READ
		// THIS!!!===============================================
		// If you don't want to use a grammar file comment below 3 lines and uncomment
		// the above line for language model
		// ====================================================================================

		// Grammar
		configuration.setGrammarPath("resource:/grammars");
		configuration.setGrammarName("grammar");
		configuration.setUseGrammar(true);

		try {
			recognizer = new LiveSpeechRecognizer(configuration);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		}

		// Start recognition process pruning previously cached data.
		// recognizer.startRecognition(true);

		// Check if needed resources are available
		startResourcesThread();
		// Start speech recognition thread
		startSpeechRecognition();
	}

	public Sphinx() {

	}
	// -----------------------------------------------------------------------------------------------

	/**
	 * Starts the Speech Recognition Thread
	 */
	public synchronized void startSpeechRecognition() {

		// Check lock
		if (speechRecognizerThreadRunning)
			logger.log(Level.INFO, "Speech Recognition Thread already running...\n");
		else
			// Submit to ExecutorService
			eventsExecutorService.submit(() -> {

				// locks
				speechRecognizerThreadRunning = true;
				ignoreSpeechRecognitionResults = false;

				// Start Recognition
				recognizer.startRecognition(true);

				// Information
				logger.log(Level.INFO, "You can start to speak...\n");

				try {
					while (speechRecognizerThreadRunning) {

						/*
						 * This method will return when the end of speech is reached. Note that the end
						 * pointer will determine the end of speech.
						 */
						SpeechResult speechResult = recognizer.getResult();
						// Check if we ignore the speech recognition results
						if (!ignoreSpeechRecognitionResults) {

							// Check the result
							if (speechResult == null)
								logger.log(Level.INFO, "I can't understand what you said.\n");
							else {

								// Get the hypothesis
								speechRecognitionResult = speechResult.getHypothesis();

								// You said?
								System.out.println("You said: [" + speechRecognitionResult + "]\n");

								// Call the appropriate method
								makeDecision(speechRecognitionResult, speechResult.getWords());

								if (listenbool || selectOrderNow) {

									Display.getDefault().syncExec(new Runnable() {
										public void run() {
											
											if (selectOrderNow == false) {
												sphinxlabel.setText("SAP is listening ...");	
											}
											else {
												sphinxlabel.setText("Say \"Select\" to choose your order!");
											}
											orderid.setText("*" + resultString + "*");
											try {
												getProposals(resultString);
											} catch (JCoException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}

										}

									}

									);
								}

							}
						} else
							logger.log(Level.INFO, "Ingoring Speech Recognition Results...");

					}
				} catch (Exception ex) {
					logger.log(Level.WARNING, null, ex);
					speechRecognizerThreadRunning = false;
				}

				logger.log(Level.INFO, "SpeechThread has exited...");

			});
	}

	/**
	 * Stops ignoring the results of SpeechRecognition
	 */
	public synchronized void stopIgnoreSpeechRecognitionResults() {

		// Stop ignoring speech recognition results
		ignoreSpeechRecognitionResults = false;
	}

	/**
	 * Ignores the results of SpeechRecognition
	 */
	public synchronized void ignoreSpeechRecognitionResults() {

		// Instead of stopping the speech recognition we are ignoring it's results
		ignoreSpeechRecognitionResults = true;

	}

	// -----------------------------------------------------------------------------------------------

	/**
	 * Starting a Thread that checks if the resources needed to the
	 * SpeechRecognition library are available
	 */
	public void startResourcesThread() {

		// Check lock
		if (resourcesThreadRunning)
			logger.log(Level.INFO, "Resources Thread already running...\n");
		else
			// Submit to ExecutorService
			eventsExecutorService.submit(() -> {
				try {

					// Lock
					resourcesThreadRunning = true;

					// Detect if the microphone is available
					while (true) {

						// Is the Microphone Available
						if (!AudioSystem.isLineSupported(Port.Info.MICROPHONE))
							logger.log(Level.INFO, "Microphone is not available.\n");

						// Sleep some period
						Thread.sleep(350);
					}

				} catch (InterruptedException ex) {
					logger.log(Level.WARNING, null, ex);
					resourcesThreadRunning = false;
				}
			});
	}

	/**
	 * Takes a decision based on the given result
	 * 
	 * @param speechWords
	 */
	public void makeDecision(String speech, List<WordResult> speechWords) {

		System.out.println("Making Decision for: [" + speech + "]");

		switch (speech) {
		case "zero":
			if (listenbool) {
				resultString += "0";
			}
			else if (selectOrderNow) {
				selectedOrderID += "0";
			}
			break;
		case "hana": {
			resultString = "";
			listenbool = true;
			resultString = "";
			break;
		}

		case "one":
			if (listenbool) {
				resultString += "1";
			}
			else if (selectOrderNow) {
				selectedOrderID += "1";
			}
			break;
		case "two":
			if (listenbool) {
				resultString += "2";
			}
			else if (selectOrderNow) {
				selectedOrderID += "2";
			}
			break;
		case "three":
			if (listenbool) {
				resultString += "3";
			}
			else if (selectOrderNow) {
				selectedOrderID += "3";
			}
			break;
		case "four":
			if (listenbool) {
				resultString += "4";
			}
			else if (selectOrderNow) {
				selectedOrderID += "4";
			}
			break;
		case "five":
			if (listenbool) {
				resultString += "5";
			}
			else if (selectOrderNow) {
				selectedOrderID += "5";
			}
			break;
		case "six":
			if (listenbool) {
				resultString += "6";
			}
			else if (selectOrderNow) {
				selectedOrderID += "6";
			}
			break;
		case "seven":
			if (listenbool) {
				resultString += "7";
			}
			else if (selectOrderNow) {
				selectedOrderID += "7";
			}
			break;
		case "eight":
			if (listenbool) {
				resultString += "8";
			}
			else if (selectOrderNow) {
				selectedOrderID += "8";
			}
			break;
		case "nine":
			if (listenbool) {
				resultString += "9";
			}
			else if (selectOrderNow) {
				selectedOrderID += "9";
			}
			break;
		case "okay": {
			listenbool = false;
			if (selectOrderNow) {
				selectOrderNow = false;
				openPickOutMonitor();
			}
			else if (list.size() > 0) {
				selectOrderNow= true;
			}
			System.out.println(resultString);
		}
			break;
		case "select": {
			listenbool = false;
			selectOrderNow = true;
			selectedOrderID = "";
		}
			break;

		default:
			System.out.println("DEFAULT CASE");
		}

		System.out.println("RESULT STRING : " + resultString);

	}

	private void openPickOutMonitor() {
		// Implementation for opening the ViewOrderDetails
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ViewOrderDetails vod = new ViewOrderDetails(ordertable.getDisplay());
				System.out.println("Selected order id : " +selectedOrderID);
				vod.open((Order) list.toArray()[Integer.parseInt(selectedOrderID)]  );
			}}); 
			
		
		
	}

	public String getNumber() {
		return resultString;
	}

	public boolean getRecordNumber() {
		if (listenbool == false) {
			return true;
		} else {
			return true;
		}
	}

	public boolean getIgnoreSpeechRecognitionResults() {
		return ignoreSpeechRecognitionResults;
	}

	public boolean getSpeechRecognizerThreadRunning() {
		return speechRecognizerThreadRunning;
	}

	public void getProposals(String prfx) throws JCoException {
		SAPConnection sapConnect = new SAPConnection();
		// Sends spelled prefix to SAP Function Module
		list.clear();
		list = sapConnect.getProposalList(prfx);
		writeTableData(list);

	}

	public void writeTableData(Collection<Order> list) {
		int idx = 0;
		if (list.size() < 50) {
			ordertable.setItemCount(0);
			ordertable.clearAll();

			for (Order o : list) {

				TableItem item = new TableItem(ordertable, SWT.NULL);
				item.setText("Item " + idx);
				item.setText(0, "" + idx);
				item.setText(1, o.getOrderID());
				item.setText(2, o.getDistributorID());
				item.setText(3, o.getOrderDate().toString());

				item.setFont(new Font(ordertable.getDisplay(), "Calibri", 18, SWT.NONE));
				System.out.println("TABLE ENTRY " + o);

				idx++;

			}
		} else if (list.size() == 0) {
			ordertable.setItemCount(0);
			ordertable.clearAll();
			TableItem item = new TableItem(ordertable, SWT.NULL);
			item.setText("Warning ");

			item.setText(1, "No entries found.");

			item.setFont(new Font(ordertable.getDisplay(), "Calibri", 18, SWT.NONE));
		}

		else {
			ordertable.setItemCount(0);
			ordertable.clearAll();
			TableItem item = new TableItem(ordertable, SWT.NULL);
			item.setText("Warning ");

			item.setText(1, "Too many entries. Please keep specifying.");

			item.setFont(new Font(ordertable.getDisplay(), "Calibri", 18, SWT.NONE));

		}

	}

	/**
	 * Main Method
	 * 
	 * @param args
	 */

}
