package javaa.main.views;

import javaa.main.model.domain.Budget;
import javaa.main.model.domain.Item;
import javaa.main.model.domain.Items;
import javaa.main.model.domain.Section;
import javaa.main.views.util.AddItemLayout;
import javaa.main.views.util.AddSectionLayout;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Created on 14/08/2016.
 */
public class BudgetView extends VBox {

    private String name;
    private Stage window;
    private Budget budget = new Budget();

    public void setBudget(Budget b) {
        this.budget = b;
        name = budget.getName();
        setItemsListFromBudget();
    }

    private void setItemsListFromBudget() {
        for (Items item : budget.getItems())
            addItemToList(item);
    }

    private void addItemToList(Items item) {
        obList.add(item);
    }

    public BudgetView() {
        addControls();
    }

    public BudgetView(String name, Stage parent) {
        this.name = name;
        this.window = parent;
        addControls();
    }

    private void addControls() {
        this.setSpacing(10);
        this.getChildren().add(addTitle());
        this.getChildren().add(addAddress());
        this.getChildren().add(addNewItems());
        this.getChildren().add(addItemsListView());
        this.getChildren().add(addNotes());
        this.getChildren().add(addConfirmButton());
    }

    private Node addConfirmButton() {
        HBox hb = new HBox();
        hb.setSpacing(15);
        hb.setPadding(new Insets(5, 0, 10, 0));
        hb.setAlignment(Pos.BASELINE_CENTER);
        Button btt = new Button("confirmar");
        btt.setOnAction((e) -> {
            System.out.println(budget);
            window.close();
        });

        hb.getChildren().add(btt);
        return hb;
    }

    private Node addNotes() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(5, 12, 10, 12));
        hBox.setSpacing(10);

        Text label = new Text("Notas:");
        label.setFont(new Font("Helvetica", 18));

        this.getChildren().add(label);

        TextArea notes = new TextArea();
        hBox.getChildren().add(notes);

        return hBox;
    }

    private Node addTitle() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);

        Text title = new Text("Orçamento: " + name);
        title.setFont(new Font("Helvetic", 36));

        hBox.getChildren().add(title);

        return hBox;
    }

    private Node addAddress() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);

        Text label = new Text("Morada:");
        label.setFont(new Font("Helvetic", 24));

        TextField addressInput = new TextField();
        if (budget != null) {
            addressInput.setText(budget.getAddress());
        }

        hBox.getChildren().addAll(label, addressInput);
        return hBox;
    }

    private Node addNewItems() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);

        addItemControl(hBox);
        addSectionControl(hBox);
        return hBox;
    }

    private void addItemControl(HBox hBox) {
        final boolean[] open = {false};
        Button button = new Button("+");
        button.setFont(new Font("Helvetica", 24));
        button.setOnAction(e->{
            if(!open[0]) {
                open[0] = true;
                Stage s = new Stage();
                s.setTitle("Item");
                s.setScene(new Scene(new AddItemLayout((item) -> obList.add(item)), AddItemLayout.WIDTH, AddItemLayout.HEIGHT));
                s.setWidth(AddItemLayout.WIDTH);
                s.setHeight(AddItemLayout.HEIGHT);
                s.initModality(Modality.APPLICATION_MODAL);
                s.setOnCloseRequest(event -> open[0] = false);
                s.show();
            }
        });
        Text label = new Text("adicionar item");
        label.setFont(new Font("Helvetic", 24));

        hBox.getChildren().addAll(button, label);
    }

    private void addSectionControl(HBox hBox) {
        Button button = new Button("+");
        button.setFont(new Font("Helvetica", 24));
        button.setOnAction(e->{
            Stage s = new Stage();
            s.setTitle("Secção");
            s.setScene(new Scene(new AddSectionLayout((item)->{obList.add(item); }), AddSectionLayout.WIDTH, AddSectionLayout.HEIGHT));
            s.setWidth(AddSectionLayout.WIDTH);
            s.setHeight(AddSectionLayout.HEIGHT);
            s.initModality(Modality.APPLICATION_MODAL);
            s.show();
        });
        Text label = new Text("adicionar secção");
        label.setFont(new Font("Helvetica", 24));

        hBox.getChildren().addAll(button, label);
    }

    private ListView<Items> listView;
    private ObservableList<Items> obList = FXCollections.observableArrayList();

    private Node addItemsListView() {
        listView = new ListView<>();

        listView.setCellFactory(new Callback<ListView<Items>, ListCell<Items>>() {
            @Override
            public ListCell<Items> call(ListView<Items> param) {
                ListCell<Items> cell = new ListCell<Items>() {

                    @Override
                    protected void updateItem(Items item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null) {
                            //check if its section too, not OOP :´(
                            if (item instanceof Item)
                                setGraphic(itemLayout((Item) item));
                            else if (item instanceof Section) {
                                setGraphic(sectionLayout((Section) item));
                            }
                        }
                    }
                };
                return cell;
            }
        });

        listView.setItems(obList);
        return listView;
    }

    private Node itemLayout(Item item) {
        HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setPadding(new Insets(5, 0, 0, 5));

        Text t = new Text("-");
        t.setFont(new Font("Helvetica", 18));
        t.setStyle("font-weight: bold;");

        Text text = new Text(item.toString());
        text.setFont(new Font("Helvetica", 19));

        hb.getChildren().addAll(t, text);
        return hb;
    }

    private Node sectionLayout(Section section) {
        HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setPadding(new Insets(5, 0, 0, 5));

        Text t = new Text("-");
        t.setFont(new Font("Helvetica", 18));
        t.setStyle("font-weight: bold;");
        hb.getChildren().add(t);

        Text sec = new Text(section.getDescription() + "    " + section.getValue() + "€");
        sec.setFont(new Font("sans-serif", 19));
        hb.getChildren().add(sec);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.BASELINE_LEFT);
        for (Item item : section.getItems()) {
            vBox.getChildren().add(itemLayout(item));
        }

        hb.getChildren().add(vBox);
        return hb;
    }


}
