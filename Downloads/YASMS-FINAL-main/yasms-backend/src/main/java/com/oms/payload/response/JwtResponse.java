package com.oms.payload.response;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String firstName;
  private String lastName;
  private char gender;
  private String email;

  private List<String> roles;

/*  public JwtResponse(String accessToken, Long id, String firstName, String email,String lastName, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.roles = roles;
  }*/

  public JwtResponse(String token,Long id, String firstName, String lastName,char gender, String email, List<String> roles) {
    this.token = token;
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.email = email;
    this.roles = roles;
  }

    public String getAccessToken() {
      return token;
    }

    public void setAccessToken(String accessToken) {
      this.token = accessToken;
    }

    public String getTokenType() {
      return type;
    }

    public void setTokenType(String tokenType) {
      this.type = tokenType;
    }
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  public String getFirstName() {
    return firstName;
  }

  public char getGender() {
    return gender;
  }

  public void setGender(char gender) {
    this.gender = gender;
  }

  public void setFirstName(String username) {
    this.firstName = firstName;
  }

  public String getLastName() { return lastName; }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<String> getRoles() {
    return roles;
  }

  @Override
  public String toString() {
    return "JwtResponse{" +
      "token='" + token + '\'' +
      ", type='" + type + '\'' +
      ", id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", email='" + email + '\'' +
      ", roles=" + roles +
      '}';
  }
}
