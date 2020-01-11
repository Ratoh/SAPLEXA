package guiSAP;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import com.sap.conn.jco.JCoException;

import javaConnector.SAPConnection;
import model.*;
import model.Item;

public class ViewOrderDetails {

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
	
	Text infoBoxBookOrder;

	Label labelIssuedDate;
	Text issuedDate;

	Button showSelectedRows;
	Button backDeliveryButton;
	Button callVorgesetzten;
	Label labelSelectedRows;

	Table table;
	Text text;
	
	ArrayList<Integer> deliveredAmounts = new ArrayList<>();
	
	private Order order;
	
	
	public ViewOrderDetails (Display display) {
		
		this.display = display;
		
	}
	
	public void open(Order selectedOrder) {
		this.order = selectedOrder;
		createContents();
		try {
			retrieveOrderItems();
		} catch (JCoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	private void retrieveOrderItems() throws JCoException {
		SAPConnection sapConnect = new SAPConnection();
		// Retrieves OrderItems
		try {
			order.setItemlist(sapConnect.getOrderItems(order.getOrderID()));
			deliveredAmounts = sapConnect.getOpenPositions(order.getOrderID());
			
			int idx = 0;
			for (Item i : order.getItemlist()) {
				i.setAmountToBeDelivered(deliveredAmounts.get(idx));
			}
		} catch (JCoException e) {
			
			e.printStackTrace();
		}
		
		writeTableData();
		
	}

	private void writeTableData() {
		int idx = 0;
		
		// String[] columns = { "Position", "Material", "Kurzbezeichnung", "Geliefert Menge", "Bestellmenge",
		//"Lagerort", "Bild" };
		for (Item i : order.getItemlist()) {
			TableItem item = new TableItem(table, SWT.NULL);
			item.setText("Item " + idx);
			item.setText(0, i.getItemPosition());
			item.setText(1, i.getMaterialID());
			item.setText(2, i.getDescription());
			item.setText(3, "" +i.getAmountToBeDelivered());
			item.setText(4, ""+ i.getAmountOrdered());
			item.setText(5, i.getStockDescription());
			
			item.setFont(new Font(table.getDisplay(), "Calibri", 18, SWT.NONE));
			
			idx++;
		}
		
		table.addListener(SWT.MouseDoubleClick,  new ViewImageListener(table.getDisplay() , table));
		table.pack();
		
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
		btnOkShell.setFullScreen(true);
		
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
		receiptNumber.setText(order.getOrderID());
		receiptNumber.setFont(new Font(display, "Calibri", 26, SWT.NONE));
		receiptNumber.setEditable(false);

		labelIssuedDate = new Label(transactionInfoGroup, SWT.NONE);
		labelIssuedDate.setText("Issued on");
		issuedDate = new Text(transactionInfoGroup, SWT.NONE);
		issuedDate.setText(order.getOrderDate().toString());
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
		distributorID.setText(order.getDistributorID());
		distributorID.setFont(new Font(display, "Calibri", 26, SWT.NONE));
		distributorID.setEditable(false);

	}
	
	public void createTable() {
		table = new Table(overview, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setSize(200, 100);
		table.setFont(new Font(display, "Calibri", 19, SWT.NONE));
		String[] columns = { "Position", "Material", "Description", "Amount delivered", "Amount ordered",
				"Stock", "Image" };
		
		for (int loopIndex = 0; loopIndex < columns.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(columns[loopIndex]);
			column.setMoveable(true);
			column.setWidth(270);
			column.setResizable(true);

		}


	
		

	}



	public void createButtons() {

		labelSelectedRows = new Label(overview, SWT.NONE);
		labelSelectedRows.setText("");
		labelSelectedRows.setFont(new Font(display, "Arial", 19, SWT.BOLD));
		labelSelectedRows.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		
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
				switch(answer) {
				case SWT.OK: 
					System.out.print("Calling...");
					break;
				case SWT.CANCEL:
					break;
				}
			}
		});
		
		infoBoxBookOrder = new Text(overview, SWT.NONE);
		infoBoxBookOrder.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
		infoBoxBookOrder.setEditable(false);
		infoBoxBookOrder.setFont(new Font(display, "Arial", 18, SWT.BOLD));
		infoBoxBookOrder.setText("Say \"Book Order\" for adding a unit to stock");

//		showSelectedRows = new Button(overview, SWT.PUSH);
//		showSelectedRows.setText("Book");
//		showSelectedRows.setForeground(new Color(display, 0, 255, 0));
//		showSelectedRows.setFont(new Font(display, "Arial", 18, SWT.BOLD));
//		showSelectedRows.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
//		showSelectedRows.addSelectionListener(new BookingListener(table, labelSelectedRows));
//		
//		backDeliveryButton = new Button(overview, SWT.PUSH);
//		backDeliveryButton.setText("Mark as error due to...");
//		backDeliveryButton.setForeground(new Color(display, 255, 0, 0));
//		backDeliveryButton.setFont(new Font(display, "Arial", 18, SWT.BOLD));
//		backDeliveryButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
//		backDeliveryButton.addSelectionListener(new BackDeliveryListener(display));

	
	}
}
