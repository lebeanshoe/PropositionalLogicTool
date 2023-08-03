package persistence;

import model.Canvas;
import model.Comparison;
import model.PropToTable;
import model.Query;
import operations.BinaryOperation;
import operations.TruthTable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonTest {
    protected void checkQuery(int x, int y, Query query, Canvas cv) {
        assertTrue(cv.getQueries().contains(query));
        assertEquals(x, cv.getX(query));
        assertEquals(y, cv.getY(query));
    }
}