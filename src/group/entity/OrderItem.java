package group.entity;

public class OrderItem {
	public OrderItem() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	private int id;//sequence number of table
	private String orderId;
	private int gid;//goods id
	private String gname;//goods name
	private Double price;
	private int amount;
	
	public OrderItem(int id, String orderId, int gid, String gname, Double price, int amount) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.gid = gid;
		this.gname = gname;
		this.price = price;
		this.amount = amount;
	}
}
