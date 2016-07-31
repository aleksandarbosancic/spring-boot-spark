/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.spark.config;

import java.util.Objects;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import static spark.Spark.*;

/**
 *
 * @author aleksandar
 */
@Configuration
public class SparkWebConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(SparkWebConfig.class);
    
    @Value("${ip}")
    private String ip;
    
    @Value("${port}")
    private String port;
    
    @PostConstruct
    public void setupApp() {
        logger.info("Setup Spark routes...");
        setupServer();
        setupFilters();
        logger.info("Spark setup is ready ToGo...");
    }
    
    private void setupServer() {
        staticFiles.location("/public");
        staticFiles.expireTime(600); // ten minutes
        Objects.requireNonNull(ip, "The input String for ip cannot be null");
        Objects.requireNonNull(port, "The input String for port cannot be null");
        ipAddress(ip);
        port(Integer.parseInt(port)); // Spark will run on port 8080
    }

    private void setupFilters() {
        // Set up before-filters (called before each get/post)
        //before("*", Filters.addTrailingSlashes);
        
        //Set up after-filters (called after each get/post)
        after("*",                   Filters.addGzipHeader);
    }
    
}
