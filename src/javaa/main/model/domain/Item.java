package javaa.main.model.domain;

/**
 * Created on 14/08/2016.
 */
public class Item implements Items{
    private int value;
    private String description;

    public Item(String desc, int val) {
        this.value = val;
        this.description = desc;
    }

    public Item() {
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description + "    " + value+"€";
    }


}
