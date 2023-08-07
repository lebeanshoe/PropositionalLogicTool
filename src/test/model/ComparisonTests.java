package model;

import operations.BinaryOperation;
import operations.Proposition;
import operations.TruthTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Code for testing preview() obtained from https://www.baeldung.com/java-testing-system-out-println
public class ComparisonTests {
    private Proposition prop1;
    private Proposition prop2;
    private Proposition prop3;
    private Proposition prop4;
    private TruthTable tab1;
    private TruthTable tab2;
    private TruthTable tab3;
    private TruthTable tab4;
    private Query pttA;
    private Query pttB;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void runBefore() {
        prop1 = new BinaryOperation("a v b", new ArrayList<>(), new ArrayList<>());
        prop2 = new BinaryOperation("a xor b", new ArrayList<>(), new ArrayList<>());
        prop3 = new BinaryOperation("p v (q ^ r)", new ArrayList<>(), new ArrayList<>());
        prop4 = new BinaryOperation("(p v q) ^ (p v r)", new ArrayList<>(), new ArrayList<>());
        tab1 = new TruthTable(prop1);
        tab2 = new TruthTable(prop2);
        tab3 = new TruthTable(prop3);
        tab4 = new TruthTable(prop4);
        pttA = new Comparison(prop1, prop2, tab1, tab2);
        pttB = new Comparison(prop3, prop4, tab3, tab4);

        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testConstructor() {
        assertEquals(prop1, pttA.getInputs().get(0));
        assertEquals(tab1, pttA.getOutputs().get(0));
        assertEquals(prop2, pttA.getInputs().get(1));
        assertEquals(tab2, pttA.getOutputs().get(1));


        assertEquals(prop3, pttB.getInputs().get(0));
        assertEquals(tab3, pttB.getOutputs().get(0));
        assertEquals(prop4, pttB.getInputs().get(1));
        assertEquals(tab4, pttB.getOutputs().get(1));

        assertEquals("Comparison", pttA.getType());
    }

    @Test
    void testOverrideEq() {
        assertTrue(pttA.equals(pttA));
        assertFalse(pttA.equals("not equals"));
    }

    @Test
    void testPreviewA() {
        pttA.preview();
        assertEquals("Comparison between a v b and a xor b", outputStreamCaptor.toString().trim());
    }

    @Test
    void testPreviewB() {
        pttB.preview();
        assertEquals("Comparison between p v (q ^ r) and (p v q) ^ (p v r)", outputStreamCaptor.toString().trim());
    }
}
