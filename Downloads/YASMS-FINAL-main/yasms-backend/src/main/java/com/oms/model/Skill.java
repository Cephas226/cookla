package com.oms.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(	name = "skill")
@Component
public class Skill {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="skillName")
	private String skillName;

	public Skill(String skillName) {
		this.skillName = skillName;
	}

	public Skill() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
}
