package javaa.main.views.util;

import javaa.main.model.domain.Item;
import javaa.main.model.domain.Items;
import javaa.main.model.domain.Section;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created on 15/08/2016.
 */
public class AddSectionLayout extends VBox {

    public static final double WIDTH = 480;
    public static final double HEIGHT = 480;

    private Consumer<Items> callback;
    private TextField descTf;

    private ListView<Items> listView = new ListView<>();
    private ObservableList<Items> obList = FXCollections.observableArrayList();

    private Section section;
    private Text totalText;
    private int total;

    public AddSectionLayout(Consumer<Items> callback) {
        this.callback = callback;
        section = new Section();
        this.setSpacing(15);
        this.getChildren().add(addTitle());
        this.getChildren().add(addDescription());
        this.getChildren().add(addAddItemLabel());
        this.getChildren().add(addItemsArea());
        this.getChildren().add(addTotal());
        this.getChildren().add(addConfirm());
    }

    private Node addTitle() {
        HBox hb = new HBox();
        hb.setAlignment(Pos.BASELINE_CENTER);
        Text text = new Text("Secção");
        text.setFont(new Font("Tahoma", 24));
        hb.getChildren().add(text);
        return hb;
    }

    private Node addDescription() {
        HBox hb = new HBox();
        hb.setPadding(new Insets(5, 0, 0, 5));
        Text label = new Text("descrição");
        label.setFont(new Font("Tahoma", 18));

        descTf = new TextField();
        hb.getChildren().addAll(label, descTf);
        return hb;
    }

    private Node addAddItemLabel() {
        HBox hb = new HBox();
        hb.setSpacing(10);
        hb.setPadding(new Insets(5, 0, 0, 5));
        Text label = new Text("Items");
        label.setFont(new Font("Helvetica", 18));
        hb.getChildren().addAll(label);
        return hb;
    }

    private Node addItemsArea() {
        HBox hb = new HBox();
        hb.setSpacing(5);
        VBox vb = new VBox();
        vb.setSpacing(5);
        vb.getChildren().addAll(plusBtt(), removeBtt());
        hb.getChildren().addAll(addItemListView(), vb);
        return hb;
    }

    private boolean removeMode = false;

    private Node removeBtt() {
        Button btt = new Button("x");
        btt.setFont(new Font("Helvetica", 18));
        btt.setStyle("-fx-text-fill: white;" +
                "-fx-background-color: red;");
        btt.setOnAction((e) -> {
            if (removeMode) {
                removeMode = false;
                btt.setStyle("-fx-text-fill: white;" +
                        "-fx-background-color: red;");
                listView.setFocusTraversable(false);
            } else {
                removeMode = true;
                btt.setStyle("-fx-text-fill: black;");
                listView.setFocusTraversable(false);
            }
        });
        return btt;
    }

    private Node plusBtt() {
        Button btt = new Button("+");
        btt.setFont(new Font("Helvetica", 18));
        VBox parent = this;
        btt.setOnAction((e) -> {
            Stage s = new Stage();
            s.setTitle("Item");
            s.setScene(new Scene(new AddItemLayout((item) -> {
                obList.add(item);
                total += ((Item) item).getValue();
                section.addItem((Item) item);
                totalText.setText(total + "");
            }), AddItemLayout.WIDTH, AddItemLayout.HEIGHT));
            s.setX(parent.getLayoutX() ); //TODO !!!!!!!!!
            s.setY(parent.getLayoutY() + AddSectionLayout.WIDTH / 2);
            s.setWidth(AddItemLayout.WIDTH);
            s.setHeight(AddItemLayout.HEIGHT);
            s.show();
        });
        btt.setStyle("-fx-background-color: green;");
        return btt;
    }

    private Node addItemListView() {
        listView.setCellFactory(new Callback<ListView<Items>, ListCell<Items>>() {
            @Override
            public ListCell<Items> call(ListView<Items> param) {
                ListCell<Items> cell = new ListCell<Items>() {
                    @Override
                    protected void updateItem(Items item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            //check if its section too, not OOP :´(
                            if (item instanceof Item) {
                                //setGraphic(itemLayout((Item) item));
                                setGraphic(new Text(item.toString()));
                            }
                        } else {
                            setGraphic(null);
                        }
                    }
                };
                return cell;
            }
        });
        listView.setFocusTraversable(false);
        listView.setOnMouseClicked(event -> {
            Items item = listView.getSelectionModel().getSelectedItem();
            if (item == null) return;
            System.out.println(item);
            if (removeMode) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Remover");
                alert.setHeaderText("Remover " + ((Item) item).getDescription());
                alert.setContentText("Tem a certeza que quer remover?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    obList.remove(item);
                    section.removeItem((Item) item);
                    total -= ((Item) item).getValue();
                    totalText.setText(total+"");
                    listView.setItems(obList);
                } else {
                    alert.close();
                }

            }
        });
        listView.setItems(obList);
        return listView;
    }

    private Node addTotal() {
        HBox hb = new HBox();
        hb.setSpacing(5);
        Text label = new Text("Total:");
        totalText = new Text("0");
        hb.getChildren().addAll(label, totalText);
        return hb;
    }

    private Node addConfirm() {
        HBox hb = new HBox();
        hb.setSpacing(15);
        hb.setPadding(new Insets(5, 0, 10, 0));
        hb.setAlignment(Pos.BASELINE_CENTER);
        Button btt = new Button("confirmar");
        btt.setOnAction((e) -> {
            //todo check if it is valid values
            section.setDescription(descTf.getText());
            section.setValue(total);
            callback.accept(section);
        });

        hb.getChildren().add(btt);
        return hb;
    }


}
