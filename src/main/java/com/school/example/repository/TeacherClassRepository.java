package com.school.example.repository;

import com.school.example.domain.TeacherClass;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TeacherClass entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeacherClassRepository extends JpaRepository<TeacherClass, Long> {

}
