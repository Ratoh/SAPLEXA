package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Order {

	private String orderID;

	private String distributorID;

	private Date orderDate = null;

	private Collection<Item> itemlist = new ArrayList<>();

	public Order() {

	}

	public Collection<Item> getItemlist() {
		return itemlist;
	}

	public void setItemlist(Collection<Item> itemlist) {
		this.itemlist = itemlist;
	}
	
	public void setItemOpenPosition(ArrayList<Integer> openPositions) {
		int idx = 0;
		for (Item i :this.getItemlist()) {
			i.setAmountToBeDelivered(openPositions.get(idx));	
			idx++;
		}
	}

	public Order(String o, String dID, Date oDate) {
		this.orderID = o;
		this.distributorID = dID;
		this.orderDate = oDate;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getDistributorID() {
		return distributorID;
	}

	public void setDistributorID(String distributorID) {
		this.distributorID = distributorID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public void clear() {
		this.distributorID = null;
		this.orderDate = null;
		this.orderID = null;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", distributorID=" + distributorID + ", orderDate=" + orderDate + "]\n";
	}

}
