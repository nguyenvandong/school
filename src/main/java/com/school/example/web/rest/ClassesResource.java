package com.school.example.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.school.example.domain.Classes;
import com.school.example.service.ClassesService;
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
 * REST controller for managing Classes.
 */
@RestController
@RequestMapping("/api")
public class ClassesResource {

    private final Logger log = LoggerFactory.getLogger(ClassesResource.class);

    private static final String ENTITY_NAME = "classes";

    private final ClassesService classesService;

    public ClassesResource(ClassesService classesService) {
        this.classesService = classesService;
    }

    /**
     * POST  /classes : Create a new classes.
     *
     * @param classes the classes to create
     * @return the ResponseEntity with status 201 (Created) and with body the new classes, or with status 400 (Bad Request) if the classes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/classes")
    @Timed
    public ResponseEntity<Classes> createClasses(@Valid @RequestBody Classes classes) throws URISyntaxException {
        log.debug("REST request to save Classes : {}", classes);
        if (classes.getId() != null) {
            throw new BadRequestAlertException("A new classes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Classes result = classesService.save(classes);
        return ResponseEntity.created(new URI("/api/classes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /classes : Updates an existing classes.
     *
     * @param classes the classes to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated classes,
     * or with status 400 (Bad Request) if the classes is not valid,
     * or with status 500 (Internal Server Error) if the classes couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/classes")
    @Timed
    public ResponseEntity<Classes> updateClasses(@Valid @RequestBody Classes classes) throws URISyntaxException {
        log.debug("REST request to update Classes : {}", classes);
        if (classes.getId() == null) {
            return createClasses(classes);
        }
        Classes result = classesService.save(classes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, classes.getId().toString()))
            .body(result);
    }

    /**
     * GET  /classes : get all the classes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of classes in body
     */
    @GetMapping("/classes")
    @Timed
    public ResponseEntity<List<Classes>> getAllClasses(Pageable pageable) {
        log.debug("REST request to get a page of Classes");
        Page<Classes> page = classesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/classes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /classes/:id : get the "id" classes.
     *
     * @param id the id of the classes to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the classes, or with status 404 (Not Found)
     */
    @GetMapping("/classes/{id}")
    @Timed
    public ResponseEntity<Classes> getClasses(@PathVariable Long id) {
        log.debug("REST request to get Classes : {}", id);
        Classes classes = classesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(classes));
    }

    /**
     * DELETE  /classes/:id : delete the "id" classes.
     *
     * @param id the id of the classes to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/classes/{id}")
    @Timed
    public ResponseEntity<Void> deleteClasses(@PathVariable Long id) {
        log.debug("REST request to delete Classes : {}", id);
        classesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
