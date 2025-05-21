// Authors: Bjarne Beruldsen, Laurent Zogaj & Severin Waller Sørensen

/* Denne filen ...
 * 
 */

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

            //Opprette Controlleren og få første scene
            SakController sakController = new SakController(stage);

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
            Scene sceneDB = new Scene(statusLabel, 300, 100);


            // Sett scene, tittel og show()
            stage.setScene(sakController.getStartScene());
            stage.setTitle("Saksbehandling");
            stage.show();
        }

        
        // Launch / Main-method
        public static void main(String[] args) {
            launch(args);
        }
    }