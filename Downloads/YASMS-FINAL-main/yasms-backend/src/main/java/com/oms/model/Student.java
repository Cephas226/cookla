package com.oms.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

@Table(name="students")
public class Student {

	/*Personal Data*/

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="code")
	private Long code;

	@Column(name="oldID")
	private Long oldID;

	@Column(name="first_name")
	private String firstName;

	@Column(name="middle_name")
	private String middleName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="gender")
	private char gender;

	@Column(name="birthday")
	private String birthday;

	@Column(name="phone_1", unique=true)
	private String phone1;

	@Column(name="phone_2")
	private String phone2;

	@Column(name="email", unique=true)
	private String email;

	@Column(name="nationality")
	private String nationality;

	@Column(name="identification_type")
	private String identificationType;

	@Column(name="identification_number", unique=true)
	private String identificationNumber;

	/*Address*/

	@Column(name="address")
	private String address;

	@Column(name="city")
	private String city;

	@Column(name="country")
	private String country;

	/*Emergency Contact*/

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "student_emmergency",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "emmergency_id"))
	private Set<EmergencyContact> emergencyContacts = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "student_institution",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "institution_id"))
	private Set<Institution> institutions = new HashSet<>();

	/*Educational Background*/

	@Column(name="profession")
	private String profession;

	@Column(name="school_name")
	private String schoolName;

	@Column(name="school_place")
	private String schoolPlace;

	@Column(name="school_type")
	private String schoolType;

	@Column(name="niveau_etude")
	private String niveauEtude;

	@Column(name="system_etude")
	private String systemEtude;

	@Column(name="serie_bac")
	private String serieBac;

	@Column(name="moyenne_bac")
	private Double moyenneBac;

	/*Institution*/
	@Column(name="course")
	private String course;

    @Column(name="enrollmentDate")
    private String enrollmentDate;

	public Student() {
	}

	public Student(Long oldID, String firstName, String middleName, String lastName, char gender, String birthday, String phone1, String phone2, String email, String nationality, String identificationType, String identificationNumber, String address, String city, String country, String profession, String schoolName, String schoolPlace, String schoolType, String niveauEtude, String systemEtude, String serieBac, Double moyenneBac,String enrollmentDate,Set<Institution>institutions) {
		this.oldID = oldID;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.email = email;
		this.nationality = nationality;
		this.identificationType = identificationType;
		this.identificationNumber = identificationNumber;
		this.address = address;
		this.city = city;
		this.country = country;
		this.profession = profession;
		this.schoolName = schoolName;
		this.schoolPlace = schoolPlace;
		this.schoolType = schoolType;
		this.niveauEtude = niveauEtude;
		this.systemEtude = systemEtude;
		this.serieBac = serieBac;
		this.moyenneBac = moyenneBac;
		//this.course = course;
		this.institutions=institutions;
		this.enrollmentDate = enrollmentDate;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Set<Institution> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(Set<Institution> institutions) {
		this.institutions = institutions;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public Long getOldID() {
		return oldID;
	}

	public void setOldID(Long oldID) {
		this.oldID = oldID;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<EmergencyContact> getEmergencyContacts() {
		return emergencyContacts;
	}

	public void setEmergencyContacts(Set<EmergencyContact> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolPlace() {
		return schoolPlace;
	}

	public void setSchoolPlace(String schoolPlace) {
		this.schoolPlace = schoolPlace;
	}

	public String getNiveauEtude() {
		return niveauEtude;
	}

	public void setNiveauEtude(String niveauEtude) {
		this.niveauEtude = niveauEtude;
	}

	public String getSystemEtude() {
		return systemEtude;
	}

	public void setSystemEtude(String systemEtude) {
		this.systemEtude = systemEtude;
	}

	public String getSerieBac() {
		return serieBac;
	}

	public void setSerieBac(String serieBac) {
		this.serieBac = serieBac;
	}

	public Double getMoyenneBac() {
		return moyenneBac;
	}

	public void setMoyenneBac(Double moyenneBac) {
		this.moyenneBac = moyenneBac;
	}

	public String getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(String enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
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

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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


}
