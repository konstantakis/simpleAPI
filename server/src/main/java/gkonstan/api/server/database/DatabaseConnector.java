package gkonstan.api.server.database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;

import gkonstan.api.server.model.Model;

public abstract class DatabaseConnector<T extends Model> {
    protected List<T> table;
    protected int lastId;

    protected DatabaseConnector() {
        table = new ArrayList<>();
        lastId = 0;
    }

    public boolean add(T x){
        if (search(x.getId()) != null) {
            return false;
        }
        table.add(x);
        return true;
    }

    public void edit(T x){
        remove(x.getId());
        add(x);
    }

    public T search(String id){
        for (T x : table) {
            if (x.getId().equals(id)) {
                return x;
            }
        }
        return null;
    }

    public List<T> searchMultipleTransactions(List<String> idList) {
        return table.stream().filter(x -> idList.contains(x.getId())).collect(Collectors.toList());
    }

    public T remove(String id) {
        T toRemove = search(id);
        if (toRemove == null) {
            return null;
        }
        table.remove(toRemove);
        return toRemove;
    }

    public List<T> getAll() {
        return table;
    }

    public JSONArray toJSON() {
        return new JSONArray(
            table.stream().map(x -> x.toJSON()).collect(Collectors.toList()));
    }
}
    
