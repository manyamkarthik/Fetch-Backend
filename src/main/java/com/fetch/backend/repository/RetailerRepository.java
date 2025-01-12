package com.fetch.backend.repository;

import com.fetch.backend.entity.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailerRepository extends JpaRepository<Retailer, Long> {

    @Query("SELECT r.points FROM Retailer r WHERE r.response = ?1")
    Integer getPointsByResponse(String id);
}
