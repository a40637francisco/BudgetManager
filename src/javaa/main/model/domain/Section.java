package javaa.main.model.domain;

import java.util.ArrayList;

/**
 * Created on 14/08/2016.
 */
public class Section implements Items{


    private String description;
    private int values;
    private ArrayList<Item> items = new ArrayList<>();

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setValue(int values) {
        this.values = values;
    }

    public int getValue() {
        return values;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
}
