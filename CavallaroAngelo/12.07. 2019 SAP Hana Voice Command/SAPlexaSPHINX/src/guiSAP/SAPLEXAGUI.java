package guiSAP;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import sphinx.Sphinx;

public class SAPLEXAGUI {

	Display display;
	Shell shell;

	Images img = new Images();

	Group overview;
	Group transactionInfoGroup;
	Group distributor;
	Label labelReceiptNumber;
	Text receiptNumber;
	Text receiptNumberFull;
	Label distributorNamePlate;
	Text distributorName;
	Label distributorIDPlate;
	Text distributorID;

	Label labelIssuedDate;
	Text issuedDate;
	Text txt;
	Text txt2;

	Button showSelectedRows;
	Button backDeliveryButton;
	Button callVorgesetzten;
	Button pickOut;
	Label labelSelectedRows;

	Table table;

	Sphinx spx = new Sphinx();
	String number;
	boolean recordNumber;

	boolean fetchData = false;

	public SAPLEXAGUI(String n) {

	}

	public SAPLEXAGUI() {

		createDisplay();
		createShell();

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
		shell.setText("SAPLEXA");

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

	public void createGroup() {
		overview = new Group(shell, SWT.SHADOW_ETCHED_IN);
		overview.setLayout(new GridLayout(2, true));
		overview.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		overview.setText("Ordered list");
		overview.setFont(new Font(display, "Calibri", 20, SWT.BOLD));

		transactionInfoGroup = new Group(overview, SWT.SHADOW_ETCHED_IN);
		transactionInfoGroup.setLayout(new GridLayout(2, true));
		transactionInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		labelReceiptNumber = new Label(transactionInfoGroup, SWT.NONE);
		labelReceiptNumber.setText("Looking for OID: ");
		labelReceiptNumber.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		labelReceiptNumber.setFont(new Font(display, "Calibri", 22, SWT.NONE));

		txt2 = new Text(transactionInfoGroup, SWT.SINGLE);
		txt2.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, true, 1, 1));
		txt2.setText("----------");
		txt2.setFont(new Font(display, "Calibri", 22, SWT.NONE));
		txt2.setEditable(false);

		txt = new Text(transactionInfoGroup, SWT.SINGLE);
		txt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		// txt.setText("SPHINX Voice Control is booting... Please wait");
		txt.setFont(new Font(display, "Calibri", 10, SWT.NONE));
		txt.setEditable(false);

		Label lbl = new Label(overview, SWT.NONE);
		lbl.setLayoutData(new GridData(SWT.FILL, SWT.RIGHT, true, true, 1, 1));
		lbl.setText("Wird aufgenommen: " + spx.getRecordNumber());

		
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
	
		

		spx = new Sphinx(txt, txt2, table);
		// Filling the table with data

	}

	public void createButtons() {

		pickOut = new Button(shell, SWT.PUSH);
		pickOut.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
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
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		} // end while
	} // end open
}
