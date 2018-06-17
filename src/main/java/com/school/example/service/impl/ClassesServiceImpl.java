package com.school.example.service.impl;

import com.school.example.service.ClassesService;
import com.school.example.domain.Classes;
import com.school.example.repository.ClassesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Classes.
 */
@Service
@Transactional
public class ClassesServiceImpl implements ClassesService {

    private final Logger log = LoggerFactory.getLogger(ClassesServiceImpl.class);

    private final ClassesRepository classesRepository;

    public ClassesServiceImpl(ClassesRepository classesRepository) {
        this.classesRepository = classesRepository;
    }

    /**
     * Save a classes.
     *
     * @param classes the entity to save
     * @return the persisted entity
     */
    @Override
    public Classes save(Classes classes) {
        log.debug("Request to save Classes : {}", classes);
        return classesRepository.save(classes);
    }

    /**
     * Get all the classes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Classes> findAll(Pageable pageable) {
        log.debug("Request to get all Classes");
        return classesRepository.findAll(pageable);
    }

    /**
     * Get one classes by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Classes findOne(Long id) {
        log.debug("Request to get Classes : {}", id);
        return classesRepository.findOne(id);
    }

    /**
     * Delete the classes by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Classes : {}", id);
        classesRepository.delete(id);
    }
}
