package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource("/view/Calculator.fxml");
        fxmlLoader.setLocation(url);
        BorderPane borderPane = fxmlLoader.load();
        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("CalculatorFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }



}
