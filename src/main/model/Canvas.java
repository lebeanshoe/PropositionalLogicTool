package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

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
    }

    // REQUIRES: query is in queries
    // MODIFIES: this
    // EFFECTS: removes query and its corresponding coordinates
    public void removeQuery(Query query) {
        this.hashQueries.remove(query);
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

    public void setX(Query query, int x) {
        this.hashQueries.get(query).set(0, x);
    }

    public void setY(Query query, int y) {
        this.hashQueries.get(query).set(1, y);
    }

    public String getName() {
        return this.name;
    }

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