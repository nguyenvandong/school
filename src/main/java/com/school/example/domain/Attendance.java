package com.school.example.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Attendance.
 */
@Entity
@Table(name = "attendance")
public class Attendance implements Serializable {

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

    public Attendance dateAttendance(LocalDate dateAttendance) {
        this.dateAttendance = dateAttendance;
        return this;
    }

    public void setDateAttendance(LocalDate dateAttendance) {
        this.dateAttendance = dateAttendance;
    }

    public String getReason() {
        return reason;
    }

    public Attendance reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
        Attendance attendance = (Attendance) o;
        if (attendance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attendance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Attendance{" +
            "id=" + getId() +
            ", dateAttendance='" + getDateAttendance() + "'" +
            ", reason='" + getReason() + "'" +
            "}";
    }
}
