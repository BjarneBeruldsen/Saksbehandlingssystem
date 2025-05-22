// Author: Severin Waller Sørensen

package com.example.gruppe15eksamen.client.view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class LederView {
    
    BorderPane mainPanel;

    // Konstruktør
    public LederView() {
        String temp = "DETTE SKAL BORT";
    }

    private Node lederPanel() {
        mainPanel = new BorderPane();
        mainPanel.setStyle("-fx-background-color:rgb(255, 255, 255)");;
        
        return mainPanel;
    }

    public Node visLederPanel() {
        return lederPanel();
    }

}
