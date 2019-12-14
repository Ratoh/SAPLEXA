package guiSAP;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import sphinx.Sphinx;

public class SAPLEXAGUI {

	Display display;
	Shell shell;

	Images img = new Images();

	Group overview, transactionInfoGroup, statusSaplexa, distributor;
	Label labelReceiptNumber, distributorNamePlate, distributorIDPlate, labelIssuedDate, labelSelectedRows, indicatorSymbol;
	Text receiptNumber, receiptNumberFull, distributorName, distributorID, issuedDate, txt, txt2, indicatorSymbolText;

	Button showSelectedRows, backDeliveryButton, callVorgesetzten, pickOut;
	
	Menu menuBar;
	MenuItem menuItem;
	Menu menuSAPlexa;
	MenuItem closeSAPlexa, muteSAPlexa, unmuteSAPlexa;

	Table table;

	Sphinx spx = new Sphinx();
	String number;
	boolean recordNumber, fetchData = false;

	public SAPLEXAGUI(String nothing) {

	}

	public SAPLEXAGUI() {

		createDisplay();
		createShell();
		createMenu();
		createGroup();
		createTable();
		createButtons();

		// fetchData = true;

		shell.pack();
	}

	public void createDisplay() {
		display = new Display();
	}

	public void createShell() {
		shell = new Shell(display);
		shell.setLayout(new GridLayout(1, true));
		shell.setImage(img.SHELLICON(display));
		shell.setText("SAPlexa");
		
		

//		shell.addListener(SWT.Close, new Listener() {
//			public void handleEvent(Event event) {
//				int style = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
//	            MessageBox messageBox = new MessageBox(shell, style);
//	            messageBox.setText("Information");
//	            messageBox.setMessage("Close the shell?");
//	            event.doit = messageBox.open() == SWT.YES;
//	        }
//	    });
	}
	
	public void createMenu() {
		
		menuBar = new Menu(shell, SWT.BAR);
		
		shell.setMenuBar(menuBar);
		menuItem = new MenuItem(menuBar, SWT.CASCADE);
		menuItem.setText("SAPlexa Settings");
		
		menuSAPlexa = new Menu(shell, SWT.DROP_DOWN);
		menuItem.setMenu(menuSAPlexa);

		
		
		muteSAPlexa = new MenuItem(menuSAPlexa, SWT.PUSH);
		muteSAPlexa.setText("Turn SAPlexa to deaf");
		unmuteSAPlexa = new MenuItem(menuSAPlexa, SWT.PUSH);
		unmuteSAPlexa.setText("Turn SAPlexa to listening");
		closeSAPlexa = new MenuItem(menuSAPlexa, SWT.PUSH);
		closeSAPlexa.setText("Close SAPlexa");
		
		
	}

	public void createGroup() {
		overview = new Group(shell, SWT.SHADOW_ETCHED_IN);
		overview.setLayout(new GridLayout(2, true));
		overview.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		overview.setText("Ordered list");
		overview.setFont(new Font(display, "Calibri", 20, SWT.BOLD));

		transactionInfoGroup = new Group(overview, SWT.SHADOW_ETCHED_IN);
		transactionInfoGroup.setLayout(new GridLayout(2, true));
		transactionInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, false, false, 1, 1));

		labelReceiptNumber = new Label(transactionInfoGroup, SWT.NONE);
		labelReceiptNumber.setText("Looking for OID: ");
		labelReceiptNumber.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		labelReceiptNumber.setFont(new Font(display, "Calibri", 22, SWT.NONE));

		txt2 = new Text(transactionInfoGroup, SWT.SINGLE);
		txt2.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		txt2.setText("----------");
		txt2.setFont(new Font(display, "Calibri", 22, SWT.NONE));
		txt2.setEditable(false);

		txt = new Text(transactionInfoGroup, SWT.SINGLE);
		txt.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		// txt.setText("SPHINX Voice Control is booting... Please wait");
		txt.setFont(new Font(display, "Calibri", 10, SWT.NONE));
		txt.setEditable(false);

		statusSaplexa = new Group(overview, SWT.SHADOW_ETCHED_IN);
		statusSaplexa.setLayout(new GridLayout(2, true));
		statusSaplexa.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 2));
				

		
		indicatorSymbolText = new Text(statusSaplexa, SWT.SINGLE);
		indicatorSymbolText.setEditable(false);
		indicatorSymbolText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
		indicatorSymbolText.setText("Say \"HANA\" for initiating the voice command.");
		indicatorSymbolText.setFont(new Font(display, "Calibri", 16, SWT.NONE));
		indicatorSymbolText.setBackground(new Color(shell.getDisplay(), 0, 200, 0));
	}

	public void createTable() {
		table = new Table(overview, SWT.SINGLE | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		String[] columns = { "Voice ID", "Bestellnummer", "Lieferant", "Belegdatum" };

		for (int loopIndex = 0; loopIndex < columns.length; loopIndex++) {

			TableColumn column = new TableColumn(table, SWT.NULL);
			column.setText(columns[loopIndex]);
			column.setMoveable(true);
			column.setWidth((shell.getSize().x - 50) / columns.length);
			column.setResizable(true);

		}
		
		table.setItemCount(10);
	
		

		spx = new Sphinx(txt, txt2, indicatorSymbolText, table );
		// Filling the table with data

	}

	public void createButtons() {

		pickOut = new Button(shell, SWT.PUSH);
		pickOut.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		pickOut.setText("Pick out");
		pickOut.setFont(new Font(display, "Calibri", 20, SWT.BOLD));
		pickOut.addSelectionListener(new ButtonPickOutListener(display));

	}
	


//	public void update() {
//		
//		while (fetchData) {
//			
//			number = spx.getNumber();
//			recordNumber = spx.getRecordNumber();
//		}
//	}

	

	public void open() {
		shell.open();
		shell.setFullScreen(true);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		} // end while
	} // end open
}
