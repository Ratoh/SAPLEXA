package listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class SelectedRowsListener implements SelectionListener {
	
	Table table;
	Label label;
	
	public SelectedRowsListener() {
		
	}
	
	public SelectedRowsListener(Table t, Label l) {
		this.table = t;
		this.label = l;
	}



	public void widgetSelected(SelectionEvent arg0) {
			
		
		
		boolean[] indices = new boolean[table.getItems().length];
		
		int i= 0;
		for ( TableItem ti : table.getItems()) {
			if (ti.getChecked()) {
				indices[i] = true; // + " " +i;
			}
			i++;
		}
		
		String selectionString = "Selected items: ";
		
		i= 0;
		for (boolean b : indices) {
			if (b) {
				selectionString += i +", ";
			}
			i++;
			
		}
		if (selectionString =="Selected items: " || selectionString == "No items selected") {
			selectionString = "No items selected";
		}
		else {
			selectionString = selectionString.substring(0, selectionString.length()-2);
		}
		
		label.setText(selectionString);
	}

	public void widgetDefaultSelected(SelectionEvent arg0) {
		
		
	}

}
