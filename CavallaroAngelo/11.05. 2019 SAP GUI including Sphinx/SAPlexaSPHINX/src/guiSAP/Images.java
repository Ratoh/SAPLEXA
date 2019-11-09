package guiSAP;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class Images {
	Image img;
	
	public Images() {
		
	}
	
	public Image SAPLOGO (Display display) {
		return new Image(display, new ImageData("SAPlogoIcon.png")) ;		 
		 
	}
	
	public Image SHELLICON (Display display) {
		return new Image(display, new ImageData("shellIcon.png"));	
	}
	

	

}