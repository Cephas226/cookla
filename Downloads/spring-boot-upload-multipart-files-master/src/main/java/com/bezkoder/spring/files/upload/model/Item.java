package com.bezkoder.spring.files.upload.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Item {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private String name;

  private String categorie;

  private String type;

  @Lob
  private byte[] data;

  public Item() {
  }

  public Item(String name, String type, byte[] data) {
    this.name = name;
    this.type = type;
    this.data = data;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

}
