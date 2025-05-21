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
import com.example.gruppe15eksamen.client.view.SakView;
import com.example.gruppe15eksamen.common.Bruker;

public class TesterView {
    
    BorderPane mainPanel;

    public TesterView() {
        String temp = "DETTE SKAL BORT";
    }

    private Node testerPanel() {
        mainPanel = new BorderPane();
        mainPanel.setStyle("-fx-background-color:rgb(182, 18, 207)");;
        return mainPanel;
    }

    public Node visTesterPanel() {
        return testerPanel();
    }

}
