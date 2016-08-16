package javaa.main.model.dataAPI.model;

import java.util.ArrayList;

/**
 * Created on 14/08/2016.
 */
public class DtoSection implements DtoItems {

    private String description;
    private int value;
    private ArrayList<DtoItem> items = new ArrayList<>();

    public ArrayList<DtoItem> getItems() {
        return items;
    }

    public void addItem(DtoItem i) {
        items.add(i);
    }

    public void removeItem(DtoItem i) {
        items.remove(i);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
