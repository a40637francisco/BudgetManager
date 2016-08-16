package javaa.main.views;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Created on 14/08/2016.
 */
public class IndexView extends BorderPane {

    public IndexView() {
        this.setTop(addHeader());
        this.setLeft(addMenu());
        this.setRight(addStatsPane());
        this.setBottom(addFooter());
        //this.setCenter(new HBox());
    }

    private HBox addHeader() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);
        //add padding to top
        Node image = getImage();

        hBox.getChildren().addAll(image);

        return hBox;
    }

    private Node getImage() {
        try {
            Image image = new Image("logo.png");
            ImageView iv = new ImageView();
            iv.setImage(image);
            iv.setFitWidth(300);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setCache(true);
            return iv;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new Text("Construa");
    }

    private VBox addMenu() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(55, 12, 15, 12));
        vBox.setSpacing(5);

        //Menu
        HBox[] menu = new HBox[3];
        menu[0] = addMenuOption("Novo Orçamento", () -> {
            newBudgetWindow();
        });
        menu[1] = addMenuOption("Produtos", () -> {
            productsWindow();
        });
        menu[2] = addMenuOption("Histórico Orçamento", () -> {
            budgetHistoryWindow();
        });

        for (int i = 0; i < menu.length; i++) {
            vBox.getChildren().add(menu[i]);
        }

        return vBox;
    }

    private void budgetHistoryWindow() {
        Stage newBudgetStage = new Stage();
        newBudgetStage.setTitle("Histórial Orçamentos");
        newBudgetStage.setResizable(false);
        newBudgetStage.initModality(Modality.APPLICATION_MODAL);
        newBudgetStage.setHeight(720); // change
        newBudgetStage.setWidth(720);
        Scene scene = new Scene(new BudgetHistoryView(), 720, 720);
        newBudgetStage.setScene(scene);
        newBudgetStage.show();
    }

    private void productsWindow() {
        Stage newBudgetStage = new Stage();
        newBudgetStage.setTitle("Produtos");
        newBudgetStage.setResizable(false);
        //newBudgetStage.initModality(Modality.APPLICATION_MODAL);
        newBudgetStage.setHeight(720); // change
        newBudgetStage.setWidth(720);
        Scene scene = new Scene(new ItemsListView(), 720, 720);
        newBudgetStage.setScene(scene);
        newBudgetStage.show();
    }

    private void newBudgetWindow() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Novo Orçamento");
        dialog.setHeaderText("Orçamento");
        dialog.setContentText("Nome do orçamento:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name ->{
            System.out.println("name: " + name);

            Stage newBudgetStage = new Stage();
            newBudgetStage.setTitle("Orçamento");
            newBudgetStage.setResizable(false);
            newBudgetStage.setHeight(720); // change
            newBudgetStage.setWidth(720);

            Scene scene = new Scene(new BudgetView(name, newBudgetStage), 720, 720);
            newBudgetStage.setScene(scene);
            newBudgetStage.show();
        });

    }

    private HBox addMenuOption(String s, Runnable callback) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);

        Text label = new Text(s);
        label.setFont(new Font("Helvitica", 26));

        Button button = new Button("->");
        button.setOnAction(event -> {
            callback.run();
        });

        hBox.getChildren().addAll(label, button);

        return hBox;
    }

    private TilePane addStatsPane() {
        TilePane  stats = new TilePane ();
        stats.setPadding(new Insets(55, 0, 15, 0));
        stats.setVgap(4);
        stats.setHgap(4);
        stats.setPrefColumns(1);
        //stats.setStyle("-fx-background-color: DAE6F3;");

        Text statsText[] = new Text[4];
        for (int i = 0; i < statsText.length; i++) {
            statsText[i] = new Text("todo later...");
            stats.getChildren().add(statsText[i]);
        }

        return stats;
    }

    private Node addFooter() {
        HBox hBox = new HBox();

        Text t = new Text("Developed by: franmcod");

        hBox.getChildren().addAll(t);
        return hBox;
    }

}
