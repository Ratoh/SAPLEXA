package main.java.saplexa.application;

public class SAP_migo_Position {
	private static long positions;
	private long position;
	private boolean ok;
	private long menge;
	private String Lagerort;
	private String Werk;

	public SAP_migo_Position() {
		position = positions++;
	}

	public static long getPositions() {
		return positions;
	}

	public static void setPositions(long positions) {
		SAP_migo_Position.positions = positions;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public long getMenge() {
		return menge;
	}

	public void setMenge(long menge) {
		this.menge = menge;
	}

	public String getLagerort() {
		return Lagerort;
	}

	public void setLagerort(String lagerort) {
		Lagerort = lagerort;
	}

	public String getWerk() {
		return Werk;
	}

	public void setWerk(String werk) {
		Werk = werk;
	}

}
