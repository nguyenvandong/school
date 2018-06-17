package com.school.example.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Teacher.
 */
@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "tid")
    private Long tid;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Size(max = 10)
    @Column(name = "gender", length = 10)
    private String gender;

    @Size(max = 200)
    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_join")
    private LocalDate dateOfJoin;

    @Column(name = "date_of_leaving")
    private LocalDate dateOfLeaving;

    @Size(max = 10)
    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Size(max = 200)
    @Column(name = "qualification", length = 200)
    private String qualification;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTid() {
        return tid;
    }

    public Teacher tid(Long tid) {
        this.tid = tid;
        return this;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getFirstName() {
        return firstName;
    }

    public Teacher firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Teacher middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Teacher lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public Teacher gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public Teacher address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Teacher dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfJoin() {
        return dateOfJoin;
    }

    public Teacher dateOfJoin(LocalDate dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
        return this;
    }

    public void setDateOfJoin(LocalDate dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public LocalDate getDateOfLeaving() {
        return dateOfLeaving;
    }

    public Teacher dateOfLeaving(LocalDate dateOfLeaving) {
        this.dateOfLeaving = dateOfLeaving;
        return this;
    }

    public void setDateOfLeaving(LocalDate dateOfLeaving) {
        this.dateOfLeaving = dateOfLeaving;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Teacher phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQualification() {
        return qualification;
    }

    public Teacher qualification(String qualification) {
        this.qualification = qualification;
        return this;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
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
        Teacher teacher = (Teacher) o;
        if (teacher.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teacher.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Teacher{" +
            "id=" + getId() +
            ", tid=" + getTid() +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", gender='" + getGender() + "'" +
            ", address='" + getAddress() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", dateOfJoin='" + getDateOfJoin() + "'" +
            ", dateOfLeaving='" + getDateOfLeaving() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", qualification='" + getQualification() + "'" +
            "}";
    }
}
