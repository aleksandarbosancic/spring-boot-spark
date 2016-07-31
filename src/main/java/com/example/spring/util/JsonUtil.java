/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.spring.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author aleksandar
 */
@Component
public class JsonUtil {

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private Gson gson;
    
//    public String dataToJson(Object data) {
//        try {
//            StringWriter sw = new StringWriter();
//            objectMapper.writeValue(sw, data);
//            return sw.toString();
//        } catch (IOException e) {
//            throw new RuntimeException("IOEXception while mapping object (" + data + ") to JSON");
//        }
//    }
    
    public String dataToJson(Object data) {
        return gson.toJson(data);
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

}
