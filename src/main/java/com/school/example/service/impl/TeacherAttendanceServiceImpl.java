package com.school.example.service.impl;

import com.school.example.service.TeacherAttendanceService;
import com.school.example.domain.TeacherAttendance;
import com.school.example.repository.TeacherAttendanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing TeacherAttendance.
 */
@Service
@Transactional
public class TeacherAttendanceServiceImpl implements TeacherAttendanceService {

    private final Logger log = LoggerFactory.getLogger(TeacherAttendanceServiceImpl.class);

    private final TeacherAttendanceRepository teacherAttendanceRepository;

    public TeacherAttendanceServiceImpl(TeacherAttendanceRepository teacherAttendanceRepository) {
        this.teacherAttendanceRepository = teacherAttendanceRepository;
    }

    /**
     * Save a teacherAttendance.
     *
     * @param teacherAttendance the entity to save
     * @return the persisted entity
     */
    @Override
    public TeacherAttendance save(TeacherAttendance teacherAttendance) {
        log.debug("Request to save TeacherAttendance : {}", teacherAttendance);
        return teacherAttendanceRepository.save(teacherAttendance);
    }

    /**
     * Get all the teacherAttendances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TeacherAttendance> findAll(Pageable pageable) {
        log.debug("Request to get all TeacherAttendances");
        return teacherAttendanceRepository.findAll(pageable);
    }

    /**
     * Get one teacherAttendance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TeacherAttendance findOne(Long id) {
        log.debug("Request to get TeacherAttendance : {}", id);
        return teacherAttendanceRepository.findOne(id);
    }

    /**
     * Delete the teacherAttendance by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TeacherAttendance : {}", id);
        teacherAttendanceRepository.delete(id);
    }
}
