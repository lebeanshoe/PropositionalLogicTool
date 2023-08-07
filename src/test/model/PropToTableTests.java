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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

// Code for testing preview() obtained from https://www.baeldung.com/java-testing-system-out-println
public class PropToTableTests {
    private Proposition prop1;
    private Proposition prop2;
    private TruthTable tab1;
    private TruthTable tab2;
    private Query ptt1;
    private Query ptt2;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void runBefore() {
        prop1 = new BinaryOperation("a v b", new ArrayList<>(), new ArrayList<>());
        prop2 = new BinaryOperation("a xor b", new ArrayList<>(), new ArrayList<>());
        tab1 = new TruthTable(prop1);
        tab2 = new TruthTable(prop2);
        ptt1 = new PropToTable(prop1, tab1);
        ptt2 = new PropToTable(prop2, tab2);

        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testConstructor() {
        assertEquals(prop1, ptt1.getInputs().get(0));
        assertEquals(tab1, ptt1.getOutputs().get(0));

        assertEquals(prop2, ptt2.getInputs().get(0));
        assertEquals(tab2, ptt2.getOutputs().get(0));

        assertEquals("Truth Table Conversion", ptt1.getType());
    }

    @Test
    void testOverrideEq() {
        assertFalse(ptt1.equals(ptt2));
        assertFalse(ptt1.equals("not equals"));
    }

    @Test
    void testPreview1() {
        ptt1.preview();
        assertEquals("Conversion to truth table: a v b", outputStreamCaptor.toString().trim());
    }

    @Test
    void testPreview2() {
        ptt2.preview();
        assertEquals("Conversion to truth table: a xor b", outputStreamCaptor.toString().trim());
    }
}
