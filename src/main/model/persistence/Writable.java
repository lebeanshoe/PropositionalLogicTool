package model.persistence;

import org.json.JSONObject;

// generic writable interface
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
