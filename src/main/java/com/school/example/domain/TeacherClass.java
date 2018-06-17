package com.school.example.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TeacherClass.
 */
@Entity
@Table(name = "teacher_class")
public class TeacherClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "class_id")
    private Long classId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public TeacherClass teacherId(Long teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getClassId() {
        return classId;
    }

    public TeacherClass classId(Long classId) {
        this.classId = classId;
        return this;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeacherClass teacherClass = (TeacherClass) o;
        if (teacherClass.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teacherClass.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TeacherClass{" +
            "id=" + getId() +
            ", teacherId=" + getTeacherId() +
            ", classId=" + getClassId() +
            "}";
    }
}
