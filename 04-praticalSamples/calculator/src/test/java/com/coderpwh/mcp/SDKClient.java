package com.coderpwh.mcp;

import io.modelcontextprotocol.client.transport.WebFluxSseClientTransport;
import io.modelcontextprotocol.spec.McpClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.web.reactive.function.client.WebClient;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.WebFluxSseClientTransport;
import io.modelcontextprotocol.spec.McpClientTransport;
import io.modelcontextprotocol.spec.McpSchema.CallToolRequest;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;

import java.util.Map;

public class SDKClient {

    public static void main(String[] args) {

        var transport = new WebFluxSseClientTransport(WebClient.builder().baseUrl("http://localhost:8080"));
        new SDKClient(transport).run();
    }


    private final McpClientTransport mcpClientTransport;


    public SDKClient(McpClientTransport mcpClientTransport) {
        this.mcpClientTransport = mcpClientTransport;
    }

    public void run() {
        //  创建MCP的客户端连接
        var client = McpClient.sync(this.mcpClientTransport).build();

        // 初始化MCP
        client.initialize();

        // 校验连接
        client.ping();

        // 链接服务懂，获取服务
        McpSchema.ListToolsResult toolsList = client.listTools();
        System.out.println("工具信息为:" + toolsList);

        System.out.println("----------------------------------------------------------------------------------------------------------------");


        CallToolResult resultAdd = client.callTool(new CallToolRequest("add", Map.of("a", 5.0, "b", 3.0)));
        System.out.println("加法运算结果为:" + resultAdd);

        System.out.println("----------------------------------------------------------------------------------------------------------------");

        CallToolResult resultSubtract = client.callTool(new CallToolRequest("subtract", Map.of("a", 10.0, "b", 4.0)));
        System.out.println("减法运算结果为:" + resultSubtract);

        System.out.println("----------------------------------------------------------------------------------------------------------------");

        CallToolResult resultMultiply = client.callTool(new CallToolRequest("multiply", Map.of("a", 6.0, "b", 7.0)));
        System.out.println("乘法运算的结果为:" + resultMultiply);

        System.out.println("----------------------------------------------------------------------------------------------------------------");

        CallToolResult resultDivide = client.callTool(new CallToolRequest("divide", Map.of("a", 20.0, "b", 4.0)));
        System.out.println("乘法运算的结果为:" + resultDivide);

        System.out.println("----------------------------------------------------------------------------------------------------------------");
        CallToolResult resultPower = client.callTool(new CallToolRequest("power", Map.of("base", 2.0, "exponent", 8.0)));
        System.out.println("幂运算的结果为:" + resultPower);


        System.out.println("----------------------------------------------------------------------------------------------------------------");

        CallToolResult resultSqrt = client.callTool(new CallToolRequest("squareRoot", Map.of("number", 16.0)));
        System.out.println("平方根运算的结果为:" + resultSqrt);



        System.out.println("----------------------------------------------------------------------------------------------------------------");
        CallToolResult resultAbsolute = client.callTool(new CallToolRequest("absolute", Map.of("number", -5.5)));
        System.out.println("绝对值运算的结果为:" + resultAbsolute);



        System.out.println("----------------------------------------------------------------------------------------------------------------");
        // 获取帮助类-工具
        CallToolResult resultHelp = client.callTool(new CallToolRequest("help", Map.of()));
        System.out.println("计算工具帮助类:" + resultHelp);


        System.out.println("----------------------------------------------------------------------------------------------------------------");
        // 清理连接
        client.closeGracefully();

        //t退出
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.exit(0);
    }


}
