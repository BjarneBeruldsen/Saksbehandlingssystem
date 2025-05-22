/**
 * @Author Severin Waller Sørensen
 */

package com.example.gruppe15eksamen.client.view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class UtviklerView {
    
    BorderPane mainPanel;

    // Kunstruktør
    public UtviklerView() {
        String temp = "DETTE SKAL BORT";
    }

    private Node utviklerPanel() {
        mainPanel = new BorderPane();
        mainPanel.setStyle("-fx-background-color:rgb(255, 255, 255)");;
        
        return mainPanel;
    }

    public Node visUtviklerPanel() {
        return utviklerPanel();
    }


}
