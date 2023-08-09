package model.operators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OperatorTests {
    private Operator and;
    private Operator bic;
    private Operator imp;
    private Operator not;
    private Operator or;
    private Operator xor;
    private boolean T;
    private boolean F;

    @BeforeEach
    void runBefore() {
        and = new And();
        bic = new Biconditional();
        imp = new Implies();
        not = new Not();
        or = new Or();
        xor = new Xor();

        T = true;
        F = false;
    }

    @Test
    void testAnd() {
        assertFalse(and.evaluate(F, F));
        assertFalse(and.evaluate(F, T));
        assertFalse(and.evaluate(T, F));
        assertTrue(and.evaluate(T, T));
    }

    @Test
    void testBic() {
        assertTrue(bic.evaluate(F, F));
        assertFalse(bic.evaluate(F, T));
        assertFalse(bic.evaluate(T, F));
        assertTrue(bic.evaluate(T, T));
    }

    @Test
    void testImp() {
        assertTrue(imp.evaluate(F, F));
        assertTrue(imp.evaluate(F, T));
        assertFalse(imp.evaluate(T, F));
        assertTrue(imp.evaluate(T, T));
    }

    @Test
    void testNot() {
        assertTrue(not.evaluate(F, null));
        assertFalse(not.evaluate(T, null));
    }

    @Test
    void testOr() {
        assertFalse(or.evaluate(F, F));
        assertTrue(or.evaluate(F, T));
        assertTrue(or.evaluate(T, F));
        assertTrue(or.evaluate(T, T));
    }


    @Test
    void testXor() {
        assertFalse(xor.evaluate(F, F));
        assertTrue(xor.evaluate(F, T));
        assertTrue(xor.evaluate(T, F));
        assertFalse(xor.evaluate(T, T));
    }
}
