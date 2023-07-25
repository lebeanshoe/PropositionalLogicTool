package persistence;

import model.Canvas;
import model.Query;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkQuery(Class className, int x, int y, Query query, Canvas cv) {
        assertEquals(className, query.getClass());
        assertEquals(x, cv.getX(query));
        assertEquals(y, cv.getY(query));
    }
}
