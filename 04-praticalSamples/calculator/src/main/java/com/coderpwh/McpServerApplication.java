package com.coderpwh;

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
        // Start Spring Boot server that will expose calculator tools via MCP
        SpringApplication.run(McpServerApplication.class, args);
    }





}
