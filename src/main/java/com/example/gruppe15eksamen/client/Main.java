/* Authors: Bjarne Beruldsen, Laurent Zogaj, Severin Waller Sørensen & Abdinasir Ali
 * 
 */

package com.example.gruppe15eksamen.client;

import com.example.gruppe15eksamen.client.controller.SakController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

        //Opprette Controlleren og få første scene
        SakController sakController = new SakController(stage);

        // Sett scene, tittel og show()
        Scene scene = sakController.getStartScene();
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Saksbehandling");
        stage.show();
    }

    // Launch / Main-method
    public static void main(String[] args) {
        launch(args);
    }
}