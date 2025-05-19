package com.example.gruppe15eksamen.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Label("Hei, verden!"), 300, 200);
        stage.setTitle("Testapp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
