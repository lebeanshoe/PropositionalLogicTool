package persistence;

import model.Canvas;
import model.Comparison;
import model.PropToTable;
import model.Query;
import operations.BinaryOperation;
import operations.BinaryOperationTests;
import operations.TruthTable;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTests extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Canvas cv = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCanvas.json");
        try {
            Canvas cv = reader.read();
            assertEquals("My canvas", cv.getName());
            assertEquals(0, cv.getQueries().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCanvas.json");
        BinaryOperation binOp1 = new BinaryOperation("a ^ b", new ArrayList<>(), new ArrayList<>());
        TruthTable tab1 = new TruthTable(binOp1);
        BinaryOperation binOp2a = new BinaryOperation("a xor b", new ArrayList<>(), new ArrayList<>());
        BinaryOperation binOp2b = new BinaryOperation("(a -> b) v (a <-> b)",
                new ArrayList<>(), new ArrayList<>());
        TruthTable tab2a = new TruthTable(binOp2a);
        TruthTable tab2b = new TruthTable(binOp2b);
        PropToTable query1 = new PropToTable(binOp1, tab1);
        Comparison query2 = new Comparison(binOp2a, binOp2b, tab2a, tab2b);
        try {
            Canvas cv = reader.read();
            assertEquals("My canvas", cv.getName());
            List<Query> queries = cv.getQueries();
            assertEquals(2, queries.size());

            checkQuery(55, 50, query1, cv);
            checkQuery(101, 100, query2, cv);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
