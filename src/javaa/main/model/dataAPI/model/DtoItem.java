package javaa.main.model.dataAPI.model;

/**
 * Created on 14/08/2016.
 */
public class DtoItem implements DtoItems {

    private String description;
    private int value;

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
