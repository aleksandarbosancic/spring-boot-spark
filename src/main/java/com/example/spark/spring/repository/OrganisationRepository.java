/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.spark.spring.repository;

import com.example.spark.spring.domain.Organisation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author aleksandar
 */
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    List<Organisation> findByName(String name);
    
}
