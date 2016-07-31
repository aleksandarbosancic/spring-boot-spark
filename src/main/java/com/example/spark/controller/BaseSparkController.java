/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.spark.controller;

import com.example.spring.util.JsonUtil;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import spark.ResponseTransformer;

/**
 *
 * @author aleksandar
 */
public abstract class BaseSparkController {
    
    @Autowired
    protected JsonUtil jsonUtil;
    
    @PostConstruct
    public abstract void setupRoutes();
    
    protected ResponseTransformer dataToJson = (obj) -> {
        return jsonUtil.dataToJson(obj);
    };
    
}
