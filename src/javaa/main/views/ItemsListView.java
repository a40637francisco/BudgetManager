package javaa.main.views;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created on 14/08/2016.
 */
public class ItemsListView extends VBox{


    public ItemsListView() {
        this.getChildren().add(addTitle());



    }

    private Node addTitle() {
        HBox hb = new HBox();

        return hb;
    }
}
