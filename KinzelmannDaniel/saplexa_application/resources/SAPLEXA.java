import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLayeredPane;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JFormattedTextField;

public class SAPLEXA {

	private JFrame frmSaplexa;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SAPLEXA window = new SAPLEXA();
					window.frmSaplexa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SAPLEXA() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSaplexa = new JFrame();
		frmSaplexa.setForeground(SystemColor.controlDkShadow);
		frmSaplexa.setOpacity(0.7f);
		frmSaplexa.setType(Type.UTILITY);
		frmSaplexa.setTitle("SAPLEXA");
		frmSaplexa.setBounds(100, 100, 918, 499);
		frmSaplexa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSaplexa.getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 126, 878, 8);
		frmSaplexa.getContentPane().add(separator);
		
		JPanel Information = new JPanel();
		Information.setBounds(12, 12, 588, 102);
		frmSaplexa.getContentPane().add(Information);
		Information.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("147px"),
				ColumnSpec.decode("147px:grow"),
				ColumnSpec.decode("147px"),
				ColumnSpec.decode("147px:grow"),},
			new RowSpec[] {
				FormSpecs.GLUE_ROWSPEC,
				FormSpecs.GLUE_ROWSPEC,}));
		
		JLabel BelegdatumLabel = new JLabel("Belegdatum:");
		Information.add(BelegdatumLabel, "1, 1, right, default");
		
		JFormattedTextField BelegdatumText = new JFormattedTextField();
		BelegdatumLabel.setLabelFor(BelegdatumText);
		Information.add(BelegdatumText, "2, 1, fill, default");
		
		JLabel LieferscheinLabel = new JLabel("Lieferschein:");
		Information.add(LieferscheinLabel, "3, 1, right, default");
		
		JFormattedTextField LieferscheinText = new JFormattedTextField();
		LieferscheinLabel.setLabelFor(LieferscheinText);
		Information.add(LieferscheinText, "4, 1, fill, default");
		
		JLabel BuchungsdatumLabel = new JLabel("Buchungsdatum:");
		Information.add(BuchungsdatumLabel, "1, 2, right, default");
		
		JFormattedTextField BuchungsdatumText = new JFormattedTextField();
		BuchungsdatumLabel.setLabelFor(BuchungsdatumText);
		Information.add(BuchungsdatumText, "2, 2, fill, default");
		
		JLabel FrachtbriefLabel = new JLabel("Frachtbrief:");
		Information.add(FrachtbriefLabel, "3, 2, right, default");
		
		JFormattedTextField FrachtbriefText = new JFormattedTextField();
		FrachtbriefLabel.setLabelFor(FrachtbriefText);
		Information.add(FrachtbriefText, "4, 2, fill, default");
		
		JPanel Table = new JPanel();
		Table.setAlignmentY(1.0f);
		Table.setBounds(12, 146, 878, 291);
		frmSaplexa.getContentPane().add(Table);
		GridBagLayout gbl_Table = new GridBagLayout();
		gbl_Table.columnWidths = new int[]{878, 0};
		gbl_Table.rowHeights = new int[]{291, 0};
		gbl_Table.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_Table.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		Table.setLayout(gbl_Table);
		
		table = new JTable();
		table.setBorder(new LineBorder(SystemColor.windowBorder, 2));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, Boolean.FALSE, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Position", "Kurzbezeichnung", "OK", "Menge", "Lager", "Werk", "Kunde"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Boolean.class, Integer.class, String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(119);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(144);
		table.getColumnModel().getColumn(5).setPreferredWidth(149);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.weighty = 1.0;
		gbc_table.weightx = 1.0;
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		Table.add(table, gbc_table);
	}
}
