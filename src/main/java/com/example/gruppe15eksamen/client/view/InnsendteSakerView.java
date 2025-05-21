// Author: Severin Waller Sørensen

/* Denne filen
 * 
 */


/* --------------------------------------------------------------
 * KILDE1: (YouTube: thenewboston)
 *   url: https://www.youtube.com/watch?v=mtdlX2NMy4M 
 * 
 * KOMMENTAR:
 *   Brukte videoen til å lære om TableViews, hjelp med å
 *   skrive pseudokode/struktur og for inspirasjon.
 * 
 * KILDE2: stackoverflow
 *   url: https://stackoverflow.com/questions/44017673/javafx-tableview-get-selected-item-column-values-string-into-textfields
 * 
 * KOMMENTAR:
 *   Brukte denne som hjelp/guide for hvordan håndtere/registrere 
 *   hvilken rad som er valgt i tabellen.
 * 
 * --------------------------------------------------------------
 * NB! UtviklerSakerView.java og LederSakerView.java er begge
 * basert på denne filen, (dvs. bygger indirekte på samme kilder)
 * --------------------------------------------------------------
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
import com.example.gruppe15eksamen.common.Status;

public class InnsendteSakerView {

    TableView<Sak> saksTabell;
    VBox innsendteSaker;
    Label lblSakIdOverskrift;
    Label lblSakIdValg;
    ComboBox<Status> status;
    TextField searchField;
    Button searchBtn;
    Button btnSetStatus;
    Sak valgtSak;
    GridPane searchPane;
    GridPane statusPane;

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

        // tidsstempel-kolonne (sist endret)
        TableColumn<Sak, String> tidsEndringKolonne = new TableColumn<>("sist endret");
        tidsEndringKolonne.setMinWidth(150);
        tidsEndringKolonne.setCellValueFactory(new PropertyValueFactory<>("oppdatertTidspunkt"));

        saksTabell = new TableView<>();
        saksTabell.setItems(getSaker());
        saksTabell.getColumns().addAll(idKolonne, tittelKolonne, beskrivelseKolonne,
                                       prioritetKolonne, kategoriKolonne, statusKolonne, 
                                       rapportørKolonne, mottakerKolonne, tidsstempelKolonne, 
                                       tidsEndringKolonne);

        // Panel som inneholder nødvendige element for å søke
        searchPane = new GridPane();
        searchField = new TextField();
        searchField.setPromptText("oppgi informasjon ");
        searchBtn = new Button("Søk");
        searchPane.add(searchField, 1, 0);
        searchPane.add(searchBtn, 2, 0);
        searchPane.setAlignment(Pos.CENTER_RIGHT);

        // Panel som inneholder nødvendig element for å endre Status til sak
        statusPane = new GridPane();
        lblSakIdOverskrift = new Label("Sak: ");
        lblSakIdOverskrift.getStyleClass().add("valgtSak-tabell");
        lblSakIdValg = new Label("");
        status = new ComboBox<>();
        status.getItems().addAll(Status.values());
        btnSetStatus = new Button("Oppdater status");
        statusPane.add(lblSakIdOverskrift, 0, 0);
        statusPane.add(lblSakIdValg, 0, 1);
        statusPane.add(status, 1, 0);
        statusPane.add(btnSetStatus, 2, 0);

        // Lytter for å 
        // JavaFX krevde tre parameter, kunne ikke ha kun selectedSak.
        // ( oIIB = observable som ikke er i bruk | oVIIB = oldValue som ikke er i bruk )
        saksTabell.getSelectionModel().selectedItemProperty().addListener((oIU, oVIU, selectedSak) -> {
            if (selectedSak != null) {
                valgtSak = selectedSak;
                lblSakIdValg.setText("" + valgtSak.getSakID());
            }
        });

        innsendteSaker = new VBox();
        innsendteSaker.getChildren().addAll(searchPane, saksTabell, statusPane);

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
