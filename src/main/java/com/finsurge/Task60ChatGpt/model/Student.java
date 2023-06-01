package com.finsurge.Task60ChatGpt.model;

import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="student_id")
    private Long studentId;
    @Column(name="student_name")
    private String studentName;
    @Column(name="student_dept")
    private String studentDept;
    @Column(name="student_clg")
    private String studentClg;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentDept() {
        return studentDept;
    }

    public void setStudentDept(String studentDept) {
        this.studentDept = studentDept;
    }

    public String getStudentClg() {
        return studentClg;
    }

    public void setStudentClg(String studentClg) {
        this.studentClg = studentClg;
    }

    public Student(Long studentId, String studentName, String studentDept, String studentClg) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentDept = studentDept;
        this.studentClg = studentClg;
    }

    public Student() {
    }

}
