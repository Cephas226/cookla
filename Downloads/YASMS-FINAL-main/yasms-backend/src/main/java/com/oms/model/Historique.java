package com.oms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
@Entity
@Table(name = "historique")
public class Historique {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name="year")
  private String year;

  @Column(name="entitled")
  private String entitled;

  @ManyToOne
  @JoinColumn(name = "employee_id", nullable = false)
  @JsonIgnore
  private Employee employee;

  public Historique(Integer id, String year, String entitled) {
    this.id = id;
    this.year = year;
    this.entitled = entitled;
  }

  public Historique() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getEntitled() {
    return entitled;
  }

  public void setEntitled(String entitled) {
    this.entitled = entitled;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
}
