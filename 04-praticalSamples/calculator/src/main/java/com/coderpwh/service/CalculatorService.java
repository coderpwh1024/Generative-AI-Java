package com.coderpwh.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

/**
 * @author coderpwh
 */
@Service
public class CalculatorService {


    /***
     * 加法
     * @param a
     * @param b
     * @return
     */
    @Tool(description = "Add two numbers together.")
     public String add(double a,double b){
         double result = a+b;
         return formatResult(a, "+", b, result);
     }

    @Tool(description = "Subtract the second number from the first number")
     public String subtract(double a,double b){
         double result = a-b;
         return formatResult(a, "-", b, result);
     }


    private String formatResult(double a, String operator, double b, double result) {
        return String.format("%.2f %s %.2f = %.2f", a, operator, b, result);
    }


}
