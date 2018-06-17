package com.school.example.repository;

import com.school.example.domain.Parents;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Parents entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParentsRepository extends JpaRepository<Parents, Long> {

}
