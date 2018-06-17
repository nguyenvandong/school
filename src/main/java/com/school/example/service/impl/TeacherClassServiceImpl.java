package com.school.example.service.impl;

import com.school.example.service.TeacherClassService;
import com.school.example.domain.TeacherClass;
import com.school.example.repository.TeacherClassRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing TeacherClass.
 */
@Service
@Transactional
public class TeacherClassServiceImpl implements TeacherClassService {

    private final Logger log = LoggerFactory.getLogger(TeacherClassServiceImpl.class);

    private final TeacherClassRepository teacherClassRepository;

    public TeacherClassServiceImpl(TeacherClassRepository teacherClassRepository) {
        this.teacherClassRepository = teacherClassRepository;
    }

    /**
     * Save a teacherClass.
     *
     * @param teacherClass the entity to save
     * @return the persisted entity
     */
    @Override
    public TeacherClass save(TeacherClass teacherClass) {
        log.debug("Request to save TeacherClass : {}", teacherClass);
        return teacherClassRepository.save(teacherClass);
    }

    /**
     * Get all the teacherClasses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TeacherClass> findAll(Pageable pageable) {
        log.debug("Request to get all TeacherClasses");
        return teacherClassRepository.findAll(pageable);
    }

    /**
     * Get one teacherClass by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TeacherClass findOne(Long id) {
        log.debug("Request to get TeacherClass : {}", id);
        return teacherClassRepository.findOne(id);
    }

    /**
     * Delete the teacherClass by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TeacherClass : {}", id);
        teacherClassRepository.delete(id);
    }
}
