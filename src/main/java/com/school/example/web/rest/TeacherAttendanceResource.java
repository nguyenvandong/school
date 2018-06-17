package com.school.example.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.school.example.domain.TeacherAttendance;
import com.school.example.service.TeacherAttendanceService;
import com.school.example.web.rest.errors.BadRequestAlertException;
import com.school.example.web.rest.util.HeaderUtil;
import com.school.example.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TeacherAttendance.
 */
@RestController
@RequestMapping("/api")
public class TeacherAttendanceResource {

    private final Logger log = LoggerFactory.getLogger(TeacherAttendanceResource.class);

    private static final String ENTITY_NAME = "teacherAttendance";

    private final TeacherAttendanceService teacherAttendanceService;

    public TeacherAttendanceResource(TeacherAttendanceService teacherAttendanceService) {
        this.teacherAttendanceService = teacherAttendanceService;
    }

    /**
     * POST  /teacher-attendances : Create a new teacherAttendance.
     *
     * @param teacherAttendance the teacherAttendance to create
     * @return the ResponseEntity with status 201 (Created) and with body the new teacherAttendance, or with status 400 (Bad Request) if the teacherAttendance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/teacher-attendances")
    @Timed
    public ResponseEntity<TeacherAttendance> createTeacherAttendance(@Valid @RequestBody TeacherAttendance teacherAttendance) throws URISyntaxException {
        log.debug("REST request to save TeacherAttendance : {}", teacherAttendance);
        if (teacherAttendance.getId() != null) {
            throw new BadRequestAlertException("A new teacherAttendance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TeacherAttendance result = teacherAttendanceService.save(teacherAttendance);
        return ResponseEntity.created(new URI("/api/teacher-attendances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /teacher-attendances : Updates an existing teacherAttendance.
     *
     * @param teacherAttendance the teacherAttendance to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated teacherAttendance,
     * or with status 400 (Bad Request) if the teacherAttendance is not valid,
     * or with status 500 (Internal Server Error) if the teacherAttendance couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/teacher-attendances")
    @Timed
    public ResponseEntity<TeacherAttendance> updateTeacherAttendance(@Valid @RequestBody TeacherAttendance teacherAttendance) throws URISyntaxException {
        log.debug("REST request to update TeacherAttendance : {}", teacherAttendance);
        if (teacherAttendance.getId() == null) {
            return createTeacherAttendance(teacherAttendance);
        }
        TeacherAttendance result = teacherAttendanceService.save(teacherAttendance);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, teacherAttendance.getId().toString()))
            .body(result);
    }

    /**
     * GET  /teacher-attendances : get all the teacherAttendances.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of teacherAttendances in body
     */
    @GetMapping("/teacher-attendances")
    @Timed
    public ResponseEntity<List<TeacherAttendance>> getAllTeacherAttendances(Pageable pageable) {
        log.debug("REST request to get a page of TeacherAttendances");
        Page<TeacherAttendance> page = teacherAttendanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/teacher-attendances");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /teacher-attendances/:id : get the "id" teacherAttendance.
     *
     * @param id the id of the teacherAttendance to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the teacherAttendance, or with status 404 (Not Found)
     */
    @GetMapping("/teacher-attendances/{id}")
    @Timed
    public ResponseEntity<TeacherAttendance> getTeacherAttendance(@PathVariable Long id) {
        log.debug("REST request to get TeacherAttendance : {}", id);
        TeacherAttendance teacherAttendance = teacherAttendanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(teacherAttendance));
    }

    /**
     * DELETE  /teacher-attendances/:id : delete the "id" teacherAttendance.
     *
     * @param id the id of the teacherAttendance to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/teacher-attendances/{id}")
    @Timed
    public ResponseEntity<Void> deleteTeacherAttendance(@PathVariable Long id) {
        log.debug("REST request to delete TeacherAttendance : {}", id);
        teacherAttendanceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
