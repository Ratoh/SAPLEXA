package guiSAP;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import javaConnector.Order;

public class ViewImageListener extends SelectionAdapter {

	Display display;
	Shell btnViewImageShell;

	Images img = new Images();
	Image uncheckedImage;

	public ViewImageListener(Display display) {

		this.display = display;
	}

	public void widgetSelected(SelectionEvent e) {

		createContents();

	}

	public void createContents() {

		btnViewImageShell = new Shell();
		GridLayout grid = new GridLayout(1, true);
		btnViewImageShell.setLayout(grid);
		// btnViewImageShell.setSize(600, 800);
		btnViewImageShell.setImage(img.SHELLICON(display));
		btnViewImageShell.setText("SAPLEXA");

		createLabel();

		btnViewImageShell.pack();
		btnViewImageShell.open();

	}

	public void createLabel() {

		uncheckedImage = new Image(display, "schrauben.jpg");
		Label label = new Label(btnViewImageShell, SWT.BORDER);
		label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		label.setImage(uncheckedImage);

	}

}
