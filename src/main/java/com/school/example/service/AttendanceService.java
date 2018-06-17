package com.school.example.service;

import com.school.example.domain.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Attendance.
 */
public interface AttendanceService {

    /**
     * Save a attendance.
     *
     * @param attendance the entity to save
     * @return the persisted entity
     */
    Attendance save(Attendance attendance);

    /**
     * Get all the attendances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Attendance> findAll(Pageable pageable);

    /**
     * Get the "id" attendance.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Attendance findOne(Long id);

    /**
     * Delete the "id" attendance.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
