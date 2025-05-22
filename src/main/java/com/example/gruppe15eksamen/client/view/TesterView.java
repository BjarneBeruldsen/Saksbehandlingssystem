/**
 * TesterView er ansvarlig for å vise hovedpanelet for brukere med rollen "Tester".
 * 
 * @Author Severin Waller Sørensen
 */

package com.example.gruppe15eksamen.client.view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class TesterView {
    
    BorderPane mainPanel;

    // Konstruktør
    public TesterView() {
        String temp = "DETTE SKAL BORT";
    }

    private Node testerPanel() {
        mainPanel = new BorderPane();
        mainPanel.setStyle("-fx-background-color:rgb(255, 255, 255)");;
        return mainPanel;
    }

    /** @return Et Node-objekt for testerens brukergrensesnitt. */
    public Node visTesterPanel() {
        return testerPanel();
    }

}
