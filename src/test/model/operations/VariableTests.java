package model.operations;

import model.operations.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VariableTests {
    private Variable a;
    private Variable b;
    private Variable c;
    private Variable x0;
    private Variable x1;
    private Variable x2;
    private Variable x3;
    private Variable x4;
    private Variable x5;
    private Variable x6;
    private Variable x7;
    private Variable x8;


    @BeforeEach
    void runBefore() {
        a = new Variable("a", 0);
        b = new Variable("b", 1);
        c = new Variable("c", 2);

        x0 = new Variable("x", 3);
        x1 = new Variable("x", 3);
        x2 = new Variable("x", 3);
        x3 = new Variable("x", 2);
        x4 = new Variable("x", 2);
        x5 = new Variable("y", 3);
        x6 = new Variable("y", 3);
        x7 = new Variable("y", 2);
        x8 = new Variable("y", 2);
    }

    @Test
    void testOverrideEq() {
        x0.setNumVars(4);
        x1.setNumVars(4);
        x2.setNumVars(3);
        x3.setNumVars(4);
        x4.setNumVars(3);
        x5.setNumVars(4);
        x6.setNumVars(3);
        x7.setNumVars(4);
        x8.setNumVars(3);
        assertTrue(x1.equals(x1));
        assertTrue(x1.hashCode() == x1.hashCode());
        assertFalse(x1.equals("x"));
        assertFalse(x1.equals(null));
        assertTrue(x1.equals(x0));
        assertTrue(x1.hashCode() == x0.hashCode());

        assertFalse(x1.equals(x2));
        assertFalse(x1.hashCode() == x2.hashCode());
        assertFalse(x1.equals(x3));
        assertFalse(x1.hashCode() == x3.hashCode());
        assertFalse(x1.equals(x4));
        assertFalse(x1.hashCode() == x4.hashCode());
        assertFalse(x1.equals(x5));
        assertFalse(x1.hashCode() == x5.hashCode());
        assertFalse(x1.equals(x6));
        assertFalse(x1.hashCode() == x6.hashCode());
        assertFalse(x1.equals(x7));
        assertFalse(x1.hashCode() == x7.hashCode());
        assertFalse(x1.equals(x8));
        assertFalse(x1.hashCode() == x8.hashCode());
    }

    @Test
    void testEvaluateWithOne() {
        a.setNumVars(1);
        List<Boolean> assignment = a.evaluate();
        assertEquals(2, assignment.size());
        assertFalse(assignment.get(0));
        assertTrue(assignment.get(1));
    }

    @Test
    void testEvaluateWithTwo() {
        a.setNumVars(2);
        b.setNumVars(2);

        List<Boolean> assignmentA = a.evaluate();
        assertEquals(4, assignmentA.size());
        assertFalse(assignmentA.get(0));
        assertFalse(assignmentA.get(1));
        assertTrue(assignmentA.get(2));
        assertTrue(assignmentA.get(3));

        List<Boolean> assignmentB = b.evaluate();
        assertEquals(4, assignmentB.size());
        assertFalse(assignmentB.get(0));
        assertTrue(assignmentB.get(1));
        assertFalse(assignmentB.get(2));
        assertTrue(assignmentB.get(3));
    }

    @Test
    void testEvaluateWithThree() {
        a.setNumVars(3);
        b.setNumVars(3);
        c.setNumVars(3);

        List<Boolean> assignmentA = a.evaluate();
        assertEquals(8, assignmentA.size());
        assertFalse(assignmentA.get(0));
        assertFalse(assignmentA.get(1));
        assertFalse(assignmentA.get(2));
        assertFalse(assignmentA.get(3));
        assertTrue(assignmentA.get(4));
        assertTrue(assignmentA.get(5));
        assertTrue(assignmentA.get(6));
        assertTrue(assignmentA.get(7));

        List<Boolean> assignmentB = b.evaluate();
        assertEquals(8, assignmentB.size());
        assertFalse(assignmentB.get(0));
        assertFalse(assignmentB.get(1));
        assertTrue(assignmentB.get(2));
        assertTrue(assignmentB.get(3));
        assertFalse(assignmentB.get(4));
        assertFalse(assignmentB.get(5));
        assertTrue(assignmentB.get(6));
        assertTrue(assignmentB.get(7));


        List<Boolean> assignmentC = c.evaluate();
        assertEquals(8, assignmentC.size());
        assertFalse(assignmentC.get(0));
        assertTrue(assignmentC.get(1));
        assertFalse(assignmentC.get(2));
        assertTrue(assignmentC.get(3));
        assertFalse(assignmentC.get(4));
        assertTrue(assignmentC.get(5));
        assertFalse(assignmentC.get(6));
        assertTrue(assignmentC.get(7));
    }

    @Test
    void testNullMethods() {
        assertNull(a.getVars());
        assertNull(a.getOOP());
        assertNull(a.getOperator());
        assertNull(a.getSubProps());
        assertEquals(0, a.getNumVar());
    }
}
