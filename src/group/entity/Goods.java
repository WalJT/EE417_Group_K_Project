package group.entity;

public class Goods {
	private int gid;
	private String gname;
	private double price;
	private int amount;
	public Goods() {
		super();
	}
	public Goods(int gid, String gname, double price, int amount) {
		super();
		this.gid = gid;
		this.gname = gname;
		this.price = price;
		this.amount = amount;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	

}
