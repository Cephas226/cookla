package com.oms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(	name = "courses")
public class Course {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long IdCourses;

    @Column (name = "courseName")
    private String courseName;

    @Column (name = "courseLevel")
    private String courseLevel;

    @Column (name = "courseUnit")
    private String courseUnit;

    @Column (name = "coursePrice")
    private Long coursePrice;

    @Column (name = "courseStatus")
    private Boolean courseStatus=true;

    @Lob @Column(columnDefinition = "text")
    private String courseComment;

    @Lob @Column(columnDefinition = "text")
    private String courseDescription;

    @ManyToMany
    @JoinTable(name = "course_student",joinColumns = @JoinColumn(name = "student_id"),inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<Student> student;

   /* @JsonIgnore
    @OneToMany(mappedBy="courses")
    private Set<PlannedCourse> plannedCourse;*/

    @OneToMany(mappedBy="courses")
    @JsonIgnore
    private Set<PlannedCourse> plannedCourse;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "institution_id", referencedColumnName = "id")
    private Institution institution;

    public Course(String courseName, String courseLevel, String courseUnit, Long coursePrice, Boolean courseStatus, String courseComment, String courseDescription, Institution institution) {
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        this.courseUnit = courseUnit;
        this.coursePrice = coursePrice;
        this.courseStatus = courseStatus;
        this.courseComment = courseComment;
        this.courseDescription = courseDescription;
        this.institution = institution;
    }

    public Course() {
    }

    public Long getIdCourses() {
        return IdCourses;
    }

    public void setIdCourses(Long idCourses) {
        IdCourses = idCourses;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public String getCourseUnit() {
        return courseUnit;
    }

    public void setCourseUnit(String courseUnit) {
        this.courseUnit = courseUnit;
    }

    public Long getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Long coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Boolean getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(Boolean courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseComment() {
        return courseComment;
    }

    public void setCourseComment(String courseComment) {
        this.courseComment = courseComment;
    }

    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }

    public Set<PlannedCourse> getPlannedCourse() {
        return plannedCourse;
    }

    public void setPlannedCourse(Set<PlannedCourse> plannedCourse) {
        this.plannedCourse = plannedCourse;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    @Override
    public String toString() {
        return "Course{" +
                "IdCourses=" + IdCourses +
                ", courseName='" + courseName + '\'' +
                ", courseLevel='" + courseLevel + '\'' +
                ", courseUnit='" + courseUnit + '\'' +
                ", coursePrice=" + coursePrice +
                ", courseStatus=" + courseStatus +
                ", courseComment='" + courseComment + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", student=" + student +
                ", plannedCourse=" + plannedCourse +
                ", institution=" + institution +
                '}';
    }
}
