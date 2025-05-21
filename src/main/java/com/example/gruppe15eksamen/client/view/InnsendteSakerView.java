// Author: Severin Waller Sørensen

/*
 * 
 */


/* KILDE: (YouTube: thenewboston)
 *   url: https://www.youtube.com/watch?v=mtdlX2NMy4M 
 * KOMMENTAR:
 *   Brukte videoen til å lære om TableViews, hjelp med å
 *   skrive pseudokode/struktur og for inspirasjon.
 */


package com.example.gruppe15eksamen.client.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.time.LocalDateTime;

import com.example.gruppe15eksamen.common.Kategori;
import com.example.gruppe15eksamen.common.Prioritet;
import com.example.gruppe15eksamen.common.Sak;

public class InnsendteSakerView {

    TableView<Sak> saksTabell;
    VBox innsendteSaker;

    public InnsendteSakerView() {
    
        // ID-kolonne
        TableColumn<Sak, String> idKolonne = new TableColumn<>("ID_");
        idKolonne.setMinWidth(60);
        idKolonne.setCellValueFactory(new PropertyValueFactory<>("sakID"));

        // Tittel-kolonne
        TableColumn<Sak, String> tittelKolonne = new TableColumn<>("Tittel");
        tittelKolonne.setMinWidth(100);
        tittelKolonne.setCellValueFactory(new PropertyValueFactory<>("tittel"));

        // Beskrivelse-kolonne
        TableColumn<Sak, String> beskrivelseKolonne = new TableColumn<>("Beskrivelse");
        beskrivelseKolonne.setMinWidth(200);
        beskrivelseKolonne.setCellValueFactory(new PropertyValueFactory<>("beskrivelse"));

        // Beskrivelse-kolonne
        TableColumn<Sak, String> prioritetKolonne = new TableColumn<>("Prioritet");
        prioritetKolonne.setMinWidth(60);
        prioritetKolonne.setCellValueFactory(new PropertyValueFactory<>("prioritet"));

        // Kategori-kolonne
        TableColumn<Sak, String> kategoriKolonne = new TableColumn<>("kategori");
        kategoriKolonne.setMinWidth(60);
        kategoriKolonne.setCellValueFactory(new PropertyValueFactory<>("kategori"));

        // Status-kolonne
        TableColumn<Sak, String> statusKolonne = new TableColumn<>("Status");
        statusKolonne.setMinWidth(100);
        statusKolonne.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Rapportør-kolonne
        TableColumn<Sak, String> rapportørKolonne = new TableColumn<>("Rapportør");
        rapportørKolonne.setMinWidth(100);
        rapportørKolonne.setCellValueFactory(new PropertyValueFactory<>("rapportør"));

        // Mottaker-kolonne
        TableColumn<Sak, String> mottakerKolonne = new TableColumn<>("Mottaker");
        mottakerKolonne.setMinWidth(100);
        mottakerKolonne.setCellValueFactory(new PropertyValueFactory<>("mottaker"));

        // tidsstempel-kolonne
        TableColumn<Sak, String> tidsstempelKolonne = new TableColumn<>("Opprettelses dato");
        tidsstempelKolonne.setMinWidth(150);
        tidsstempelKolonne.setCellValueFactory(new PropertyValueFactory<>("tidsstempel"));

        saksTabell = new TableView<>();
        saksTabell.setItems(getSaker());
        saksTabell.getColumns().addAll(idKolonne, tittelKolonne, beskrivelseKolonne,
                                       prioritetKolonne, kategoriKolonne, statusKolonne, 
                                       rapportørKolonne, mottakerKolonne, tidsstempelKolonne);

        innsendteSaker = new VBox();
        innsendteSaker.getChildren().add(saksTabell);

    }


    // HARDKODET TEMP-liste med saker
    public ObservableList<Sak> getSaker() {
        ObservableList<Sak> saker = FXCollections.observableArrayList();
        saker.add(new Sak("sak1", "Beskrivelse1", Prioritet.HØY, Kategori.UI_FEIL,  ""));
        saker.add(new Sak ("sak2", "beskrivelse2", Prioritet.HØY, Kategori.UI_FEIL, ""));
        saker.add(new Sak ("sak3", "beskrivelse3", Prioritet.HØY, Kategori.UI_FEIL, ""));
        
        return saker;
    }

    // get-metode for tabell over innsendte saker ("hovedpanel")
    public Node getInnsendteSaker() { return innsendteSaker; }
    
}
