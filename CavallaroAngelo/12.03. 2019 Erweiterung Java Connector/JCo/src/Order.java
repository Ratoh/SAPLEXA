import java.util.Date;

public class Order {

	private String orderID; 
	
	private String distributorID;
	
	private Date orderDate = null;
	
	public Order() {
		
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
