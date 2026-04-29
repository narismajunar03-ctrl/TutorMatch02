package com.tutormatch.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class TutorRegistrationRequest {

	private String fname;
	private String lname;
	private String email;
	private String contactNumber;
	private String streetAddress;
	private String barangay;
	private String city;
	private String province;
	private String zipCode;
	private String username;
	private String password;

	private List<MultipartFile> certificate;
	private List<MultipartFile> license;
	private List<MultipartFile> additionalDocs;

	public TutorRegistrationRequest() {

	}

	public List<MultipartFile> getCertificate() {
		return certificate;
	}

	public void setCertificate(final List<MultipartFile> certificate) {
		this.certificate = certificate;
	}

	public List<MultipartFile> getLicense() {
		return license;
	}

	public void setLicense(final List<MultipartFile> license) {
		this.license = license;
	}

	public List<MultipartFile> getAdditionalDocs() {
		return additionalDocs;
	}

	public void setAdditionalDocs(List<MultipartFile> additionalDocs) {
		this.additionalDocs = additionalDocs;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(final String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(final String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(final String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(final String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getBarangay() {
		return barangay;
	}

	public void setBarangay(final String barangay) {
		this.barangay = barangay;
	}

	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(final String province) {
		this.province = province;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(final String zipCode) {
		this.zipCode = zipCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
