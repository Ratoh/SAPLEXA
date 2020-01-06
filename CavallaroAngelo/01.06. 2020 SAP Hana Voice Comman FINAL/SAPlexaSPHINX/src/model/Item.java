package model;


public class Item {

	private String itemPosition; 
	
	private String materialID;
	
	private String description;
	
	private int amountOrdered;
	
	private int amountToBeDelivered;
	
	private String stockDescription;
	
	
	
	public Item() {
		
	}



	public Item(String itemPosition, String materialID, String description, int amountOrdered,
			String stockDescription) {
		super();
		this.itemPosition = itemPosition;
		this.materialID = materialID;
		this.description = description;
		this.amountOrdered = amountOrdered;
		this.stockDescription = stockDescription;
	}



	public int getAmountToBeDelivered() {
		return amountToBeDelivered;
	}



	public void setAmountToBeDelivered(int amountToBeDelivered) {
		this.amountToBeDelivered = amountToBeDelivered;
	}



	public String getItemPosition() {
		return itemPosition;
	}



	public void setItemPosition(String itemPosition) {
		this.itemPosition = itemPosition;
	}



	public String getMaterialID() {
		return materialID;
	}



	public void setMaterialID(String materialID) {
		this.materialID = materialID;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getAmountOrdered() {
		return amountOrdered;
	}



	public void setAmountOrdered(int amountOrdered) {
		this.amountOrdered = amountOrdered;
	}



	public String getStockDescription() {
		return stockDescription;
	}



	public void setStockDescription(String stockDescription) {
		this.stockDescription = stockDescription;
	}



	
	
}
