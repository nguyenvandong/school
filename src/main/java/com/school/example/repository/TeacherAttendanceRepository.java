package com.school.example.repository;

import com.school.example.domain.TeacherAttendance;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TeacherAttendance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeacherAttendanceRepository extends JpaRepository<TeacherAttendance, Long> {

}
