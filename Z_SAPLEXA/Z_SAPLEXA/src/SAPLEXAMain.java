import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class SAPLEXAMain {

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(2, true));
		shell.setSize(800, 800); // funktioniert nicht wegen shell.pack() (ganz unten)
		shell.setText("SAP Tabelle");

		// Tabelle erstellen
		Table table = new Table(shell, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setHeaderVisible(true);
		String[] titles = { "Position ", "Materialkurztext ", "Menge ", "Bewegungsart ", "Lagerort ", "Werk ", "Lieferant " };
		table.setFont(new Font(display, "Arial", 14, SWT.BOLD));
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setLinesVisible(true);
		table.setBounds(25, 25, 400, 400);

		// Anzahl der Titel = Spaltenanzahl
		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.BORDER);
			column.setText(titles[loopIndex]);
		}

		// Inhalt befüllen
		for (int loopIndex = 0; loopIndex < 6; loopIndex++) {
			TableItem item = new TableItem(table, SWT.BORDER);
			item.setText("Position " + loopIndex);
			item.setText(0, "Position " + loopIndex);
			item.setText(1, "R1-002 Räder ");
			item.setText(2, "10" + " St. ");
			item.setText(3, "Wareneingang ");
			item.setText(4, "Fertigwaren FG00 ");
			item.setText(5, "Heidelberg HD00 ");
			item.setText(6, "LF1-### ");
		}

		// "Format" geben
		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			table.getColumn(loopIndex).pack();
		}

		// Textfeld erstellen
		final Text text = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 4));
		text.setFont(new Font(display, "Arial", 14, SWT.BOLD));
		text.setBackground(new Color(display, 200, 200, 0));

		// Listener auf Selektion bzw Auswahl der Position
		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (event.detail == SWT.CHECK) {
					text.setText("Es wurde ausgewählt: " + event.item);
				} else {
					text.setText("Es wurde selektiert: " + event.item);
				}
			}
		});

		// dicht um die Kind-Widgets packen
		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}