package com.oms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(	name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "archived")
    private Boolean archived;

    @OneToMany(mappedBy="room")
    @JsonIgnore
    private Set<PlannedCourse> plannedCourse;

    public Set<PlannedCourse> getPlannedCourse() {
        return plannedCourse;
    }

    public void setPlannedCourse(Set<PlannedCourse> plannedCourse) {
        this.plannedCourse = plannedCourse;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Room(String name, Boolean archived, Set<PlannedCourse> plannedCourse) {
        this.name = name;
        this.archived = archived;
        this.plannedCourse = plannedCourse;
    }

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
