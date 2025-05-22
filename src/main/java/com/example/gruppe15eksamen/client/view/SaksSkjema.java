/**
 * @Author Severin Waller Sørensen
 */

package com.example.gruppe15eksamen.client.view;

import java.time.LocalDateTime;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import com.example.gruppe15eksamen.common.Kategori;
import com.example.gruppe15eksamen.common.Prioritet;

public class SaksSkjema {
    
    // Hovedpanel
    GridPane hovedPanel;

    // Felter for inndata:
    TextField tittel, rapportør;
    TextArea beskrivelsesfelt;
    ComboBox<Prioritet> prioritet;
    ComboBox<Kategori> kategori;
    GridPane knappPanel = new GridPane();

    // Knapper
    Button btnOpprett, btnAvbryt;



    // Konstruktør
    public SaksSkjema() {
        hovedPanel = new GridPane();
        hovedPanel.setHgap(5);
        hovedPanel.setVgap(5);
        hovedPanel.setPadding(new Insets(20,10,10,20));
        hovedPanel.setAlignment(Pos.TOP_LEFT);

        tittel = new TextField();
        tittel.setPromptText("tittel");

        beskrivelsesfelt = new TextArea();
        beskrivelsesfelt.setPromptText("beskrivelse");
        beskrivelsesfelt.setWrapText(true);
        beskrivelsesfelt.setPrefRowCount(5);

        prioritet = new ComboBox<>();
        prioritet.getItems().addAll(Prioritet.values());

        kategori = new ComboBox<>();
        kategori.getItems().addAll(Kategori.values());

        // Rapportør skal fylles inn automatisk
        // (settes til innlogget sitt brukernavn)
        rapportør = new TextField();
        rapportør.setEditable(false);
        rapportør.getStyleClass().add("saksskjema-rapportor");

        btnOpprett = new Button("Opprett");
        btnOpprett.getStyleClass().add("knapp-opprett");

        btnAvbryt = new Button("Avbryt");
        btnAvbryt.getStyleClass().add("knapp-avbryt");

        hovedPanel.add(new Label("Tittel"), 0, 0);
        hovedPanel.add(tittel, 0, 1);
        hovedPanel.add(new Label("Beskrivelse"), 0, 2);
        hovedPanel.add(beskrivelsesfelt, 0, 3);
        hovedPanel.add(new Label("Prioritet"), 0, 4);
        hovedPanel.add(prioritet, 0, 5);
        hovedPanel.add(new Label("Kategori"), 0, 6);
        hovedPanel.add(kategori, 0, 7);
        hovedPanel.add(new Label("Rapportør"), 0, 8);
        hovedPanel.add(rapportør, 0, 9);

        // Legge til knappene i GridPane(s). (+ styling)
        // Litt "rotete" måte, bør endres!
        knappPanel.setHgap(10);
        knappPanel.add(btnAvbryt,0, 0);
        knappPanel.add(btnOpprett, 1,0);
        hovedPanel.add(knappPanel, 0, 10);
    }
    
    // get-metode for hovedPanel (skjema)
    public GridPane getSaksSkjema() { return hovedPanel; }

    // get-metoder for input-felt
    public String getTittel() { return tittel.getText(); }
    public String getBeskrivelse() { return beskrivelsesfelt.getText(); }
    public Prioritet getPrioritet() { return prioritet.getValue(); }
    public Kategori getKategori() { return kategori.getValue(); }
    public String getRapportør() { return rapportør.getText(); }

    // get-metode for rapportør-felt (brukes for å automatisk fylle inn brukernavn)
    public TextField getRapportørFelt() {
        return rapportør;
    }

    // get-metode for "Opprett"-knapp og "avbryt"-knapp
    public Button getBtnOpprett() { return btnOpprett; }
    public Button getBtnAngre() { return btnAvbryt; }

    // set-metode for Rapportør (skal fylles inn automatisk)
    public void setRapportør(String navn) { rapportør.setText(navn); }

}
