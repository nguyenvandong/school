package com.school.example.service;

import com.school.example.domain.Classes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Classes.
 */
public interface ClassesService {

    /**
     * Save a classes.
     *
     * @param classes the entity to save
     * @return the persisted entity
     */
    Classes save(Classes classes);

    /**
     * Get all the classes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Classes> findAll(Pageable pageable);

    /**
     * Get the "id" classes.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Classes findOne(Long id);

    /**
     * Delete the "id" classes.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
