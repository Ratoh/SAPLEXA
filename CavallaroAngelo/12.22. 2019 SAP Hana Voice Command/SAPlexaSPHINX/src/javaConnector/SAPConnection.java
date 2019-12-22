package javaConnector;
import java.util.ArrayList;
import java.util.Collection;

import com.sap.conn.jco.*;

import model.Item;
import model.Order;

public class SAPConnection {

	public Order ord;
	public Item item;
	public ArrayList<Integer> openPositions = new ArrayList<>();
	public Collection<Order> orderlist = new ArrayList<>();
	public Collection<Item> itemlist = new ArrayList<>();
	public JCoRepository repository;
	public JCoFunction function;
	public JCoDestination d;

	public SAPConnection() throws JCoException {
		// Initialisieren und Testen der Verbindung
		d = JCoDestinationManager.getDestination("ConProperties");
		d.ping();

		repository = d.getRepository();
	}
	

	public Collection<Order> getProposalList(String prefix) throws JCoException {
		// Funktion aufrufen
		function = repository.getFunction("ZE268_GETPROPOSALLIST");
		JCoParameterList input = function.getImportParameterList();
		input.setValue("I_PREFIX", prefix);
		function.execute(d);

		JCoParameterList valuelist = function.getExportParameterList();

		JCoTable jcotable = valuelist.getTable(0);

		for (int i = 0; i < jcotable.getNumRows(); i++) {
			jcotable.setRow(i);
			ord = new Order();
			ord.setOrderID(jcotable.getString("EBELN"));
			ord.setDistributorID(jcotable.getString("LIFNR"));
			ord.setOrderDate(jcotable.getDate("BEDAT"));

			orderlist.add(ord);

		}


		return orderlist;

	}
	
	
	public ArrayList<Integer> getOpenPositions(String orderId) throws JCoException{
						
		function = repository.getFunction("ZE268_GETOPENPOSITION");
		JCoParameterList input = function.getImportParameterList();
		input.setValue("I_ORDERID", orderId);
		function.execute(d);
		
		JCoParameterList valuelist = function.getExportParameterList();
		
		JCoTable jcotable = valuelist.getTable(0);
		
		openPositions.clear();

		for (int i = 0; i < jcotable.getNumRows(); i++) {
			jcotable.setRow(i);
			openPositions.add( jcotable.getInt("WEMNG")) ;
		}
		return openPositions;
		
	}
	
	public ArrayList<Item> getOrderItems(String orderId) throws JCoException{
		
		function = repository.getFunction("ZE268_GETORDERITEMS");
		JCoParameterList input = function.getImportParameterList();
		input.setValue("I_ORDERID", orderId);
		function.execute(d);
		
		JCoParameterList valuelist = function.getExportParameterList();
		
		JCoTable jcotable = valuelist.getTable(0);


		for (int i = 0; i < jcotable.getNumRows(); i++) {
			jcotable.setRow(i);
			item = new Item();
			item.setItemPosition(jcotable.getString("EBELP"));
			item.setMaterialID(jcotable.getString("MATNR"));
			item.setDescription(jcotable.getString("TXZ01"));
			item.setAmountOrdered(jcotable.getInt("MENGE"));
			item.setStockDescription(jcotable.getString("LGORT"));

			itemlist.add(item);

		}
		
		return (ArrayList<Item>) itemlist;
	}

	
}