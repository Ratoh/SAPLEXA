package guiSAP;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;



public class ViewImageListener implements Listener{

	Display display;
	Shell btnViewImageShell;

	Images img = new Images();
	Image uncheckedImage;
	
	Table table;

	public ViewImageListener(Display display, Table tb) {

		this.display = display;
		this.table = tb;
	}


	public void createContents(String matID) {

		btnViewImageShell = new Shell();
		GridLayout grid = new GridLayout(1, true);
		btnViewImageShell.setLayout(grid);
		
		btnViewImageShell.setImage(img.SHELLICON(display));
		btnViewImageShell.setText("Image Viewer");

		createLabel(matID);

		btnViewImageShell.pack();
		btnViewImageShell.open();

	}

	public void createLabel(String materialID) {

		uncheckedImage = new Image(display, "materialImages/" +materialID + ".jpg");
		Label label = new Label(btnViewImageShell, SWT.BORDER);
		label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		label.setSize(600, 600);
		label.setImage(uncheckedImage);

	}




	@Override
	public void handleEvent(Event e) {
		TableItem t =  table.getSelection()[0];
		System.out.println(t.getText(1));
		createContents(t.getText(1));
		
		
	}

}
