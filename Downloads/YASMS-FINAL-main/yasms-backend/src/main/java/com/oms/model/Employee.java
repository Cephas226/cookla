package com.oms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "employees",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
  }
)
@Component
public class Employee {

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

	@Column(name="birthday")
	private String birthday;

	@Column(name="business_phone", unique=true)
	private String businessPhone;

	@Column(name="private_phone", unique=true)
	private String privatePhone;

	@Column(name="email", unique=true)
	private String email;

	@Column(name="nationality")
	private String nationality;

	@Column(name="status")
	private Boolean status;

	@Column(name="linkedinLink")
	private String linkedinLink;

	@Column(name="facebookLink")
	private String facebookLink;

	@Column(name="youtubeLink")
	private String youtubeLink;

	@Column(name="xingLink")
	private String xingLink;

	/*Address*/

	@Column(name="address")
	private String address;

	@Column(name="city")
	private String city;

	@Column(name="country")
	private String country;

	/*Functional Data*/


	@Column(name="department")
	private String department;

	@Column(name="job_role")
	private String jobRole;

	@Column(name="jobType")
	private String jobType;

	@Column(name="work_location")
	private String workLocation;


	@Column(name="password")
	private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(	name = "employee_roles",
    joinColumns = @JoinColumn(name = "employee_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "employee")
  private Set<Historique> historique = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "employee_emmergency",
			joinColumns = @JoinColumn(name = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "emmergency_id"))
	private Set<EmergencyContact> emergencyContacts = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "employee_skill",
			joinColumns = @JoinColumn(name = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private Set<Skill> skills = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "employee_institution",
			joinColumns = @JoinColumn(name = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "institution_id"))
	private Set<Institution> institutions = new HashSet<>();

	@OneToMany(mappedBy="employee")
	@JsonIgnore
	private Set<PlannedCourse> plannedCourse;

	public Employee() {
		super();
	}

	public Employee(String lastName, String firstName, char gender, String birthday, String businessPhone, String privatePhone, String email, String nationality, Boolean status, String linkedinLink, String facebookLink, String youtubeLink, String xingLink, String address, String city, String country, String department, String jobRole, String jobType, String workLocation, String password) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.gender = gender;
		this.birthday = birthday;
		this.businessPhone = businessPhone;
		this.privatePhone = privatePhone;
		this.email = email;
		this.nationality = nationality;
		this.status = status;
		this.linkedinLink = linkedinLink;
		this.facebookLink = facebookLink;
		this.youtubeLink = youtubeLink;
		this.xingLink = xingLink;
		this.address = address;
		this.city = city;
		this.country = country;
		this.department = department;
		this.jobRole = jobRole;
		this.jobType = jobType;
		this.workLocation = workLocation;
		this.password = password;
	}

	public Set<PlannedCourse> getPlannedCourse() {
		return plannedCourse;
	}

	public void setPlannedCourse(Set<PlannedCourse> plannedCourse) {
		this.plannedCourse = plannedCourse;
	}

	public Set<Institution> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(Set<Institution> institutions) {
		this.institutions = institutions;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public Long getId() {
		return id;
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


	public String getJobRole() {
		return jobRole;
	}


	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getBusinessPhone() {
		return businessPhone;
	}


	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}


	public String getPrivatePhone() {
		return privatePhone;
	}


	public void setPrivatePhone(String privatePhone) {
		this.privatePhone = privatePhone;
	}


	public String getWorkLocation() {
		return workLocation;
	}


	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public Set<Historique> getHistorique() {
    return historique;
  }

  public void setHistorique(Set<Historique> historique) {
    this.historique = historique;
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

	public String getLinkedinLink() {
		return linkedinLink;
	}

	public void setLinkedinLink(String linkedinLink) {
		this.linkedinLink = linkedinLink;
	}

	public String getFacebookLink() {
		return facebookLink;
	}

	public void setFacebookLink(String facebookLink) {
		this.facebookLink = facebookLink;
	}

	public String getYoutubeLink() {
		return youtubeLink;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}

	public String getXingLink() {
		return xingLink;
	}

	public void setXingLink(String xingLink) {
		this.xingLink = xingLink;
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

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public Set<EmergencyContact> getEmergencyContacts() {
		return emergencyContacts;
	}

	public void setEmergencyContacts(Set<EmergencyContact> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}

}
