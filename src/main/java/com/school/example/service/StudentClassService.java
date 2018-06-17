package com.school.example.service;

import com.school.example.domain.StudentClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing StudentClass.
 */
public interface StudentClassService {

    /**
     * Save a studentClass.
     *
     * @param studentClass the entity to save
     * @return the persisted entity
     */
    StudentClass save(StudentClass studentClass);

    /**
     * Get all the studentClasses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StudentClass> findAll(Pageable pageable);

    /**
     * Find one entity
     * @param classId
     * @param studentId
     * @return
     */
    StudentClass findOne(Long classId, Long studentId);

    /**
     * Delete entity
     * @param classId
     * @param studentId
     */
    void delete(Long classId, Long studentId);
}
