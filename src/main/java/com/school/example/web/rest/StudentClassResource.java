package com.school.example.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.school.example.domain.StudentClass;
import com.school.example.service.StudentClassService;
import com.school.example.web.rest.util.HeaderUtil;
import com.school.example.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing StudentClass.
 */
@RestController
@RequestMapping("/api")
public class StudentClassResource {

    private final Logger log = LoggerFactory.getLogger(StudentClassResource.class);

    private static final String ENTITY_NAME = "studentClass";

    private final StudentClassService studentClassService;

    public StudentClassResource(StudentClassService studentClassService) {
        this.studentClassService = studentClassService;
    }

    /**
     * POST  /student-classes : Create a new studentClass.
     *
     * @param studentClass the studentClass to create
     * @return the ResponseEntity with status 201 (Created) and with body the new studentClass, or with status 400 (Bad Request) if the studentClass has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/student-classes")
    @Timed
    public ResponseEntity<StudentClass> createStudentClass(@RequestBody StudentClass studentClass) throws URISyntaxException {
        log.debug("REST request to save StudentClass : {}", studentClass);
        StudentClass result = studentClassService.save(studentClass);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getClassId().toString()))
            .body(result);
    }

    /**
     * PUT  /student-classes : Updates an existing studentClass.
     *
     * @param studentClass the studentClass to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated studentClass,
     * or with status 400 (Bad Request) if the studentClass is not valid,
     * or with status 500 (Internal Server Error) if the studentClass couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/student-classes")
    @Timed
    public ResponseEntity<StudentClass> updateStudentClass(@RequestBody StudentClass studentClass) throws URISyntaxException {
        log.debug("REST request to update StudentClass : {}", studentClass);
        if (studentClass.getClassId() == null) {
            return createStudentClass(studentClass);
        }
        StudentClass result = studentClassService.save(studentClass);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, studentClass.getClassId().toString()))
            .body(result);
    }

    /**
     * GET  /student-classes : get all the studentClasses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of studentClasses in body
     */
    @GetMapping("/student-classes")
    @Timed
    public ResponseEntity<List<StudentClass>> getAllStudentClasses(Pageable pageable) {
        log.debug("REST request to get a page of StudentClasses");
        Page<StudentClass> page = studentClassService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/student-classes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /student-classes/:id : get the "id" studentClass.
     *
     * @param id the id of the studentClass to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the studentClass, or with status 404 (Not Found)
     */
    /*@GetMapping("/student-classes/{id}")
    @Timed
    public ResponseEntity<StudentClass> getStudentClass(@PathVariable Long id) {
        log.debug("REST request to get StudentClass : {}", id);
        StudentClass studentClass = studentClassService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(studentClass));
    }*/

    /**
     * DELETE  /student-classes/:classId/:studentId : delete the studentClass.
     * @param classId Class id param
     * @param studentId Student id param
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/student-classes/{classId}/{studentId}")
    @Timed
    public ResponseEntity<Void> deleteStudentClass(@PathVariable Long classId, @PathVariable Long studentId) {
        log.debug("REST request to delete StudentClass : {}", classId);
        studentClassService.delete(classId, studentId);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, classId.toString()
            + "-" + studentId.toString())).build();
    }
}
