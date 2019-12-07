package javaConnector;


public class Item {

	private String itemID; 
	
	private String description;
	
	private int amount;
	
	
	
	public Item() {
		
	}



	public String getItemID() {
		return itemID;
	}



	public void setItemID(String itemID) {
		this.itemID = itemID;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	@Override
	public String toString() {
		return "Item [itemID=" + itemID + ", description=" + description + ", amount=" + amount + "]";
	}
	
	
	
}
