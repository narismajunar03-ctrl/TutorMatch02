package com.tutormatch.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "tutors")
public class TutorModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name= "fname")
	private String fname;

	@Column(name= "lname")
	private String lname;

	@Column(name= "email", unique = true)
	private String email;

	@Column(name= "contact_number")
	private String contactNumber;

	@Column(name= "street_address")
	private String streetAddress;

	@Column(name= "barangay")
	private String barangay;

	@Column(name= "city")
	private String city;

	@Column(name= "province")
	private String province;

	@Column(name= "zip_code")
	private String zipCode;	

	@Column(name= "username", unique = true)
	private String username;

	@Column(name= "password")
	private String password;
	
	@OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DocumentModel> documents = new ArrayList<>();
	
	public TutorModel() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getBarangay() {
		return barangay;
	}

	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<DocumentModel> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentModel> documents) {
		this.documents = documents;
	}
	
	

}
