package main.java.saplexa.application;

import java.util.ArrayList;

public class SAP_migo_Bestellung {
	private long Bestellnummer;
	private ArrayList<SAP_migo_Position> Positions = new ArrayList<SAP_migo_Position>();

	public void addPosition(SAP_migo_Position newPosition) {
		Positions.add(newPosition);
	}

	public long getBestellnummer() {
		return Bestellnummer;
	}

	public void setBestellnummer(long bestellnummer) {
		Bestellnummer = bestellnummer;
	}

}
