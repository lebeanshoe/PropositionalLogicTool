package model.persistence;

import model.*;
import model.logging.Event;
import model.logging.EventLog;
import model.operations.BinaryOperation;
import model.operations.TruthTable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a writer that writes JSON representation of workroom to file
// Taken from JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads canvas from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public Canvas read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCanvas(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses canvas from JSON object and returns it
    private Canvas parseCanvas(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Canvas cv = new Canvas(name);
        addQueries(cv, jsonObject);
        EventLog.getInstance().logEvent(new Event("Loaded " + name + " with " + cv.getQueries().size()
                + " queries from: " + source));
        return cv;
    }

    // MODIFIES: cv
    // EFFECTS: parses queries from JSON object and adds them to canvas
    private void addQueries(Canvas cv, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("queries");
        for (Object json : jsonArray) {
            JSONObject nextQuery = (JSONObject) json;
            addQuery(cv, nextQuery);
        }
    }

    // MODIFIES: cv
    // EFFECTS: parses query from JSON object and adds it to canvas
    private void addQuery(Canvas cv, JSONObject jsonObject) {
        String type = jsonObject.getString("query");
        int x = (int) jsonObject.get("XPos");
        int y = (int) jsonObject.get("YPos");
        if (type.equals("PropToTable")) {
            BinaryOperation binOp = new BinaryOperation(jsonObject.getString("proposition"),
                    new ArrayList<>(), new ArrayList<>());
            TruthTable tab = new TruthTable(binOp);
            Query query = new PropToTable(binOp, tab);
            cv.addQuery(query, x, y);
        } else {
            BinaryOperation binOp1 = new BinaryOperation(jsonObject.getString("prop1"),
                    new ArrayList<>(), new ArrayList<>());
            BinaryOperation binOp2 = new BinaryOperation(jsonObject.getString("prop2"),
                    new ArrayList<>(), new ArrayList<>());
            TruthTable tab1 = new TruthTable(binOp1);
            TruthTable tab2 = new TruthTable(binOp2);
            Query query = new Comparison(binOp1, binOp2, tab1, tab2);
            cv.addQuery(query, x, y);
        }
    }
}
