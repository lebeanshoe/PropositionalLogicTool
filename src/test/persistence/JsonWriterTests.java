package persistence;

import model.Canvas;
import model.Comparison;
import model.PropToTable;
import model.Query;
import operations.BinaryOperation;
import operations.TruthTable;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTests extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Canvas cv = new Canvas("My canvas");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Canvas cv = new Canvas("My writer canvas");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCanvas.json");
            writer.open();
            writer.write(cv);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCanvas.json");
            cv = reader.read();
            assertEquals("My writer canvas", cv.getName());
            assertEquals(0, cv.getQueries().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Canvas cv = new Canvas("My writer general canvas");
//            cv.addThingy(new Thingy("saw", Category.METALWORK));
//            cv.addThingy(new Thingy("needle", Category.STITCHING));
            BinaryOperation binOp1 = new BinaryOperation("a ^ b", new ArrayList<>(), new ArrayList<>());
            BinaryOperation binOp2 = new BinaryOperation("~(p v q)", new ArrayList<>(), new ArrayList<>());
            BinaryOperation binOp3 = new BinaryOperation("(~p) ^ (~q)", new ArrayList<>(), new ArrayList<>());
            TruthTable tab1 = new TruthTable(binOp1);
            TruthTable tab2 = new TruthTable(binOp2);
            TruthTable tab3 = new TruthTable(binOp3);
            PropToTable query1 = new PropToTable(binOp1, tab1);
            Comparison query2 = new Comparison(binOp2, binOp3, tab2, tab3);
            cv.addQuery(query1, 25, 26);
            cv.addQuery(query2, 65, 66);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(cv);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            cv = reader.read();
            assertEquals("My writer general canvas", cv.getName());
            List<Query> queries = cv.getQueries();
            assertEquals(2, queries.size());

            checkQuery(25, 26, query1, cv);

            checkQuery(65, 66, query2, cv);
//            List<Thingy> thingies = cv.getThingies();
//            assertEquals(2, thingies.size());
//            checkThingy("saw", Category.METALWORK, thingies.get(0));
//            checkThingy("needle", Category.STITCHING, thingies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
