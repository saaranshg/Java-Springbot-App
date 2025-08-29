package com.webhook.sqlsolver;

import com.webhook.sqlsolver.service.SqlSolverService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SqlSolverApplicationTests {

    @Autowired
    private SqlSolverService sqlSolverService;

    @Test
    void contextLoads() {
        assertNotNull(sqlSolverService);
    }

    @Test
    void testSqlSolverWithOddRegNo() {
        String result = sqlSolverService.solveSqlProblem("REG12347");
        assertNotNull(result);
        assertTrue(result.length() > 0);
    }

    @Test
    void testSqlSolverWithEvenRegNo() {
        String result = sqlSolverService.solveSqlProblem("REG12348");
        assertNotNull(result);
        assertTrue(result.length() > 0);
    }
}
