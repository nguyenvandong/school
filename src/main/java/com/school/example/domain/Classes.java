package com.school.example.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Classes.
 */
@Entity
@Table(name = "classes")
public class Classes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "num_of_student")
    private Integer numOfStudent;

    @Size(max = 200)
    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @Size(max = 200)
    @Column(name = "location", length = 200)
    private String location;

    @Column(name = "s_date")
    private LocalDate sDate;

    @Column(name = "e_date")
    private LocalDate eDate;


    @ManyToMany(mappedBy = "classes", fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumOfStudent() {
        return numOfStudent;
    }

    public Classes numOfStudent(Integer numOfStudent) {
        this.numOfStudent = numOfStudent;
        return this;
    }

    public void setNumOfStudent(Integer numOfStudent) {
        this.numOfStudent = numOfStudent;
    }

    public String getName() {
        return name;
    }

    public Classes name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Classes capacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public Classes location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getsDate() {
        return sDate;
    }

    public Classes sDate(LocalDate sDate) {
        this.sDate = sDate;
        return this;
    }

    public void setsDate(LocalDate sDate) {
        this.sDate = sDate;
    }

    public LocalDate geteDate() {
        return eDate;
    }

    public Classes eDate(LocalDate eDate) {
        this.eDate = eDate;
        return this;
    }

    public void seteDate(LocalDate eDate) {
        this.eDate = eDate;
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
        Classes classes = (Classes) o;
        if (classes.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), classes.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Classes{" +
            "id=" + getId() +
            ", numOfStudent=" + getNumOfStudent() +
            ", name='" + getName() + "'" +
            ", capacity=" + getCapacity() +
            ", location='" + getLocation() + "'" +
            ", sDate='" + getsDate() + "'" +
            ", eDate='" + geteDate() + "'" +
            "}";
    }
}
