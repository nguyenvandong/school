package com.school.example.repository;

import com.school.example.domain.Classes;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Classes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {

}
