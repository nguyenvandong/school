package com.school.example.service.impl;

import com.school.example.service.AttendanceService;
import com.school.example.domain.Attendance;
import com.school.example.repository.AttendanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Attendance.
 */
@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private final Logger log = LoggerFactory.getLogger(AttendanceServiceImpl.class);

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    /**
     * Save a attendance.
     *
     * @param attendance the entity to save
     * @return the persisted entity
     */
    @Override
    public Attendance save(Attendance attendance) {
        log.debug("Request to save Attendance : {}", attendance);
        return attendanceRepository.save(attendance);
    }

    /**
     * Get all the attendances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Attendance> findAll(Pageable pageable) {
        log.debug("Request to get all Attendances");
        return attendanceRepository.findAll(pageable);
    }

    /**
     * Get one attendance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Attendance findOne(Long id) {
        log.debug("Request to get Attendance : {}", id);
        return attendanceRepository.findOne(id);
    }

    /**
     * Delete the attendance by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Attendance : {}", id);
        attendanceRepository.delete(id);
    }
}
