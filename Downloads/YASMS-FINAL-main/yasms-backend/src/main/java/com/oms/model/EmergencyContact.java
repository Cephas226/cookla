package com.oms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "emergencyContact")
@Component
public class EmergencyContact {

	/*Personal Data*/

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="last_name")
	private String lastName;

	@Column(name="first_name")
	private String firstName;

	@Column(name="gender")
	private char gender;

	@Column(name="phone1", unique=true)
	private String phone1;

	@Column(name="phone2", nullable = true)
	private String phone2;

	@Column(name="email", unique=true)
	private String email;

	@Column(name="nature")
	private String nature;

	/*Address*/

	@Column(name="address")
	private String address;


	public EmergencyContact(String lastName, String firstName, char gender, String phone1, String phone2, String email, String address,String nature) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.gender = gender;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.email = email;
		this.address = address;
		this.nature=nature;
	}

	public EmergencyContact() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}
}
