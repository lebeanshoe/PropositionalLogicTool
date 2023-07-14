package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogicStatementTest {
    private LogicStatement testStatement;

    @BeforeEach
    void runBefore() {
        testStatement = new LogicStatement("a ^ b");

    }

    @Test
    void testConstructor() {
        assertEquals(2, testStatement.getVariables().size());
        assertEquals("a", testStatement.getVariables().get(0));
        assertEquals("b", testStatement.getVariables().get(1));
        // TODO check made right operation
    }
}