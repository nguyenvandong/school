package com.school.example.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A TeacherAttendance.
 */
@Entity
@Table(name = "teacher_attendance")
public class TeacherAttendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_attendance")
    private LocalDate dateAttendance;

    @Size(max = 200)
    @Column(name = "reason", length = 200)
    private String reason;

    @ManyToOne
    private Teacher teacherId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateAttendance() {
        return dateAttendance;
    }

    public TeacherAttendance dateAttendance(LocalDate dateAttendance) {
        this.dateAttendance = dateAttendance;
        return this;
    }

    public void setDateAttendance(LocalDate dateAttendance) {
        this.dateAttendance = dateAttendance;
    }

    public String getReason() {
        return reason;
    }

    public TeacherAttendance reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public TeacherAttendance teacherId(Teacher teacher) {
        this.teacherId = teacher;
        return this;
    }

    public void setTeacherId(Teacher teacher) {
        this.teacherId = teacher;
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
        TeacherAttendance teacherAttendance = (TeacherAttendance) o;
        if (teacherAttendance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teacherAttendance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TeacherAttendance{" +
            "id=" + getId() +
            ", dateAttendance='" + getDateAttendance() + "'" +
            ", reason='" + getReason() + "'" +
            "}";
    }
}
