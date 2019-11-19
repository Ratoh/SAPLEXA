import com.sap.conn.jco.*;


public class ConnectTest {

	public static void main(String[] args) throws JCoException {
		
		
		// Initialisieren und Testen der Verbindung
		JCoDestination d = JCoDestinationManager.getDestination("ConProperties");
		d.ping();

		
		JCoRepository repository = d.getRepository();
		JCoFunction function = repository.getFunction("ZE268_GETPROPOSAL");
		
		 
		JCoParameterList input = function.getImportParameterList(); 
		input.setValue("I_PREFIX","4500000002");		
		 
		// Funktion aufrufen
		function.execute(d);
		  
		JCoParameterList valuelist = function.getExportParameterList();

		//for (int i = 0; i < jcotable.getNumRows(); i++) {
			//String string = jcotable[i];
		//	jcotable.setRow(i);
		//	System.out.println(jcotable.getValue("MESSAGE"));
		//}
		
		for (JCoField jcr : valuelist) {
			System.out.println("TYPE " +jcr.getTypeAsString() );
			System.out.println(jcr.getValue() );			
		}
		
		
		
//for (JCoField jCoField : jcotable) {
//	System.out.println(jCoField.getValue());
//}			

		
		
//		for (JCoField jCoField : table) {
//			System.out.println(jCoField.getValue());
//		}
		
	}

	
/*	
    // Methode ermittelt Liste von Stringwerten zu einer Selektion (selOpt)
    // Beispiel: Auftragsnummern zu einer Selektion (z.B. matNo)
    public static ArrayList<String> getStringValues(String tblNo, String selOpt, String fieldName) throws JCoException {

        ArrayList<String> as = new ArrayList<String>();
        JCoRepository repos = sapErp.getRepository();        
        JCoFunction function = repos.getFunction("RFC_READ_TABLE");
        if(function == null)
           throw new AbapException("JCO_ERROR_FUNCTION_NOT_FOUND");

        function.getImportParameterList().setValue("QUERY_TABLE", tblNo);
        JCoParameterList tableParameters  = function.getTableParameterList();
        JCoTable options = tableParameters.getTable("OPTIONS");
        options.appendRow();
        options.setValue( "TEXT", selOpt );           
        
        JCoTable fields = tableParameters.getTable("FIELDS");
        fields.appendRow();
        fields.setValue( "FIELDNAME", fieldName );
        
        function.execute(sapErp);

        JCoTable resultData = tableParameters.getTable("DATA");
        int nRows = resultData.getNumRows();
        for (int i = 0; i < nRows; i++) {
           resultData.setRow(i);
           as.add(resultData.getString("WA"));
        }

        return as;     
    }
*/
	
}
