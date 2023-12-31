package model;

import model.operations.BinaryOperation;
import model.operations.TruthTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CanvasTests {
    private Canvas testCanvas;
    private Query testQuery;
    private Query testQuery2;
    private BinaryOperation testBinOp;
    private BinaryOperation testBinOp2;
    private TruthTable testTab1;
    private TruthTable testTab2;

    @BeforeEach
    void runBefore() {
        testCanvas = new Canvas("testCanvas");
        testBinOp = new BinaryOperation("a -> b", new ArrayList<>(), new ArrayList<>());
        testBinOp2 = new BinaryOperation("a ^ b", new ArrayList<>(), new ArrayList<>());
        testTab1 = new TruthTable(testBinOp);
        testTab2 = new TruthTable(testBinOp2);
        testQuery = new PropToTable(testBinOp, testTab1);
        testQuery2 = new PropToTable(testBinOp2, testTab2);
    }

    @Test
    void testConstructorEmpty() {
        assertEquals("testCanvas", testCanvas.getName());
        assertTrue(testCanvas.getQueries().isEmpty());
    }

    @Test
    void testSetters() {
        testCanvas.addQuery(testQuery, 2, 3);
        testCanvas.addQuery(testQuery2, 5, 0);
        testCanvas.setX(testQuery, 20);
        testCanvas.setY(testQuery, 30);
        testCanvas.setX(testQuery2, 50);
        testCanvas.setY(testQuery2, 100);
        assertEquals(20, testCanvas.getX(testQuery));
        assertEquals(30, testCanvas.getY(testQuery));
        assertEquals(50, testCanvas.getX(testQuery2));
        assertEquals(100, testCanvas.getY(testQuery2));

        testCanvas.setX(testQuery, 25);
        testCanvas.setY(testQuery, 35);
        testCanvas.setX(testQuery2, 55);
        testCanvas.setY(testQuery2, 105);
        assertEquals(25, testCanvas.getX(testQuery));
        assertEquals(35, testCanvas.getY(testQuery));
        assertEquals(55, testCanvas.getX(testQuery2));
        assertEquals(105, testCanvas.getY(testQuery2));
    }

    @Test
    void testAddQuery() {
        testCanvas.addQuery(testQuery, 0, 5);
        assertEquals(1, testCanvas.getQueries().size());
        assertEquals(0, testCanvas.getX(testQuery));
        assertEquals(5, testCanvas.getY(testQuery));
        assertTrue(testCanvas.getQueries().contains(testQuery));

        BinaryOperation samp = new BinaryOperation("a -> b", new ArrayList<>(), new ArrayList<>());
        TruthTable sampTab = new TruthTable(samp);
        Query sampQuery = new PropToTable(samp, sampTab);
        assertTrue(testCanvas.getQueries().contains(sampQuery));
    }

    @Test
    void testAddQueries() {
        testCanvas.addQuery(testQuery, 2, 3);
        testCanvas.addQuery(testQuery2, 5, 0);
        assertEquals(2, testCanvas.getQueries().size());
        assertEquals(2, testCanvas.getX(testQuery));
        assertEquals(3, testCanvas.getY(testQuery));
        assertEquals(2, testCanvas.getQueries().size());
        assertEquals(5, testCanvas.getX(testQuery2));
        assertEquals(0, testCanvas.getY(testQuery2));
    }

    @Test
    void testRemoveQuery() {
        testCanvas.addQuery(testQuery, 2, 3);
        testCanvas.addQuery(testQuery2, 5, 0);
        testCanvas.removeQuery(testQuery2);

        assertEquals(1, testCanvas.getQueries().size());
        assertEquals(2, testCanvas.getX(testQuery));
        assertEquals(3, testCanvas.getY(testQuery));

        testCanvas.removeQuery(testQuery);
        assertEquals(0, testCanvas.getQueries().size());
    }
}
