package com.school.example.service.impl;

import com.school.example.service.ParentsService;
import com.school.example.domain.Parents;
import com.school.example.repository.ParentsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Parents.
 */
@Service
@Transactional
public class ParentsServiceImpl implements ParentsService {

    private final Logger log = LoggerFactory.getLogger(ParentsServiceImpl.class);

    private final ParentsRepository parentsRepository;

    public ParentsServiceImpl(ParentsRepository parentsRepository) {
        this.parentsRepository = parentsRepository;
    }

    /**
     * Save a parents.
     *
     * @param parents the entity to save
     * @return the persisted entity
     */
    @Override
    public Parents save(Parents parents) {
        log.debug("Request to save Parents : {}", parents);
        return parentsRepository.save(parents);
    }

    /**
     * Get all the parents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Parents> findAll(Pageable pageable) {
        log.debug("Request to get all Parents");
        return parentsRepository.findAll(pageable);
    }

    /**
     * Get one parents by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Parents findOne(Long id) {
        log.debug("Request to get Parents : {}", id);
        return parentsRepository.findOne(id);
    }

    /**
     * Delete the parents by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Parents : {}", id);
        parentsRepository.delete(id);
    }
}
