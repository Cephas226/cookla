package com.oms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(	name = "plannedCourse")
public class PlannedCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlanCourse;

    @Column(name = "startDate")
    private String startDate;

    @Column (name = "endDate")
    private String endDate;

    @Column (name = "startTime")
    private String startTime;

    @Column (name = "endTime")
    private String endTime;

    @Column (name = "courseFrequency")
    private String courseFrequency;

    @Column (name = "courseMode")
    private String courseMode;

    @Column (name = "courseType")
    private String courseType;

    @Column (name = "remark")
    private String remark;

    @Column (name = "plannedCourseStatus")
    private String plannedCourseStatus="ongoing";

   @ManyToOne
   @JoinColumn(name="idCourse", nullable=false)
   private Course courses;

   @ManyToOne
   @JoinColumn(name="employee_id", nullable=false)
   private Employee employee;

    @ManyToOne
    @JoinColumn(name="room_id", nullable=false)
    //@JsonIgnore
    private Room room;


    @ManyToMany
    Set<Student> students;

    public PlannedCourse(String startDate, String endDate, String startTime, String endTime, String courseFrequency, String courseMode, String courseType, String plannedCourseStatus,String remark, Course courses, Employee employee, Room room, Set<Student> students) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseFrequency = courseFrequency;
        this.courseMode = courseMode;
        this.courseType = courseType;
        this.plannedCourseStatus = plannedCourseStatus;
        this.remark = remark;
        this.courses = courses;
        this.employee = employee;
        this.room = room;
        this.students = students;
    }

    public PlannedCourse() {
    }

    public Long getIdPlanCourse() {
        return idPlanCourse;
    }

    public void setIdPlanCourse(Long idPlanCourse) {
        this.idPlanCourse = idPlanCourse;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCourseFrequency() {
        return courseFrequency;
    }

    public void setCourseFrequency(String courseFrequency) {
        this.courseFrequency = courseFrequency;
    }

    public String getCourseMode() {
        return courseMode;
    }

    public void setCourseMode(String courseMode) {
        this.courseMode = courseMode;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Course getCourses() {
        return courses;
    }

    public void setCourses(Course courses) {
        this.courses = courses;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String getPlannedCourseStatus() {
        return plannedCourseStatus;
    }

    public void setPlannedCourseStatus(String plannedCourseStatus) {
        this.plannedCourseStatus = plannedCourseStatus;
    }
}
