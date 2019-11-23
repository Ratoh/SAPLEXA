package guiSAP;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class BackDeliveryListener extends SelectionAdapter {

	Display display;
	Shell listShell;

	Group group;
	Label label;
	List list;
	Button confirmButton;

	public BackDeliveryListener(Display display) {

		this.display = display;

	}

	public void widgetSelected(SelectionEvent e) {

		createContents();

	}

	public void createContents() {

		listShell = new Shell(display);
		GridLayout grid = new GridLayout(2, true);
		listShell.setLayout(grid);
		listShell.setText("Optionen");

		grid.marginBottom = 20;
		grid.marginTop = 20;
		grid.marginLeft = 20;
		grid.marginRight = 20;

		group = new Group(listShell, SWT.SHADOW_ETCHED_IN);
		group.setLayout(new GridLayout(1, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		createLabel();
		createList();
		createButtons();
		listShell.pack();
		listShell.open();

	}

	public void createLabel() {

		label = new Label(group, SWT.BORDER);
		label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		label.setText("Bitte Grund auswählen: ");
		label.setFont(new Font(display, "Calibri", 18, SWT.BOLD));

	}

	public void createList() {

		list = new List(group, SWT.BORDER_SOLID | SWT.MULTI | SWT.V_SCROLL);
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		list.setFont(new Font(display, "Calibri", 18, SWT.BOLD));

		list.add("beschädigte Ware");
		list.add("Menge nicht vollständig");
		list.add("falscher Lagerort");
		list.add("Sonstiges");

	}

	public void createButtons() {

		confirmButton = new Button(group, SWT.PUSH);
		confirmButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		confirmButton.setText("OK");
		confirmButton.setFont(new Font(display, "Calibri", 15, SWT.BOLD));

		confirmButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {

				MessageBox msgb = new MessageBox(listShell, SWT.ICON_INFORMATION);
				msgb.setText("Information");

				// bricht ab wenn nichts selektiert wurde
				msgb.setMessage("Im System gespeichert: \n \n" + list.getItem(list.getSelectionIndex()));
				msgb.open();

				listShell.dispose();
			}
		});
	}
}
