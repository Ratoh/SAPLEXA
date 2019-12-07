package javaConnector;
import java.util.ArrayList;
import java.util.Collection;

import com.sap.conn.jco.JCoException;

public class Main {

	public static void main(String[] args) throws JCoException {
		SAPConnection c = new SAPConnection();

		// Puffern und Ausgabe der Belegliste
		Collection<Order> list = new ArrayList<>();
		list = c.getProposalList("662");
		if (list.size() < 30)
			System.out.println(list);
		else
			System.out.println("Your resultset is exceeding the limit of 30");
		

		// Ausgabe der offenen Positionen zur Bestellung
		System.out.println(c.getOpenPositions("4500000662"));
	}
	
	

}
