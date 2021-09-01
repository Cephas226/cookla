package com.oms.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(	name = "institution")
@Component
public class Institution {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="institution_name")
	private String institutionName;


	public Institution(String institutionName) {
		this.institutionName = institutionName;
	}

	public Institution() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
}
