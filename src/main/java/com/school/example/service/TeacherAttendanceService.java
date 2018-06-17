package com.school.example.service;

import com.school.example.domain.TeacherAttendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing TeacherAttendance.
 */
public interface TeacherAttendanceService {

    /**
     * Save a teacherAttendance.
     *
     * @param teacherAttendance the entity to save
     * @return the persisted entity
     */
    TeacherAttendance save(TeacherAttendance teacherAttendance);

    /**
     * Get all the teacherAttendances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TeacherAttendance> findAll(Pageable pageable);

    /**
     * Get the "id" teacherAttendance.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TeacherAttendance findOne(Long id);

    /**
     * Delete the "id" teacherAttendance.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
