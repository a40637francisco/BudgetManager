package javaa.main.views.util;

import javaa.main.model.domain.Controller;
import javaa.main.model.domain.Item;
import javaa.main.model.domain.Items;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Created on 15/08/2016.
 */
public class AddItemLayout extends VBox {


    public static final double WIDTH = 480;
    public static final double HEIGHT = 320;

    private Consumer<Items> callback;
    private TextField itemTf;
    private TextField valueTf;

    public AddItemLayout(Consumer<Items> callback) {
        this.callback = callback;
        this.setSpacing(15);
        this.getChildren().add(addTitle());
        this.getChildren().add(addItemPicker());
        this.getChildren().add(addItemDisplay());
        this.getChildren().add(addConfirmButton());
    }

    private Node addConfirmButton() {
        HBox hb = new HBox();
        hb.setSpacing(15);
        hb.setAlignment(Pos.BASELINE_CENTER);
        Button btt = new Button("confirmar");
        btt.setOnAction((e) -> {
            //check if it is valid values

            Item item = new Item();
            item.setDescription(itemTf.getText());
            item.setValue(Integer.parseInt(valueTf.getText()));
            callback.accept(item);
        });

        hb.getChildren().add(btt);
        return hb;
    }

    private Node addItemDisplay() {
        HBox hb = new HBox();
        hb.setSpacing(20);
        hb.setAlignment(Pos.BASELINE_CENTER);

        addDescriptionDisplay(hb);
        addValueDisplay(hb);

        return hb;
    }

    private void addDescriptionDisplay(HBox hb) {
        VBox vBox = new VBox();

        Text label = new Text("Item");
        itemTf = new TextField();
        itemTf.setFocusTraversable(false);
        itemTf.setEditable(false);
        vBox.getChildren().addAll(label, itemTf);

        hb.getChildren().add(vBox);
    }

    private void addValueDisplay(HBox hb) {
        VBox vBox = new VBox();

        Text label = new Text("Valor");
        valueTf = new TextField();
        valueTf.setFocusTraversable(false);
        vBox.getChildren().addAll(label, valueTf);

        hb.getChildren().add(vBox);
    }

    private Node addItemPicker() {
        HBox hb = new HBox();
        hb.setAlignment(Pos.BASELINE_CENTER);

        ComboBox<Item> picker = new ComboBox<>();
        picker.setPromptText("Escolher...");
        picker.setOnAction(event -> {
            valueTf.setText(String.valueOf(picker.getValue().getValue()));
            itemTf.setText(picker.getValue().getDescription());
        });

        Controller.getAllProducts((item) -> picker.getItems().add(item));

        hb.getChildren().add(picker);
        return hb;
    }

    private Node addTitle() {
        HBox hb = new HBox();
        hb.setAlignment(Pos.BASELINE_CENTER);
        Text title = new Text("Escolher Item");
        title.setFont(new Font("Helvitic", 24));

        hb.getChildren().add(title);
        return hb;
    }

}
