package model;

import java.util.ArrayList;
import java.util.List;

// represents a canvas of queries, with a list of queries and their corresponding x,y coordinates (top left corner)
public class Canvas {
    private List<Query> queries;
    private List<Integer> posX;
    private List<Integer> posY;

    // EFFECTS: constructs a new canvas with no queries and no stored positions
    public Canvas() {
        this.queries = new ArrayList<>();
        this.posX = new ArrayList<>();
        this.posY = new ArrayList<>();
    }

    // REQUIRES: x,y >= 0
    // MODIFIES: this
    // EFFECTS: adds a query to the canvas with an x and y position of the top left corner
    public void addQuery(Query query, int x, int y) {
        this.queries.add(query);
        this.posX.add(x);
        this.posY.add(y);
    }

    // REQUIRES: query is in queries
    // MODIFIES: this
    // EFFECTS: removes query and its corresponding coordinates
    public void removeQuery(Query query) {
        int index = this.queries.indexOf(query);
        this.queries.remove(index);
        this.posX.remove(index);
        this.posY.remove(index);
    }

    public List<Query> getQueries() {
        return this.queries;
    }

    // REQUIRES: query is in queries
    // EFFECTS: gets X position of specified query
    public int getX(Query query) {
        int index = this.queries.indexOf(query);
        return this.posX.get(index);
    }

    // REQUIRES: query is in queries
    // EFFECTS: gets Y position of specified query
    public int getY(Query query) {
        int index = this.queries.indexOf(query);
        return this.posY.get(index);
    }
}