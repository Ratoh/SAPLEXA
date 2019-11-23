package guiSAP;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class SAPlexaGUI {
	
	Shell shell;
	Display display;
	
	Images img = new Images();
	
	Button voiceButton;
	Button tableButton;
	
	Menu menubar;
	MenuItem openMenuItem;
	Menu openMenu;
	MenuItem openFile;
	
	Group buttonGroup;
	
	public SAPlexaGUI ( ) {
		
		createDisplay();
		createShell();
		createImage();
		createMenu();
		createIconLabels();
		createButtons();
	}
	
	public void createDisplay() {
		display = new Display();
	}
	
	public void createShell() {
		shell = new Shell(display);
		shell.setLayout(new GridLayout(2, true));
		shell.setText("SAPlexa");
		
	}
	
	public void createMenu() {
		menubar = new Menu(shell, SWT.BAR);
		
		shell.setMenuBar(menubar);
		
		openMenuItem = new MenuItem(menubar, SWT.CASCADE);
		openMenuItem.setText("File");
		
		openMenu = new Menu(shell, SWT.DROP_DOWN);
		openMenuItem.setMenu(openMenu);
		
		openFile= new MenuItem(openMenu, SWT.PUSH);
		openFile.setText("Open File");
		
	}
	
	public void createButtons() {
		buttonGroup = new Group(shell,SWT.SHADOW_ETCHED_IN);
		buttonGroup.setLayout(new GridLayout(2,true));
		buttonGroup.setText("Voice Control");
		
		voiceButton = new Button(buttonGroup, SWT.PUSH);
		voiceButton.setText("Call SAPlexa");
		voiceButton.setToolTipText("Call the proper voice command for SAP");
		voiceButton.addSelectionListener(new VoiceButtonSelectionListener(display));
		
		tableButton = new Button(buttonGroup, SWT.PUSH);
		tableButton.setText("Show Table");
		tableButton.setToolTipText("Retrieve Data from SAP");
		tableButton.addSelectionListener(new TableButtonSelectionListener(display));
		shell.pack();
		
	}
	
	public void createImage() {
		shell.setImage(img.SHELLICON(display));
	}
	
	public void createIconLabels() {
		Label iconLabel = new Label(shell, SWT.NONE);
		iconLabel.setImage(img.SAPLOGO(display));
	}
	
	public void open() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	

}
