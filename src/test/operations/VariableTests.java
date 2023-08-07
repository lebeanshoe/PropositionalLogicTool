package operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VariableTests {
    private Variable a;
    private Variable b;
    private Variable c;
    private Variable x1;
    private Variable x2;
    private Variable x3;

    @BeforeEach
    void runBefore() {
        a = new Variable("a", 0);
        b = new Variable("b", 1);
        c = new Variable("c", 2);

        x1 = new Variable("x", 3);
        x2 = new Variable("x", 3);
        x3 = new Variable("x", 2);
    }

    @Test
    void testOverrideEq() {
        assertTrue(x1.equals(x1));
        assertTrue(x1.equals(x2));
        assertFalse(x1.equals("x"));
        assertFalse(x1.equals(x3));
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
