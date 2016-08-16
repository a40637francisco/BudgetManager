package mainApp;

import javaa.main.model.domain.Controller;
import javaa.main.views.IndexView;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {


    private static final String TITLE = "Budget Manager";
    private static final int WIDTH = 720; // mudar para ver o width do device and stuff
    private static final int HEIGHT = 720;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainApp(primaryStage);
        /*
        File dir = new File("nameoffolder");
        dir.mkdir(); */
        //test(primaryStage);
    }

    private void test(Stage primaryStage) {
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(addTest(), WIDTH, HEIGHT));

        Controller.getAllProducts(item -> System.out.println(item));

        primaryStage.show();
    }

    private class Car {
        @Override
        public String toString() {
            return "car";
        }
    }

    private Parent addTest() {
        VBox vb = new VBox();
        ArrayList<Car> l = new ArrayList<Car>();
        l.add(new Car());
        ComboBox<Car> tf = new ComboBox<>();
        tf.getItems().add(new Car());
        tf.getItems().add(new Car());
        tf.setPromptText("Escolher...");
        tf.setFocusTraversable(false);
        tf.setOnAction(event -> {
            System.out.println(tf.getValue());
        });
        vb.getChildren().add(tf);

        return vb;
    }

    private void mainApp(Stage primaryStage) {
        IndexView index = new IndexView();
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(index, WIDTH, HEIGHT));
        primaryStage.show();
    }


}
