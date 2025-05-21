// Author: Severin Waller Sørensen

/* Denne filen ...
 * 
 * 
 * -- NB! lagde InnsendteSakerView.java først! --
 * Den er basert på kilder som er linket i den klassen.
 * Denne filen (LederSakerView.java) og UtviklerSakerView.java
 * er begge basert på InnsendteSakerView.java.
 * (dvs. disse to filene er indirekte basert på kildene
 *  som er oppgitt i InnsendteSakerView.java)
 */

package com.example.gruppe15eksamen.client.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import com.example.gruppe15eksamen.common.Kategori;
import com.example.gruppe15eksamen.common.Prioritet;
import com.example.gruppe15eksamen.common.Sak;

public class LederSakerView {

    TableView<Sak> saksTabell;
    VBox alleSaker;

    public LederSakerView() {

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

        alleSaker = new VBox();
        alleSaker.getChildren().add(saksTabell);

    }    

    // HARDKODET TEMP-liste med saker
    public ObservableList<Sak> getSaker() {
        ObservableList<Sak> saker = FXCollections.observableArrayList();
        saker.add(new Sak("sak1", "Beskrivelse1", Prioritet.HØY, Kategori.UI_FEIL,  ""));
        saker.add(new Sak ("sak2", "beskrivelse2", Prioritet.HØY, Kategori.UI_FEIL, ""));
        saker.add(new Sak ("sak3", "beskrivelse3", Prioritet.HØY, Kategori.UI_FEIL, ""));
        
        return saker;
    }

    // get-metode som returnerer tabell/oversikt over alle saker
    public Node getAlleSaker() { return alleSaker; }
   
}
