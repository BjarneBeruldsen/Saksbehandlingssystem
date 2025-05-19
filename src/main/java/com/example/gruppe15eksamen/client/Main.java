package com.example.gruppe15eksamen.client;

import com.example.gruppe15eksamen.client.controller.SakController;
import com.example.gruppe15eksamen.common.Sak;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {
    private SakController sakController;
    @Override
    public void start(Stage stage) {
        sakController = new SakController();
        Scene scene = new Scene(new Label("Hei, verden!"), 300, 200);
        stage.setTitle("Testapp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
