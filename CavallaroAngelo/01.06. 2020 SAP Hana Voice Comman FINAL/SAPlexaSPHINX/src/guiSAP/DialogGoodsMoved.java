package guiSAP;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DialogGoodsMoved extends Dialog {
	String matdoc;

	public DialogGoodsMoved(Shell parent) {
		super(parent);
	}

	public DialogGoodsMoved(Shell parent, String matdoc) {
		super(parent);
		this.matdoc = matdoc;
	}

	public void createContents(Shell dlgShell) {
		FillLayout fill = new FillLayout(SWT.VERTICAL);
		fill.marginHeight = 20;
		fill.marginWidth = 20;
		dlgShell.setLayout(fill);
		Text textfield = new Text(dlgShell, SWT.SINGLE); // Einzeiliges Textfeld
		textfield.setText("Material Receipt :" + matdoc);
		textfield.setFont(new Font(textfield.getDisplay(), "Calibri", 20, SWT.NONE));
		textfield.setEditable(false);

		Button btnOk = new Button(dlgShell, SWT.PUSH);
		btnOk.setText("Ok");

		btnOk.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {

				dlgShell.close();
			}

		});

		dlgShell.pack();
	}

	public void open() {
		Shell myOwnShell;
		Display dsp = this.getParent().getDisplay();
		myOwnShell = new Shell(dsp);
		createContents(myOwnShell);
		myOwnShell.open();

		while (!myOwnShell.isDisposed()) {
			if (!dsp.readAndDispatch()) {
				dsp.sleep();
			}
		}

	}

}
