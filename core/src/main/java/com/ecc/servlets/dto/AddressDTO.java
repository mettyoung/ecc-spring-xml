package com.ecc.servlets.dto;

public class AddressDTO {
	private String streetNumber;
	private Integer barangay;
	private String municipality;
	private Integer zipCode;
		
	public String getStreetNumber() {
		return streetNumber;
	}

	public Integer getBarangay() {
		return barangay;
	}

	public String getMunicipality() {
		return municipality;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public void setBarangay(Integer barangay) {
		this.barangay = barangay;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return String.format("%s, Barangay %d, %s, %d", streetNumber, barangay, municipality, zipCode);
	}
}