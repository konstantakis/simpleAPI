package gkonstan.api.server.model;

import org.json.JSONObject;

public abstract class Model {
    protected final String id;

    protected Model(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract JSONObject toJSON();
}