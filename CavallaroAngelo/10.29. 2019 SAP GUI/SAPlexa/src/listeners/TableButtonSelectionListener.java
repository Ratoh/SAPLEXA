package listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import media.Images;

public class TableButtonSelectionListener extends SelectionAdapter{

	Display display;
	Shell tblShell;
	
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
	Label labelSelectedRows;
	
	Table table;
	
	
	public TableButtonSelectionListener(Display ds) {
		this.display = ds;
		
	}
	
	public void widgetSelected(SelectionEvent e) { 
		showDialog();
	}
	
	
	public void showDialog() {
		tblShell = new Shell(display);
		tblShell.setLayout(new GridLayout(1,true));
		tblShell.setImage(img.SHELLICON(display));
		tblShell.setText("Good receival - Overview");
		
		createGroup();
		createTable();
		createButtons();
		tblShell.open();
	}
	
	

	public void createGroup() {
		overview = new Group(tblShell, SWT.SHADOW_ETCHED_IN);
		overview.setLayout(new GridLayout(2, true));
		overview.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		overview.setText("MIGO Overview");
		
		
		transactionInfoGroup = new Group(overview, SWT.SHADOW_ETCHED_IN );
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
		issuedDate = new Text (transactionInfoGroup, SWT.NONE);
		issuedDate.setText("<Belegdatum>");
		issuedDate.setFont(new Font(display, "Calibri", 26, SWT.NONE));
		issuedDate.setEditable(false);
		
		distributor = new Group(overview, SWT.SHADOW_ETCHED_IN);
		distributor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		distributor.setText("Distributor Details");
		distributor.setFont(new Font(display, "Calibri", 20, SWT.BOLD));
		distributor.setLayout(new GridLayout(2, false));
		
		distributorNamePlate = new Label (distributor, SWT.NONE);
		distributorNamePlate.setText("Distributor/Vendor");
		
		distributorName = new Text(distributor, SWT.NONE);
		distributorName.setText("<Lieferantname>");
		distributorName.setFont(new Font(display, "Calibri", 26, SWT.NONE));
		distributorName.setEditable(false);
		
		distributorIDPlate = new Label (distributor, SWT.NONE);
		distributorIDPlate.setText("Distributor ID");
		
		distributorID = new Text(distributor, SWT.NONE);
		distributorID.setText("<Lieferantennummer>");
		distributorID.setFont(new Font(display, "Calibri", 26, SWT.NONE));
		distributorID.setEditable(false);
		
		
	}
	
	public void createTable() {
		 table = new Table(overview, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		    table.setHeaderVisible(true);
		    table.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true, true, 2, 1));
		    table.setSize(200, 100);
		    String[] columns = { "Position", "Kurzbezeichnung", "Menge", "Lager", "Werk" };

		    for (int loopIndex = 0; loopIndex < columns.length; loopIndex++) {
		      TableColumn column = new TableColumn(table, SWT.NULL);
		      column.setText(columns[loopIndex]);
		      column.setMoveable(true);
		      
		      column.setWidth((tblShell.getSize().x -50) / columns.length);
		      column.setResizable(true);
		      
		    }

		  
		    // Filling the table with data
		    for (int loopIndex = 0; loopIndex < 20; loopIndex++) {
		      TableItem item = new TableItem(table, SWT.NULL);
		      item.setText("Item " + loopIndex);
		      item.setText(0, "Item " + loopIndex);
		      item.setText(1, "2");
		      item.setText(2, "No");
		      item.setText(3, "A table item");
		      item.setFont(new Font(display, "Calibri", 19, SWT.NONE));
		    	    	
		     }
		    
		      
		    }

		   /*for (int loopIndex = 0; loopIndex < columns.length; loopIndex++) {
		      table.getColumn(loopIndex).pack();
		    }*/
		    
		
		
	
	public void createButtons() {
		
		
		labelSelectedRows = new Label(overview, SWT.NONE);
		labelSelectedRows.setText("Selected items array");
		labelSelectedRows.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		
		
		showSelectedRows = new Button(overview, SWT.PUSH);
		showSelectedRows.setText("Booking - Store selected items");
		showSelectedRows.addSelectionListener(new SelectedRowsListener(table, labelSelectedRows));

		//showSelectedRows.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}
	
}
