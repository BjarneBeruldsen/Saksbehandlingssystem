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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import com.example.gruppe15eksamen.common.Kategori;
import com.example.gruppe15eksamen.common.Prioritet;
import com.example.gruppe15eksamen.common.Sak;
import com.example.gruppe15eksamen.common.Status;

public class LederSakerView {

    TableView<Sak> saksTabell;
    VBox alleSaker;
    Label lblSakIdOverskrift;
    Label lblSakIdValg;
    //combobox for valg av mottaker med utviklers brukernavn
    ComboBox<String> cbUtviklere = new ComboBox<>();
    Button btLeggTilMottaker = new Button("Legg til");
    TextField searchField;
    Button searchBtn;
    Button btnSetUtvikler;
    Sak valgtSak;
    GridPane searchPane;
    GridPane velgUtviklerPane;

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

        // tidsstempel-kolonne (sist endret)
        TableColumn<Sak, String> tidsEndringKolonne = new TableColumn<>("sist endret");
        tidsEndringKolonne.setMinWidth(150);
        tidsEndringKolonne.setCellValueFactory(new PropertyValueFactory<>("oppdatertTidspunkt"));

        saksTabell = new TableView<>();
//        saksTabell.setItems(getSaker());
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
        velgUtviklerPane = new GridPane();
        lblSakIdOverskrift = new Label("Sak: ");
        lblSakIdOverskrift.getStyleClass().add("valgtSak-tabell");
        lblSakIdValg = new Label("");
        btnSetUtvikler = new Button("Bekreft");
        velgUtviklerPane.add(lblSakIdOverskrift, 0, 0);
        velgUtviklerPane.add(lblSakIdValg, 0, 1);
        velgUtviklerPane.add(cbUtviklere, 1, 0);
        velgUtviklerPane.add(btnSetUtvikler, 2, 0);

        // Lytter for å
        // JavaFX krevde tre parameter, kunne ikke ha kun selectedSak.
        // ( oIIB = observable som ikke er i bruk | oVIIB = oldValue som ikke er i bruk )
        saksTabell.getSelectionModel().selectedItemProperty().addListener((oIU, oVIU, selectedSak) -> {
            if (selectedSak != null) {
                valgtSak = selectedSak;
                lblSakIdValg.setText("" + valgtSak.getSakID());
            }
        });

        alleSaker = new VBox();
        alleSaker.setAlignment(Pos.CENTER);
        alleSaker.getChildren().addAll(searchPane, saksTabell, velgUtviklerPane,
                btLeggTilMottaker, cbUtviklere);
    }

    // HARDKODET TEMP-liste med saker
//    public ObservableList<Sak> getSaker() {
//        ObservableList<Sak> saker = FXCollections.observableArrayList();
//        saker.add(new Sak("sak1", "Beskrivelse1", Prioritet.HØY, Kategori.UI_FEIL,  ""));
//        saker.add(new Sak ("sak2", "beskrivelse2", Prioritet.HØY, Kategori.UI_FEIL, ""));
//        saker.add(new Sak ("sak3", "beskrivelse3", Prioritet.HØY, Kategori.UI_FEIL, ""));
//
//        return saker;
//    }

    //metode som returnerer sakstabellen


    public TableView<Sak> getSaksTabell() {
        return saksTabell;
    }

    // get-metode som returnerer tabell/oversikt over alle saker
    public Node getAlleSaker() { return alleSaker; }

    //get metode for å hente utvikler combobox
    public ComboBox<String> getCbUtviklere() {
        return cbUtviklere;
    }

    public Button getBtLeggTilMottaker() {
        return btLeggTilMottaker;
    }
}
