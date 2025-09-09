package com.coderpwh.controller;

import com.coderpwh.service.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author coderpwh
 */
@RestController
public class HealthController {



    private  final CalculatorService calculatorService;


    /***
     * 构造函数
     * @param calculatorService
     */
    public HealthController(CalculatorService calculatorService) {
         this.calculatorService = calculatorService;
     }


    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("service", "Basic Calculator MCP Service");
        response.put("calculatorService", calculatorService != null ? "Available" : "Unavailable");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> serviceInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("service", "Basic Calculator MCP Service");
        response.put("version", "1.0.0");
        response.put("endpoint", "/v1/tools");

        Map<String, String> tools = new HashMap<>();
        tools.put("add", "Add two numbers together");
        tools.put("subtract", "Subtract the second number from the first number");
        tools.put("multiply", "Multiply two numbers together");
        tools.put("divide", "Divide the first number by the second number");
        tools.put("power", "Calculate the power of a number (base raised to an exponent)");
        tools.put("squareRoot", "Calculate the square root of a number");
        tools.put("modulus", "Calculate the remainder when one number is divided by another");
        tools.put("absolute", "Calculate the absolute value of a number");
        tools.put("help", "Get help about available calculator operations");
        response.put("availableTools", tools);

        // Additional helpful information for clients
        response.put("documentation", "See README.md for usage examples");
        response.put("clientExamples", "Check src/test/java for client implementation examples");

        return ResponseEntity.ok(response);
    }



}
