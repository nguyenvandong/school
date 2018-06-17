package com.school.example.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.school.example.domain.Parents;
import com.school.example.service.ParentsService;
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
 * REST controller for managing Parents.
 */
@RestController
@RequestMapping("/api")
public class ParentsResource {

    private final Logger log = LoggerFactory.getLogger(ParentsResource.class);

    private static final String ENTITY_NAME = "parents";

    private final ParentsService parentsService;

    public ParentsResource(ParentsService parentsService) {
        this.parentsService = parentsService;
    }

    /**
     * POST  /parents : Create a new parents.
     *
     * @param parents the parents to create
     * @return the ResponseEntity with status 201 (Created) and with body the new parents, or with status 400 (Bad Request) if the parents has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/parents")
    @Timed
    public ResponseEntity<Parents> createParents(@Valid @RequestBody Parents parents) throws URISyntaxException {
        log.debug("REST request to save Parents : {}", parents);
        if (parents.getId() != null) {
            throw new BadRequestAlertException("A new parents cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Parents result = parentsService.save(parents);
        return ResponseEntity.created(new URI("/api/parents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /parents : Updates an existing parents.
     *
     * @param parents the parents to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated parents,
     * or with status 400 (Bad Request) if the parents is not valid,
     * or with status 500 (Internal Server Error) if the parents couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/parents")
    @Timed
    public ResponseEntity<Parents> updateParents(@Valid @RequestBody Parents parents) throws URISyntaxException {
        log.debug("REST request to update Parents : {}", parents);
        if (parents.getId() == null) {
            return createParents(parents);
        }
        Parents result = parentsService.save(parents);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, parents.getId().toString()))
            .body(result);
    }

    /**
     * GET  /parents : get all the parents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of parents in body
     */
    @GetMapping("/parents")
    @Timed
    public ResponseEntity<List<Parents>> getAllParents(Pageable pageable) {
        log.debug("REST request to get a page of Parents");
        Page<Parents> page = parentsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/parents");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /parents/:id : get the "id" parents.
     *
     * @param id the id of the parents to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the parents, or with status 404 (Not Found)
     */
    @GetMapping("/parents/{id}")
    @Timed
    public ResponseEntity<Parents> getParents(@PathVariable Long id) {
        log.debug("REST request to get Parents : {}", id);
        Parents parents = parentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(parents));
    }

    /**
     * DELETE  /parents/:id : delete the "id" parents.
     *
     * @param id the id of the parents to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/parents/{id}")
    @Timed
    public ResponseEntity<Void> deleteParents(@PathVariable Long id) {
        log.debug("REST request to delete Parents : {}", id);
        parentsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
