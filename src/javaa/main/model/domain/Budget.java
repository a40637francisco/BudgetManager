package javaa.main.model.domain;

import javaa.main.model.dataAPI.model.DtoBudget;
import javaa.main.model.dataAPI.model.DtoItems;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created on 15/08/2016.
 */
public class Budget {
    private String name;
    private String address;
    private ArrayList<Items> items = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }

    public void addItem(Items item) {
        items.add(item);
    }

}
