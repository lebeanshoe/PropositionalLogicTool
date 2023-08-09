package model;

import model.logging.Event;
import model.logging.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import model.persistence.Writable;

import java.util.*;

// represents a canvas of queries, with a list of queries and their corresponding x,y coordinates (top left corner)
// make hashmap of queries + pairs of int
public class Canvas implements Writable {
    private String name;
    private Map<Query, List<Integer>> hashQueries;

    // EFFECTS: constructs a new canvas with no queries and no stored positions
    public Canvas(String name) {
        this.name = name;
        this.hashQueries = new HashMap<>();
    }

    // REQUIRES: x,y >= 0
    // MODIFIES: this
    // EFFECTS: adds a query to the canvas with an x and y position of the top left corner
    public void addQuery(Query query, int x, int y) {
        List<Integer> pos = new ArrayList<>();
        pos.add(x);
        pos.add(y);
        this.hashQueries.put(query, pos);
        query.logAddRemoveCanvas(this.name, "added to");
    }

    // REQUIRES: query is in queries
    // MODIFIES: this
    // EFFECTS: removes query and its corresponding coordinates
    public void removeQuery(Query query) {
        this.hashQueries.remove(query);
        query.logAddRemoveCanvas(this.name, "removed from");
    }

    public List<Query> getQueries() {
        return new ArrayList<>(this.hashQueries.keySet());
    }

    // REQUIRES: query is in queries
    // EFFECTS: gets X position of specified query
    public int getX(Query query) {
        return this.hashQueries.get(query).get(0);
    }

    // REQUIRES: query is in queries
    // EFFECTS: gets Y position of specified query
    public int getY(Query query) {
        return this.hashQueries.get(query).get(1);
    }

    // MODIFIES: this
    // EFFECTS: changes the X position of query to x
    public void setX(Query query, int x) {
        this.hashQueries.get(query).set(0, x);
        logPositionUpdate("X", query);
    }

    // MODIFIES: this
    // EFFECTS: changes the Y position of query to y
    public void setY(Query query, int y) {
        this.hashQueries.get(query).set(1, y);
        logPositionUpdate("Y", query);
    }

    public String getName() {
        return this.name;
    }

    // REQUIRES: axis is either "X" or "Y"
    // EFFECTS: logs position change in EventLog
    private void logPositionUpdate(String axis, Query query) {
        int newPos = -1;
        if (axis == "X") {
            newPos = getX(query);
        } else if (axis == "Y") {
            newPos = getY(query);
        }
        EventLog.getInstance().logEvent(new Event(axis + " position of \""
                + query.getType() + "\" updated to " + newPos + "."));
    }

    // EFFECTS: writes canvas to json file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("queries", queriesToJson());
        return json;
    }

    // EFFECTS: returns queries in this canvas as a JSON array
    private JSONArray queriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Query q : getQueries()) {
            jsonArray.put(q.toJson(getX(q), getY(q)));
        }

        return jsonArray;
    }
}