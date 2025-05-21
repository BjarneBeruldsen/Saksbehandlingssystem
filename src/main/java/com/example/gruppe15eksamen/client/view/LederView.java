// Author: Severin Waller Sørensen

package com.example.gruppe15eksamen.client.view;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import static com.example.gruppe15eksamen.client.view.ViewKonstanter.VINDU_BREDDE;
import static com.example.gruppe15eksamen.client.view.ViewKonstanter.VINDU_HØYDE;
import com.example.gruppe15eksamen.common.Bruker;

public class LederView {
    
    BorderPane mainPanel;

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
