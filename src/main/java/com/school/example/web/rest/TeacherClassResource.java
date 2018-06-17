package com.school.example.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.school.example.domain.TeacherClass;
import com.school.example.service.TeacherClassService;
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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TeacherClass.
 */
@RestController
@RequestMapping("/api")
public class TeacherClassResource {

    private final Logger log = LoggerFactory.getLogger(TeacherClassResource.class);

    private static final String ENTITY_NAME = "teacherClass";

    private final TeacherClassService teacherClassService;

    public TeacherClassResource(TeacherClassService teacherClassService) {
        this.teacherClassService = teacherClassService;
    }

    /**
     * POST  /teacher-classes : Create a new teacherClass.
     *
     * @param teacherClass the teacherClass to create
     * @return the ResponseEntity with status 201 (Created) and with body the new teacherClass, or with status 400 (Bad Request) if the teacherClass has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/teacher-classes")
    @Timed
    public ResponseEntity<TeacherClass> createTeacherClass(@RequestBody TeacherClass teacherClass) throws URISyntaxException {
        log.debug("REST request to save TeacherClass : {}", teacherClass);
        if (teacherClass.getId() != null) {
            throw new BadRequestAlertException("A new teacherClass cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TeacherClass result = teacherClassService.save(teacherClass);
        return ResponseEntity.created(new URI("/api/teacher-classes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /teacher-classes : Updates an existing teacherClass.
     *
     * @param teacherClass the teacherClass to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated teacherClass,
     * or with status 400 (Bad Request) if the teacherClass is not valid,
     * or with status 500 (Internal Server Error) if the teacherClass couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/teacher-classes")
    @Timed
    public ResponseEntity<TeacherClass> updateTeacherClass(@RequestBody TeacherClass teacherClass) throws URISyntaxException {
        log.debug("REST request to update TeacherClass : {}", teacherClass);
        if (teacherClass.getId() == null) {
            return createTeacherClass(teacherClass);
        }
        TeacherClass result = teacherClassService.save(teacherClass);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, teacherClass.getId().toString()))
            .body(result);
    }

    /**
     * GET  /teacher-classes : get all the teacherClasses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of teacherClasses in body
     */
    @GetMapping("/teacher-classes")
    @Timed
    public ResponseEntity<List<TeacherClass>> getAllTeacherClasses(Pageable pageable) {
        log.debug("REST request to get a page of TeacherClasses");
        Page<TeacherClass> page = teacherClassService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/teacher-classes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /teacher-classes/:id : get the "id" teacherClass.
     *
     * @param id the id of the teacherClass to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the teacherClass, or with status 404 (Not Found)
     */
    @GetMapping("/teacher-classes/{id}")
    @Timed
    public ResponseEntity<TeacherClass> getTeacherClass(@PathVariable Long id) {
        log.debug("REST request to get TeacherClass : {}", id);
        TeacherClass teacherClass = teacherClassService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(teacherClass));
    }

    /**
     * DELETE  /teacher-classes/:id : delete the "id" teacherClass.
     *
     * @param id the id of the teacherClass to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/teacher-classes/{id}")
    @Timed
    public ResponseEntity<Void> deleteTeacherClass(@PathVariable Long id) {
        log.debug("REST request to delete TeacherClass : {}", id);
        teacherClassService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
