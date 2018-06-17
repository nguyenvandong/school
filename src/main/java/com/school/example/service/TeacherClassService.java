package com.school.example.service;

import com.school.example.domain.TeacherClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing TeacherClass.
 */
public interface TeacherClassService {

    /**
     * Save a teacherClass.
     *
     * @param teacherClass the entity to save
     * @return the persisted entity
     */
    TeacherClass save(TeacherClass teacherClass);

    /**
     * Get all the teacherClasses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TeacherClass> findAll(Pageable pageable);

    /**
     * Get the "id" teacherClass.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TeacherClass findOne(Long id);

    /**
     * Delete the "id" teacherClass.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
