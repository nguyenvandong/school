package com.school.example.repository;

import com.school.example.domain.StudentClass;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the StudentClass entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {

}
