package com.school.example.service;

import com.school.example.domain.Parents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Parents.
 */
public interface ParentsService {

    /**
     * Save a parents.
     *
     * @param parents the entity to save
     * @return the persisted entity
     */
    Parents save(Parents parents);

    /**
     * Get all the parents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Parents> findAll(Pageable pageable);

    /**
     * Get the "id" parents.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Parents findOne(Long id);

    /**
     * Delete the "id" parents.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
