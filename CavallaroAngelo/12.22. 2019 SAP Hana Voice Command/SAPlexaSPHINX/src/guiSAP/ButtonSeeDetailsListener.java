package guiSAP;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class ButtonSeeDetailsListener extends SelectionAdapter {

	Display display;
	Shell btnOkShell;

	Images img = new Images();

	Group overview;
	Group transactionInfoGroup;
	Group distributor;
	Label labelReceiptNumber;
	Text receiptNumber;
	Label distributorNamePlate;
	Text distributorName;
	Label distributorIDPlate;
	Text distributorID;

	Label labelIssuedDate;
	Text issuedDate;

	Button showSelectedRows;
	Button backDeliveryButton;
	Button callVorgesetzten;
	Label labelSelectedRows;

	Table table;
	Text text;

	public ButtonSeeDetailsListener(Display display) {

		this.display = display;

	}

	public void widgetSelected(SelectionEvent e) {

		createContents();

	}

	public void createContents() {

		btnOkShell = new Shell();
		GridLayout grid = new GridLayout(1, true);
		btnOkShell.setLayout(grid);
		btnOkShell.setImage(img.SHELLICON(display));
		btnOkShell.setText("SAPLEXA");

		createGroup();
		createTable();
		createButtons();

		btnOkShell.pack();
		btnOkShell.open();

	}

	public void createGroup() {

		overview = new Group(btnOkShell, SWT.SHADOW_ETCHED_IN);
		overview.setLayout(new GridLayout(2, true));
		overview.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		overview.setText("MIGO Overview");

		transactionInfoGroup = new Group(overview, SWT.SHADOW_ETCHED_IN);
		transactionInfoGroup.setFont(new Font(display, "Calibri", 20, SWT.BOLD));
		transactionInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		transactionInfoGroup.setText("General Information");
		transactionInfoGroup.setLayout(new GridLayout(2, false));

		labelReceiptNumber = new Label(transactionInfoGroup, SWT.NONE);
		labelReceiptNumber.setText("Receipt ID");

		receiptNumber = new Text(transactionInfoGroup, SWT.NONE);
		receiptNumber.setText("<Belegnummer>");
		receiptNumber.setFont(new Font(display, "Calibri", 26, SWT.NONE));
		receiptNumber.setEditable(false);

		labelIssuedDate = new Label(transactionInfoGroup, SWT.NONE);
		labelIssuedDate.setText("Issued on");
		issuedDate = new Text(transactionInfoGroup, SWT.NONE);
		issuedDate.setText("<Belegdatum>");
		issuedDate.setFont(new Font(display, "Calibri", 26, SWT.NONE));
		issuedDate.setEditable(false);

		distributor = new Group(overview, SWT.SHADOW_ETCHED_IN);
		distributor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		distributor.setText("Distributor Details");
		distributor.setFont(new Font(display, "Calibri", 20, SWT.BOLD));
		distributor.setLayout(new GridLayout(2, false));

		distributorNamePlate = new Label(distributor, SWT.NONE);
		distributorNamePlate.setText("Distributor/Vendor");

		distributorName = new Text(distributor, SWT.NONE);
		distributorName.setText("<Lieferantname>");
		distributorName.setFont(new Font(display, "Calibri", 26, SWT.NONE));
		distributorName.setEditable(false);

		distributorIDPlate = new Label(distributor, SWT.NONE);
		distributorIDPlate.setText("Distributor ID");

		distributorID = new Text(distributor, SWT.NONE);
		distributorID.setText("<Lieferantennummer>");
		distributorID.setFont(new Font(display, "Calibri", 26, SWT.NONE));
		distributorID.setEditable(false);

	}

	public void createTable() {
		
		table = new Table(overview, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setSize(200, 100);
		table.setFont(new Font(display, "Calibri", 19, SWT.NONE));
		String[] columns = { "Position", "Material", "Kurzbezeichnung", "offene Menge", "angelieferte Menge",
				"Lagerort", "Bild" };
		
		for (int loopIndex = 0; loopIndex < columns.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(columns[loopIndex]);
			column.setMoveable(true);
			column.setWidth(270);
			//column.setWidth((btnOkShell.getSize().x - 50) / columns.length);
			column.setResizable(true);
//			column.addListener(SWT.RESIZE, new Listener() {
//				@Override
//				public void handleEvent(Event event) {
//					table.setFont(new Font(display, "Calibri", 19, SWT.NONE));
//				}
//			});

		}

		for (int i = 0; i < 6; i++) {
			new TableItem(table, SWT.NONE);
	
		
		}

//		// Filling the table with data
//		for (int loopIndex = 0; loopIndex < 3; loopIndex++) {
//			TableItem item = new TableItem(table, SWT.NULL);
//			item.setText("Item " + loopIndex);
//			item.setText(0, "Position " + loopIndex);
//			item.setText(1, "Material #");
//			item.setText(2, "Schrauben");
//			item.setText(3, "50");
//			item.setText(4, "100");
//			item.setText(5, "Fertigwaren-Lager");
//			Button btnImage = new Button(table, SWT.PUSH);
//			btnImage.pack();
//			btnImage.setText("View Image");
//			btnImage.addSelectionListener(new ViewImageListener(display));
//			item.setFont(new Font(display, "Calibri", 19, SWT.NONE));
//
//		}
	}

	/*
	 * for (int loopIndex = 0; loopIndex < columns.length; loopIndex++) {
	 * table.getColumn(loopIndex).pack(); }
	 */

	public void createButtons() {

		labelSelectedRows = new Label(overview, SWT.NONE);
		labelSelectedRows.setText("Selektiert wurden: \n");
		labelSelectedRows.setFont(new Font(display, "Arial", 19, SWT.BOLD));
		labelSelectedRows.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		callVorgesetzten = new Button(overview, SWT.PUSH);
		callVorgesetzten.setText("Vorgesetzten anrufen");
		callVorgesetzten.setLayoutData(new GridData(SWT.RIGHT, SWT.RIGHT, false, false, 1, 1));
		callVorgesetzten.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(btnOkShell, SWT.OK | SWT.CANCEL | SWT.ICON_QUESTION);
				box.setText("SAPLEXA");
				box.setMessage("Call Max Mustermann");

				int answer = box.open();
				switch (answer) {
				case SWT.OK:
					System.out.print("Calling...");
					break;
				case SWT.CANCEL:
					break;
				}
			}
		});

		showSelectedRows = new Button(overview, SWT.PUSH);
		showSelectedRows.setText("Book");
		showSelectedRows.setForeground(new Color(display, 0, 255, 0));
		showSelectedRows.setFont(new Font(display, "Arial", 18, SWT.BOLD));
		showSelectedRows.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		showSelectedRows.addSelectionListener(new BookingListener(table, labelSelectedRows));

		backDeliveryButton = new Button(overview, SWT.PUSH);
		backDeliveryButton.setText("Mark as error due to...");
		backDeliveryButton.setForeground(new Color(display, 255, 0, 0));
		backDeliveryButton.setFont(new Font(display, "Arial", 18, SWT.BOLD));
		backDeliveryButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		backDeliveryButton.addSelectionListener(new BackDeliveryListener(display));

		// showSelectedRows.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
		// 1, 1));
	}
}
