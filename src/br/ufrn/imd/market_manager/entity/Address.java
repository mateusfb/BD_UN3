package br.ufrn.imd.market_manager.entity;

public class Address {
	
	private Long id;
	private String place;
	private String number;
	private String zipcode;
	private String city;
	private String state;
	public Long getId() {
		return id;
	}
	
	public Address() {}
	
	public Address(Long id, String place, String number, String zipcode, String city, String state) {
		super();
		this.id = id;
		this.place = place;
		this.number = number;
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "'" + place + "', '" + number + "', '" + zipcode + "', '" + city + "', '"
				+ state + "'";
	}
}
