/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.spark.controller;

import com.example.spark.spring.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.*;

/**
 *
 * @author aleksandar
 */
@Component
public class CustomerController extends BaseSparkController {
    
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    
    @Autowired
    CustomerRepository customerRepository;
    
    @Override
    public void setupRoutes() {
        
        logger.info("Init customer controller...");
        
        get("/customers", "application/json", fetchAllCustomers, dataToJson);
        
        get("/customer/:id", "application/json", fetchOneCustomer, dataToJson);
        
        get("/cus/:id", testCustomer);
        
    }
    
    public Route fetchAllCustomers = (Request request, Response response) -> {
        return customerRepository.findAll();
    };

    public Route fetchOneCustomer = (request, response) -> {
        Long id = Long.parseLong(request.params(":id"));
        return customerRepository.findOne(id);
    };
    
    public Route testCustomer = (request, response) -> {
        return "Param: " + request.queryParams(":id");
    };

}