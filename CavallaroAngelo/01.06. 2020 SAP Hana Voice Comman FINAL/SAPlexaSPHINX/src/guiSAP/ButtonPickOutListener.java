package guiSAP;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import model.Order;

public class ButtonPickOutListener extends SelectionAdapter {

	Display display;
	Shell btnPickShell;
	Order selectedOrder;
	
	Images img = new Images();
	
	Group overall, header, distributorDetails;
	Label labelReceiptNumber, labelDistributor, labelReceiptDate, labelBookingDate, distributorIDPlate;
	Text receiptNumber, distributorName, receiptDate, bookingDate, distributorID;
	Button btnSeeDetails, btnReturn;
	
	
	
	public ButtonPickOutListener(Display display) {
	
		this.display = display;
	}
	
	public void widgetSelected(SelectionEvent e) {
		
		createContents();
		
		
	}
	
	public void widgetSelected(Display dsp, Order ord) {
		this.display = dsp;
		createContents();
	}
	
	public void createContents() {
		
		btnPickShell = new Shell();
		GridLayout grid = new GridLayout(2, true);
		btnPickShell.setLayout(grid);
		btnPickShell.setImage(img.SHELLICON(display));
		btnPickShell.setText("SAPLEXA");
		
//		grid.marginBottom = 20;
//		grid.marginTop = 20;
//		grid.marginLeft = 20;
//		grid.marginRight = 20;

		overall = new Group(btnPickShell, SWT.SHADOW_ETCHED_IN);
		overall.setLayout(new GridLayout(2, true));
		overall.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 3));

		createLabel();
		createButtons();
		
		btnPickShell.pack();
		btnPickShell.open();
		
	}
	
	public void createLabel() {
		
		header = new Group(overall, SWT.SHADOW_ETCHED_IN);
		header.setLayout(new GridLayout(2, true));
		header.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		header.setText("Receipt information");
		header.setFont(new Font(display, "Calibri", 20, SWT.BOLD));
		
		labelReceiptNumber = new Label(header, SWT.NONE);
		labelReceiptNumber.setText("Receipt ID");

		receiptNumber = new Text(header, SWT.NONE);
		receiptNumber.setText("<Belegnummer>");
		receiptNumber.setFont(new Font(display, "Calibri", 36, SWT.NONE));
		receiptNumber.setEditable(false);

		labelBookingDate = new Label(header, SWT.NONE);
		labelBookingDate.setText("Issued on");
		
		bookingDate = new Text(header, SWT.NONE);
		bookingDate.setText("<Belegdatum>");
		bookingDate.setFont(new Font(display, "Calibri", 22, SWT.NONE));
		bookingDate.setEditable(false);

		labelDistributor = new Label(header, SWT.NONE);
		labelDistributor.setText("Distributor");

		distributorName = new Text(header, SWT.NONE);
		distributorName.setText("<Lieferantname>");
		distributorName.setFont(new Font(display, "Calibri", 22, SWT.NONE));
		distributorName.setEditable(false);

		distributorIDPlate = new Label(header, SWT.NONE);
		distributorIDPlate.setText("Distributor ID");

		distributorID = new Text(header, SWT.NONE);
		distributorID.setText("<Lieferantennummer>");
		distributorID.setFont(new Font(display, "Calibri", 22, SWT.NONE));
		distributorID.setEditable(false);
	}
	
	public void createButtons() {
		
		btnSeeDetails = new Button(overall, SWT.PUSH);
		btnSeeDetails.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		btnSeeDetails.setFont(new Font(display, "Calibri", 20, SWT.BOLD));
		btnSeeDetails.setText("See Details");
		btnSeeDetails.addSelectionListener(new ButtonSeeDetailsListener(display));
		
		btnReturn = new Button(overall, SWT.PUSH);
		btnReturn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		btnReturn.setFont(new Font(display, "Calibri", 20, SWT.BOLD));
		btnReturn.setText("Return");
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnPickShell.close();
			}
		});
	}
}
