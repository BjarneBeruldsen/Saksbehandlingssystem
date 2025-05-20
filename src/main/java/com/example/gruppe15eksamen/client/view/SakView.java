
/*Inneholder UI/GUI i javax for visning av brukergrensesnitt
 * for de ulike brukerne.. En metode får egenspesifiserte GUI for
 * Ledere, Testere og Utviklere? Evt. egen view */

package com.example.gruppe15eksamen.client.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import static com.example.gruppe15eksamen.client.view.ViewKonstanter.VINDU_BREDDE;
import static com.example.gruppe15eksamen.client.view.ViewKonstanter.VINDU_HØYDE;

public class SakView {
    // Variabler
    BorderPane hovedPanel; 
    GridPane hjemPanel;
    TextField tittel;
    Label lblOverskrift = new Label("Logg Inn");
    Label lblStatus = new Label("STATUS");

    // Konstruktør
    public SakView() {
        lagPanel();
    }

    // Lage panel
    public void lagPanel() {
        hovedPanel = new BorderPane();
        hovedPanel.setTop(lagTopPanel());
        hovedPanel.setCenter(lagHjemPanel());
        hovedPanel.setBottom(lagBunnPanel());
        hovedPanel.setPrefHeight(VINDU_BREDDE);
        hovedPanel.setPrefWidth(VINDU_HØYDE);
    }


    // Lage TopPanel
    private Node lagTopPanel() {
        // Opprette panel og legge til styling
        HBox topPanel = new HBox();
        topPanel.setStyle("-fx-background-color: #d3d3d3; -fx-border-width: 2px; -fx-border-color: black");
        topPanel.setSpacing(10);
        topPanel.setAlignment(Pos.CENTER);

        // Endrer størrelse på label og legger til status (ikke gjort/ferdig enda)
        Font fonten = Font.font("COPPERPLATE GOTHIC BOLC, 24");
        lblOverskrift.setFont(fonten);
        topPanel.getChildren().add(lblOverskrift);

        return topPanel;
    }

    // Lage HjemPanel
    private Node lagHjemPanel() {
        hjemPanel = new GridPane();
        hjemPanel.setAlignment(Pos.CENTER);
        hjemPanel.setVgap(10);
        return hjemPanel;
    }


    // Lage BunnPanel
    private Node lagBunnPanel() {
        // Oppretter panel og legger til styling
        HBox bunnPanel = new HBox();
        bunnPanel.setStyle("-fx-background-color: #d3d3d3; -fx-border-widht: 2px; -fx-border-color: black");
        bunnPanel.setSpacing(10);
        bunnPanel.setAlignment(Pos.CENTER);

        // Endrer størrelse på label og legger til status (ikke gjort/ferdig enda)
        Font fonten = Font.font("COPPERPLATE GOTHIC BOLC, 24");
        lblStatus.setFont(fonten);
        bunnPanel.getChildren().add(lblStatus);

        return bunnPanel;
    }

    // Buttons

    // getMetoder
    public BorderPane getHovedPanel() {
        return hovedPanel;
    }
}
