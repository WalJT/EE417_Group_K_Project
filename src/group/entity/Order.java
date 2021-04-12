package group.entity;

public class Order {
	private String orderId;
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String psd;
	private String phone;
	private String address;
	private String city;
	private int postalCode;
	public Order() {
		super();
	}
	public Order(String orderId, int id, String firstName, String lastName, String email, String psd, String phone,
			String address, String city, int postalCode) {
		super();
		this.orderId = orderId;
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.psd = psd;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPsd() {
		return psd;
	}
	public void setPsd(String psd) {
		this.psd = psd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	
	
}
