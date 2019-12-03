import com.sap.conn.jco.*;


public class ConnectTest {

	public static void main(String[] args) throws JCoException {
		
		
		// Initialisieren und Testen der Verbindung
		JCoDestination d = JCoDestinationManager.getDestination("ConProperties");
		d.ping();

		
		JCoRepository repository = d.getRepository();
		//JCoFunction function = repository.getFunction("ZE268_GETPROPOSALLIST");
		 JCoFunction function = repository.getFunction("ZE268_GETOPENPOSITION");
		// Funktionsbaustein von Stauss : Z000_GETPROPOSAL2
		
		 
		JCoParameterList input = function.getImportParameterList(); 
		//input.setValue("I_PREFIX","450000000");		
		 input.setValue("I_ORDERID", "4500000662");
		 
		// Funktion aufrufen
		function.execute(d);
		  
		JCoParameterList valuelist = function.getExportParameterList();

		//for (int i = 0; i < jcotable.getNumRows(); i++) {
			//String string = jcotable[i];
		//	jcotable.setRow(i);
		//	System.out.println(jcotable.getValue("MESSAGE"));
		//}
		
		
		for (JCoField jcr : valuelist) {
			// Ausgabe für gelieferte Anzahl aus EKET Tabelle
			System.out.println(jcr.getInt());
			
		// Ausgabe für die EKKO Tabelle
		//	System.out.println(jcr.getValue() );			
		}
		
		
	
	
}
}