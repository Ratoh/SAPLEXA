package listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import media.Images;

public class VoiceButtonSelectionListener extends SelectionAdapter{

	Display display;
	Dialog dlg;
	Shell dlgShell;
	
	Images img = new Images();
	
	public VoiceButtonSelectionListener(Display ds) {
		this.display = ds;
		
	}
	
	public void widgetSelected(SelectionEvent e) { 
		showDialog();
	}
	
	
	public void showDialog() {
		dlgShell = new Shell(display);
		dlgShell.setImage(img.SHELLICON(display));
		dlgShell.setText("Voice Control SAPlexa");
		dlgShell.setLayout(new FillLayout());
		Text txt = new Text(dlgShell, SWT.SINGLE);
		txt.setText("Hallo");
		dlgShell.open();
	}
	
}
