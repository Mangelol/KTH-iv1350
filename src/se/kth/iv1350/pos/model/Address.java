package se.kth.iv1350.pos.model;

/**
 * Represents the address info about a specific retail store
 * @author magnu
 *
 */
public class Address {
	private final String storeName;
	private final String street;
	private final String city;
	private final String zipCode;
	private final String country;
	
	public Address() {
		storeName = "Manges livs";
		street = "P Sherman, 42 Wallaby Way";
		city = "Ankeborg";
		zipCode = "123 45";
		country = "Panem";
	}
	
	public String getStoreName() {
		return storeName;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public String getCountry() {
		return country;
	}
}
