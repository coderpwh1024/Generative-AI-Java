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
    public String add(double a, double b) {
        double result = a + b;
        return formatResult(a, "+", b, result);
    }


    /***
     * 减法
     * @param a
     * @param b
     * @return
     */
    @Tool(description = "Subtract the second number from the first number")
    public String subtract(double a, double b) {
        double result = a - b;
        return formatResult(a, "-", b, result);
    }


    /**
     * 乘法
     *
     * @param a
     * @param b
     * @return
     */
    @Tool(description = "Multiply two numbers together")
    public String multiply(double a, double b) {
        double result = a * b;
        return formatResult(a, "*", b, result);
    }


    @Tool(description = "Divide the first number by the second number")
    public String divide(double a, double b) {
        // Critical validation: prevent division by zero
        if (b == 0) {
            return "Error: Cannot divide by zero";
        }
        double result = a / b;
        return formatResult(a, "/", b, result);
    }


    /***
     * 幂运算
     * @param base
     * @param exponent
     * @return
     */

    @Tool(description = "Calculate the power of a number (base raised to an exponent)")
    public String power(double base, double exponent) {
        double result = Math.pow(base, exponent);
        return formatResult(base, "^", exponent, result);
    }


    /***
     * 平方根
     * @param number
     * @return
     */
    @Tool(description = "Calculate the square root of a number")
    public String squareRoot(double number) {
        // Validate input: square root of negative numbers isn't real
        if (number < 0) {
            return "Error: Cannot calculate square root of a negative number";
        }
        double result = Math.sqrt(number);
        // Different formatting for single-operand functions
        return String.format("√%.2f = %.2f", number, result);
    }





    private String formatResult(double a, String operator, double b, double result) {
        return String.format("%.2f %s %.2f = %.2f", a, operator, b, result);
    }


    /***
     * 取余数
     * @param a
     * @param b
     * @return
     */
    @Tool(description = "Calculate the remainder when one number is divided by another")
    public String modulus(double a, double b) {
        if (b == 0) {
            return "Error: Cannot divide by zero";
        }
        double result = a % b;
        return formatResult(a, "%", b, result);
    }


    /***
     *  绝对值
     * @param number
     * @return
     */
    @Tool(description = "Calculate the absolute value of a number")
    public String absolute(double number) {
        double result = Math.abs(number);
        return String.format("|%.2f| = %.2f", number, result);
    }


    /***
     * 帮助
     * @return
     */
    @Tool(description = "Get help about available calculator operations")
    public String help() {
        return "Basic Calculator MCP Service\n\n" +
                "Available operations:\n" +
                "1. add(a, b) - Adds two numbers\n" +
                "2. subtract(a, b) - Subtracts the second number from the first\n" +
                "3. multiply(a, b) - Multiplies two numbers\n" +
                "4. divide(a, b) - Divides the first number by the second\n" +
                "5. power(base, exponent) - Raises a number to a power\n" +
                "6. squareRoot(number) - Calculates the square root\n" +
                "7. modulus(a, b) - Calculates the remainder of division\n" +
                "8. absolute(number) - Calculates the absolute value\n\n" +
                "Example usage: add(5, 3) will return 5 + 3 = 8\n\n" +
                "All operations include input validation and clear error messages.";
    }

}
