package com.webhook.sqlsolver.service;

import org.springframework.stereotype.Service;

@Service
public class SqlSolverService {

    public String solveSqlProblem(String regNo) {
        // Extract last two digits from registration number
        String lastTwoDigits = regNo.length() >= 2 ? regNo.substring(regNo.length() - 2) : regNo;
        int lastTwoDigitsInt = Integer.parseInt(lastTwoDigits.replaceAll("\\D+", ""));
        
        System.out.println("Registration Number: " + regNo);
        System.out.println("Last two digits: " + lastTwoDigitsInt);
        
        // Determine if odd or even
        boolean isOdd = lastTwoDigitsInt % 2 == 1;
        
        if (isOdd) {
            System.out.println("Last two digits are ODD - solving Question 1");
            return solveQuestion1();
        } else {
            System.out.println("Last two digits are EVEN - solving Question 2");
            return solveQuestion2();
        }
    }

    private String solveQuestion1() {
        // Question 1 (Odd): Based on the Google Drive link provided
        // This is a placeholder - you would need to fetch the actual problem from the link
        // https://drive.google.com/file/d/1lcSl6lKoSoAFfRih1TStEDlCtoz-G/view?usp=sharing
        
        // Example SQL query for Question 1
        return """
            SELECT 
                e.employee_id,
                e.first_name,
                e.last_name,
                d.department_name,
                e.salary
            FROM employees e
            JOIN departments d ON e.department_id = d.department_id
            WHERE e.salary > (
                SELECT AVG(salary) 
                FROM employees 
                WHERE department_id = e.department_id
            )
            ORDER BY e.salary DESC;
            """;
    }

    private String solveQuestion2() {
        // Question 2 (Even): Based on the Google Drive link provided
        // This is a placeholder - you would need to fetch the actual problem from the link
        // https://drive.google.com/file/d/143MR5cLFrlNEuHzzWJ5RHnEW,ijuM9X/view?usp=sharing
        
        // Example SQL query for Question 2
        return """
            SELECT 
                c.customer_id,
                c.customer_name,
                COUNT(o.order_id) as total_orders,
                SUM(o.order_amount) as total_amount
            FROM customers c
            LEFT JOIN orders o ON c.customer_id = o.customer_id
            WHERE o.order_date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
            GROUP BY c.customer_id, c.customer_name
            HAVING total_orders > 5
            ORDER BY total_amount DESC;
            """;
    }
}
