package persistence;

import model.Canvas;
import model.Comparison;
import model.PropToTable;
import model.Query;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
        try {
            Canvas cv = reader.read();
            assertEquals("My canvas", cv.getName());
            List<Query> queries = cv.getQueries();
            assertEquals(2, queries.size());

            Query query1 = queries.get(0);
            checkQuery(PropToTable.class, 55, 50, query1, cv);
            assertEquals("a ^ b", query1.getInputs().get(0).toString());

            Query query2 = queries.get(1);
            checkQuery(Comparison.class, 101, 100, query2, cv);
            assertEquals("a xor b", query2.getInputs().get(0).toString());
            assertEquals("(a -> b) v (a <-> b)", query2.getInputs().get(1).toString());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
