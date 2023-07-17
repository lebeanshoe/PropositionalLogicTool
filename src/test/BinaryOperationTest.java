import model.BinaryOperation;
import model.Proposition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryOperationTest {
    private Proposition simpleNot;
    private Proposition simpleBin1;
    private Proposition simpleBin2;

    private Proposition longOperator1;
    private Proposition longOperator2;

    private Proposition compoundOp1;
    private Proposition compoundOp2;

    private Proposition twoDeep1;
    private Proposition twoDeep2;

    @BeforeEach
    void runBefore() {
        simpleNot = new BinaryOperation("~a");
        simpleBin1 = new BinaryOperation("bvc");
        simpleBin2 = new BinaryOperation("d^e");

        longOperator1 = new BinaryOperation("f<->g");
        longOperator2 = new BinaryOperation("h xor i");

        compoundOp1 = new BinaryOperation("(xv(~y))");
        compoundOp2 = new BinaryOperation("(av(~b))^(avc)"); // DIST law

        twoDeep1 = new BinaryOperation("((a ^ b) v (c -> d)) xor ((c v a) -> (b <-> d))");
        twoDeep2 = new BinaryOperation("((x -> z) xor (w ^ y)) ^ (((~x) v y) ^ (w xor z))");
    }

    @Test
    void testConstructor() {
        assertEquals("~", simpleNot.getOperator().toString());
        assertEquals("a", simpleNot.getSubProps().get(0).toString());

        assertEquals("v", simpleBin1.getOperator().toString());
        assertEquals("b", simpleBin1.getSubProps().get(0).toString());
        assertEquals("c", simpleBin1.getSubProps().get(1).toString());

        assertEquals("^", simpleBin2.getOperator().toString());
        assertEquals("d", simpleBin2.getSubProps().get(0).toString());
        assertEquals("e", simpleBin1.getSubProps().get(1).toString());
    }

    @Test
    void testLongConstructor() {
        assertEquals("<->", longOperator1.getOperator().toString());
        assertEquals("f", longOperator1.getSubProps().get(0).toString());
        assertEquals("g", longOperator1.getSubProps().get(1).toString());

        assertEquals("xor", longOperator2.getOperator().toString());
        assertEquals("h", longOperator2.getSubProps().get(0).toString());
        assertEquals("i", longOperator2.getSubProps().get(1).toString());
    }

    @Test
    void testCompoundConstructor1() {
        assertEquals("v", compoundOp1.getOperator().toString());
        assertEquals("x", compoundOp1.getSubProps().get(0).toString());

        Proposition rChild = compoundOp1.getSubProps().get(1);
        assertEquals("~", rChild.getOperator().toString());
        assertEquals("y", rChild.getSubProps().get(0).toString());
    }

    @Test
    void testCompoundConstructor2() {
        assertEquals("^", compoundOp2.getOperator().toString());

        Proposition lChild = compoundOp2.getSubProps().get(0);
        assertEquals("v", lChild.getOperator().toString());
        assertEquals("a", lChild.getSubProps().get(0).toString());
        Proposition lChildSub = compoundOp1.getSubProps().get(1);
        assertEquals("~", lChildSub.getOperator().toString());
        assertEquals("b", lChildSub.getSubProps().get(0).toString());

        Proposition rChild = compoundOp2.getSubProps().get(1);
        assertEquals("v", rChild.getOperator().toString());
        assertEquals("a", rChild.getSubProps().get(0).toString());
        assertEquals("c", rChild.getSubProps().get(1).toString());
    }

    @Test
    void testDeeperConstruction1() {
        assertEquals("xor", twoDeep1.getOperator().toString());

        Proposition lChild = twoDeep1.getSubProps().get(0);
        assertEquals("v", lChild.getOperator().toString());
            Proposition lChildLChild = lChild.getSubProps().get(0);
            assertEquals("^", lChildLChild.getOperator().toString());
            assertEquals("a", lChildLChild.getSubProps().get(0).toString());
            assertEquals("b", lChildLChild.getSubProps().get(1).toString());
            Proposition lChildRChild = lChild.getSubProps().get(1);
            assertEquals("->", lChildRChild.getOperator().toString());
            assertEquals("c", lChildRChild.getSubProps().get(0).toString());
            assertEquals("d", lChildRChild.getSubProps().get(1).toString());

        Proposition rChild = twoDeep1.getSubProps().get(1);
        assertEquals("->", rChild.getOperator().toString());
            Proposition rChildLChild = rChild.getSubProps().get(0);
            assertEquals("v", rChildLChild.getOperator().toString());
            assertEquals("c", rChildLChild.getSubProps().get(0).toString());
            assertEquals("a", rChildLChild.getSubProps().get(1).toString());
            Proposition rChildRChild = rChild.getSubProps().get(1);
            assertEquals("<->", rChildRChild.getOperator().toString());
            assertEquals("b", rChildRChild.getSubProps().get(0).toString());
            assertEquals("d", rChildRChild.getSubProps().get(1).toString());
    }

    @Test
    void testDeeperConstruction2() {
        assertEquals("^", twoDeep2.getOperator().toString());

        Proposition lChild = twoDeep2.getSubProps().get(0);
        assertEquals("xor", lChild.getOperator().toString());
            Proposition lChildLChild = lChild.getSubProps().get(0);
            assertEquals("->", lChildLChild.getOperator().toString());
            assertEquals("x", lChildLChild.getSubProps().get(0).toString());
            assertEquals("z", lChildLChild.getSubProps().get(1).toString());
            Proposition lChildRChild = lChild.getSubProps().get(1);
            assertEquals("^", lChildRChild.getOperator().toString());
            assertEquals("w", lChildRChild.getSubProps().get(0).toString());
            assertEquals("y", lChildRChild.getSubProps().get(1).toString());

        Proposition rChild = twoDeep2.getSubProps().get(1);
        assertEquals("^", rChild.getOperator().toString());
            Proposition rChildLChild = rChild.getSubProps().get(0);
            assertEquals("v", rChildLChild.getOperator().toString());
            assertEquals("~x", rChildLChild.getSubProps().get(0).toString());
            assertEquals("y", rChildLChild.getSubProps().get(1).toString());
            Proposition rChildRChild = rChild.getSubProps().get(1);
            assertEquals("xor", rChildRChild.getOperator().toString());
            assertEquals("w", rChildRChild.getSubProps().get(0).toString());
            assertEquals("z", rChildRChild.getSubProps().get(1).toString());
    }

    @Test
    void testEvaluate() {
        List<Boolean> simpleNotAssignment = simpleNot.evaluate();
        assertEquals(2, simpleNotAssignment.size());
        assertTrue(simpleNotAssignment.get(0));
        assertFalse(simpleNotAssignment.get(1));

        List<Boolean> simpleBin1Assignment = simpleBin1.evaluate();
        assertEquals(4, simpleBin1Assignment.size());
        assertFalse(simpleBin1Assignment.get(0));
        assertTrue(simpleBin1Assignment.get(1));
        assertTrue(simpleBin1Assignment.get(2));
        assertTrue(simpleBin1Assignment.get(3));

        List<Boolean> compoundOp2Assignment = compoundOp2.evaluate();
        assertEquals(8, compoundOp2Assignment.size());
        assertFalse(compoundOp2Assignment.get(0));
        assertTrue(compoundOp2Assignment.get(1));
        assertFalse(compoundOp2Assignment.get(2));
        assertFalse(compoundOp2Assignment.get(3));
        assertTrue(compoundOp2Assignment.get(4));
        assertTrue(compoundOp2Assignment.get(5));
        assertTrue(compoundOp2Assignment.get(6));
        assertTrue(compoundOp2Assignment.get(7));

        List<Boolean> twoDeep1Assignment = twoDeep1.evaluate();
        assertEquals(16, twoDeep1Assignment.size());
        assertFalse(twoDeep1Assignment.get(0));
        assertFalse(twoDeep1Assignment.get(1));
        assertTrue(twoDeep1Assignment.get(2));
        assertTrue(twoDeep1Assignment.get(3));
        assertFalse(twoDeep1Assignment.get(4));
        assertFalse(twoDeep1Assignment.get(5));
        assertFalse(twoDeep1Assignment.get(6));
        assertFalse(twoDeep1Assignment.get(7));
        assertFalse(twoDeep1Assignment.get(8));
        assertTrue(twoDeep1Assignment.get(9));
        assertTrue(twoDeep1Assignment.get(10));
        assertTrue(twoDeep1Assignment.get(11));
        assertTrue(twoDeep1Assignment.get(12));
        assertFalse(twoDeep1Assignment.get(13));
        assertTrue(twoDeep1Assignment.get(14));
        assertFalse(twoDeep1Assignment.get(15));
    }

    @Test
    void testToString() {
        assertEquals("~a", simpleNot.toString());
        assertEquals("b v c", simpleBin1.toString());
        assertEquals("f <-> g", longOperator1.toString());
        assertEquals("h xor i", longOperator2.toString());

        assertEquals("x v (~y)", compoundOp2.toString());
        assertEquals("~y", compoundOp2.getSubProps().get(1).toString());

        assertEquals("(a v (~b)) ^ (a v c)", compoundOp2.toString());
        assertEquals("a v (~b)", compoundOp2.getSubProps().get(0).toString());

        assertEquals("(~x) v y", twoDeep2.getSubProps().get(1).getSubProps().get(0).toString());
    }
}
