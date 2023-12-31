package model.operations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TruthTableTests {
    private Proposition oneNot;
    private Proposition oneBin;
    private Proposition compoundWithNot;

    private TruthTable oneNotTT;
    private TruthTable oneBinTT;
    private TruthTable compoundWithNotTT;

    @BeforeEach
    void runBefore() {
        oneNot = new BinaryOperation("~a", new ArrayList<>(), new ArrayList<>());
        oneBin = new BinaryOperation("a^b", new ArrayList<>(), new ArrayList<>());
        compoundWithNot = new BinaryOperation("(a->c)xor(~b)", new ArrayList<>(), new ArrayList<>());

        oneNotTT = new TruthTable(oneNot);
        oneBinTT = new TruthTable(oneBin);
        compoundWithNotTT = new TruthTable(compoundWithNot);
    }

    @Test
    void testEqualsOverride() {
        BinaryOperation sampBin = new BinaryOperation("a ^b", new ArrayList<>(), new ArrayList<>());
        TruthTable sampTab = new TruthTable(sampBin);
        BinaryOperation sampBin2 = new BinaryOperation("a ^ b", new ArrayList<>(), new ArrayList<>());
        TruthTable sampTab2 = new TruthTable(sampBin2);
        BinaryOperation sampBin3 = new BinaryOperation("avb", new ArrayList<>(), new ArrayList<>());
        TruthTable sampTab3 = new TruthTable(sampBin3);

        assertTrue(oneBinTT.equals(sampTab));
        assertFalse(oneBinTT.equals("not equals"));
        assertFalse(oneBinTT.equals(null));

        assertTrue(sampTab.hashCode() == sampTab2.hashCode());
        assertFalse(sampTab.hashCode() == sampTab3.hashCode());
    }

    @Test
    void testOneNot() {
        List<Proposition> colHeads = oneNotTT.getColumnHeaders();
        List<List<Boolean>> assigns = oneNotTT.getAssignments();
        assertEquals(2, colHeads.size());
        assertEquals(2, assigns.size());
        assertEquals("a", colHeads.get(0).toString());
        assertEquals("~a", colHeads.get(1).toString());
        assertEquals(2, assigns.get(0).size());
        assertEquals(2, assigns.get(1).size());
    }

    @Test
    void testOneBin() {
        List<Proposition> colHeads = oneBinTT.getColumnHeaders();
        List<List<Boolean>> assigns = oneBinTT.getAssignments();
        assertEquals(3, colHeads.size());
        assertEquals(3, assigns.size());
        assertEquals("a", colHeads.get(0).toString());
        assertEquals("b", colHeads.get(1).toString());
        assertEquals("a ^ b", colHeads.get(2).toString());
        assertEquals(4, assigns.get(0).size());
    }

    @Test
    void testCompound() {
        List<Proposition> colHeads = compoundWithNotTT.getColumnHeaders();
        List<List<Boolean>> assigns = compoundWithNotTT.getAssignments();
        assertEquals(6, colHeads.size());
        assertEquals(6, assigns.size());
        assertEquals("a", colHeads.get(0).toString());
        assertEquals("c", colHeads.get(1).toString());
        assertEquals("b", colHeads.get(2).toString());
        assertEquals("(a -> c) xor (~b)", colHeads.get(5).toString());
        assertEquals(8, assigns.get(0).size());
    }
}
