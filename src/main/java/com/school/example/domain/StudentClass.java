package com.school.example.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * A StudentClass.
 */
@Entity
@Table(name = "student_class")
@IdClass(StudentClassPK.class)
public class StudentClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "student_id")
    private Long studentId;

    @Id
    @Column(name = "class_id")
    private Long classId;

    public StudentClass() {
    }

    public StudentClass(Long studentId, Long classId) {
        this.studentId = studentId;
        this.classId = classId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
}
