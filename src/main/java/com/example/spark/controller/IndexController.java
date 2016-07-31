/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.spark.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import static spark.Spark.*;

/**
 *
 * @author aleksandar
 */
@Component
public class IndexController extends BaseSparkController {
    
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    
    @Override
    public void setupRoutes() {
        logger.info("Init index controller...");
        get("/", (req, res) -> "Hello World Spark!");
        get("/hello", (req, res) -> "Hello World Aleksa");
        get("/users/:name", (request, response) -> "Selected user: " + request.params(":name"));
    }

}