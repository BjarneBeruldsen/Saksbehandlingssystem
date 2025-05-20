package com.example.gruppe15eksamen.client;

import com.example.gruppe15eksamen.client.controller.SakController;
import com.example.gruppe15eksamen.server.util.DatabaseUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.sql.Connection;

    public class Main extends Application {
        @Override
        public void start(Stage stage) {
            SakController sakController = new SakController();
            String tekst;
            try (Connection conn = DatabaseUtil.getConnection()) {
                tekst = "Database OK!";
                System.out.println("Database OK!");
            } catch (Exception e) {
                tekst = "Database ikke OK!";
                System.out.println("Database ikke OK!");
                e.printStackTrace();
            }

            Label statusLabel = new Label(tekst);
            Scene scene = new Scene(statusLabel, 300, 100);

            stage.setTitle("Database Status");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }