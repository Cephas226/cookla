package com.bezkoder.spring.files.upload.model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;
  private String categorie;
  private Long note;
  private Long vues;
  @Lob
  private byte[] data;

  public Product( String categorie, Long note, Long vues, byte[] data) {
    this.categorie = categorie;
    this.note = note;
    this.vues = vues;
    this.data = data;
  }

  public Product() {
  }

  public Long getNote() {
    return note;
  }

  public void setNote(Long note) {
    this.note = note;
  }

  public Long getVues() {
    return vues;
  }

  public void setVues(Long vues) {
    this.vues = vues;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getCategorie() {
    return categorie;
  }

  public void setCategorie(String categorie) {
    this.categorie = categorie;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "Product{" +
            "productId=" + productId +
            ", categorie='" + categorie + '\'' +
            ", note=" + note +
            ", vues=" + vues +
            ", data=" + Arrays.toString(data) +
            '}';
  }
}
