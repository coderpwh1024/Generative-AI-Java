package com.coderpwh.controller;

import com.coderpwh.service.CalculatorService;
import org.springframework.web.bind.annotation.RestController;

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

}
