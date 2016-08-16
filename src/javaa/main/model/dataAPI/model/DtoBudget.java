package javaa.main.model.dataAPI.model;

import java.util.ArrayList;

/**
 * Created on 15/08/2016.
 */
public class DtoBudget {

    private String name;
    private String address;
    private ArrayList<DtoItems> items = new ArrayList<>();


    public void addToItems(DtoItems i) {
        items.add(i);
    }

    public void removeFromItems(DtoItems i) {
        items.remove(i);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "DtoBudget{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", items count= "+ items.size()+
                '}';
    }

    public ArrayList<DtoItems> getItems() {
        return items;
    }
}
