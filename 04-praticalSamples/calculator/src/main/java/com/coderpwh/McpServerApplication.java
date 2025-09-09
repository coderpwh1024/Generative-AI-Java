package com.coderpwh;

import com.coderpwh.service.CalculatorService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author coderpwh
 */

@SpringBootApplication
public class McpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }


    /***
     * 注册工具
     * @param calculatorService
     * @return
     */
    @Bean
    public ToolCallbackProvider calculatorTools(CalculatorService calculatorService) {
        return MethodToolCallbackProvider.builder().toolObjects(calculatorService).build();
    }


}
