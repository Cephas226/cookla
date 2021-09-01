package com.oms.payload.request;

import com.oms.model.EmergencyContact;
import com.oms.model.Historique;
import com.oms.model.Institution;
import com.oms.model.Skill;

import java.util.Set;

public class CreationRequest {
  private Long id;
  private String firstName;
  private String lastName;
  private char gender;
  private String birthday;
  private String businessPhone;
  private String privatePhone;
  private String email;
  private String nationality;
  private Boolean status;
  private String linkedinLink;
  private String xingLink;
  private String facebookLink;
  private String youtubeLink;
  private String address;
  private String city;
  private String country;
  private Set<Institution> institution;
  private String jobType;
  private String department;
  private String jobRole;
  private String workLocation;
  private String password;
  private Set<Historique> historiques;
  private Set<EmergencyContact> emergencyContacts;
  private Set<Skill> skills;
  private Set<String> role;

  public CreationRequest(String firstName, String lastName, char gender, String birthday, String businessPhone, String privatePhone, String email, String nationality, Boolean status, String linkedinLink, String xingLink, String facebookLink, String youtubeLink, String address, String city, String country, Set<Institution> institution, String jobType, String department, String jobRole, String workLocation, String password, Set<String> role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.birthday = birthday;
    this.businessPhone = businessPhone;
    this.privatePhone = privatePhone;
    this.email = email;
    this.nationality = nationality;
    this.status = status;
    this.linkedinLink = linkedinLink;
    this.xingLink = xingLink;
    this.facebookLink = facebookLink;
    this.youtubeLink = youtubeLink;
    this.address = address;
    this.city = city;
    this.country = country;
    this.institution = institution;
    this.jobType = jobType;
    this.department = department;
    this.jobRole = jobRole;
    this.workLocation = workLocation;
    this.password = password;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
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

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }

  public CreationRequest() {
  }

  public Set<Skill> getSkills() {
    return skills;
  }

  public void setSkills(Set<Skill> skills) {
    this.skills = skills;
  }

  public Set<EmergencyContact> getEmergencyContacts() {
    return emergencyContacts;
  }

  public void setEmergencyContacts(Set<EmergencyContact> emergencyContacts) {
    this.emergencyContacts = emergencyContacts;
  }

  @Override
  public String toString() {
    return "CreationRequest{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", gender=" + gender +
      ", jobRole='" + jobRole + '\'' +
      ", department='" + department + '\'' +
      ", email='" + email + '\'' +
      ", businessPhone='" + businessPhone + '\'' +
      ", privatePhone='" + privatePhone + '\'' +
      ", workLocation='" + workLocation + '\'' +
      ", status='" + status + '\'' +
      ", password='" + password + '\'' +
      ", historiques=" + historiques +
      ", role=" + role +
      '}';
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

  public String getXingLink() {
    return xingLink;
  }

  public void setXingLink(String xingLink) {
    this.xingLink = xingLink;
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

  public Set<Historique> getHistoriques() {
    return historiques;
  }

  public void setHistoriques(Set<Historique> historiques) {
    this.historiques = historiques;
  }

  public Set<Institution> getInstitution() {
    return institution;
  }

  public void setInstitution(Set<Institution> institution) {
    this.institution = institution;
  }
}
