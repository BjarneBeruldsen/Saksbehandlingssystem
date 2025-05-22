/**
 * Sak klassen som representerer en sak i saksbehandlingssystemet.
 * En Sak har informasjon om tittel, beskrivelse, prioritet, kategori, status, rapportør, mottaker,
 * opprettelsestidspunkt og oppdatert tidspunkt, samt kommentarer fra utvikler og tester.
 * @author Laurent Zogaj, Bjarne Beruldsen & Abdinasir Ali
 */
package com.example.gruppe15eksamen.common;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Sak implements Serializable {
    private static final long serialVersionUID = 1L;

    // Instansvariabler
    protected int sakID;
    private String tittel;
    private String beskrivelse;
    private Prioritet prioritet;
    private Kategori kategori;
    private Status status;
    private String rapportør;
    private String mottaker;
    private String utviklerKommentar;
    private String testerTilbakemelding;
    private LocalDateTime tidsstempel;
    private LocalDateTime oppdatertTidspunkt;

    /**
     * Konstruktør for å lage en komplett sak.
     * @param sakID                 Saks-ID
     * @param tittel                Sakens tittel
     * @param beskrivelse           Beskrivelse av saken
     * @param prioritet             Prioritet på saken
     * @param kategori              Kategori for saken
     * @param status                Status for saken
     * @param rapportør             Navn på rapportør
     * @param mottaker              Navn på mottaker
     * @param utviklerKommentar     Kommentar fra utvikler
     * @param testerTilbakemelding  Tilbakemelding fra tester
     * @param tidsstempel           Tidspunkt saken ble opprettet
     * @param oppdatertTidspunkt    Tidspunkt saken sist ble oppdatert
     */
    public Sak(int sakID, String tittel, String beskrivelse, Prioritet prioritet, Kategori kategori, Status status,
               String rapportør, String mottaker, String utviklerKommentar, String testerTilbakemelding,
               LocalDateTime tidsstempel, LocalDateTime oppdatertTidspunkt)
    {
        setSakID(sakID);
        setTittel(tittel);
        setBeskrivelse(beskrivelse);
        setPrioritet(prioritet);
        setKategori(kategori);
        setStatus(status);
        setRapportør(rapportør);
        setMottaker(mottaker);
        setUtviklerKommentar(utviklerKommentar);
        setTesterTilbakemelding(testerTilbakemelding);
        setTidsstempel(tidsstempel);
        setOppdatertTidspunkt(oppdatertTidspunkt);
    }

    /**
     * Konstruktør for å lage en ny sak.
     * @param tittel        Sakens tittel
     * @param beskrivelse   Beskrivelse av saken
     * @param prioritet     Prioritet på saken
     * @param kategori      Kategori for saken
     * @param rapportør     Navn på rapportør
     */
    public Sak(String tittel, String beskrivelse, Prioritet prioritet,
               Kategori kategori, String rapportør) {
        this(0, tittel, beskrivelse, prioritet, kategori, Status.INNSENDT,
                rapportør, "Ikke satt", "", "", LocalDateTime.now(), LocalDateTime.now());
    }

    // Tom konstruktør for serialisering
    public Sak() {
    }

    // Gettere og settere for utviklerKommentar
    public String getUtviklerKommentar() {
        return utviklerKommentar;
    }

    public void setUtviklerKommentar(String utviklerKommentar) {
        this.utviklerKommentar = utviklerKommentar;
    }

    // Gettere og settere for testerTilbakemelding
    public String getTesterTilbakemelding() {
        return testerTilbakemelding;
    }

    public void setTesterTilbakemelding(String testerTilbakemelding) {
        this.testerTilbakemelding = testerTilbakemelding;
    }

    // Gettere og settere for andre attributter (uendret)
    public int getSakID() { return sakID; }
    public void setSakID(int sakID) {
        if (sakID < 0) {
            throw new IllegalArgumentException("SakID kan ikke være negativ.");
        }
        this.sakID = sakID;
    }

    public String getTittel() { return tittel; }
    public void setTittel(String tittel) {
        if (tittel == null || tittel.trim().isEmpty()) {
            throw new IllegalArgumentException("Tittel kan ikke være tom.");
        }
        if (tittel.length() > 100) {
            throw new IllegalArgumentException("Tittel kan ikke være lengre enn 100 tegn.");
        }
        this.tittel = tittel.trim();
    }

    public String getBeskrivelse() { return beskrivelse; }
    public void setBeskrivelse(String beskrivelse) {
        if (beskrivelse == null || beskrivelse.trim().isEmpty()) {
            throw new IllegalArgumentException("Beskrivelse kan ikke være tom.");
        }
        this.beskrivelse = beskrivelse.trim();
    }

    public Prioritet getPrioritet() { return prioritet; }
    public void setPrioritet(Prioritet prioritet) {
        if (prioritet == null) {
            throw new IllegalArgumentException("Prioritet må settes.");
        }
        this.prioritet = prioritet;
    }

    public Kategori getKategori() { return kategori; }
    public void setKategori(Kategori kategori) { this.kategori = kategori; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status kan ikke være null.");
        }
        this.status = status;
    }

    public String getRapportør() { return rapportør; }
    public void setRapportør(String rapportør) {
        this.rapportør = rapportør;
    }

    public String getMottaker() { return mottaker; }
    public void setMottaker(String mottaker) { this.mottaker = mottaker; }

    public LocalDateTime getTidsstempel() { return tidsstempel; }
    public void setTidsstempel(LocalDateTime tidsstempel) { this.tidsstempel = tidsstempel; }

    public LocalDateTime getOppdatertTidspunkt() { return oppdatertTidspunkt; }
    public void setOppdatertTidspunkt(LocalDateTime oppdatertTidspunkt) { this.oppdatertTidspunkt = oppdatertTidspunkt; }

    /**
     * toString metode
     * @return tekstlig representasjon av Sak
     */
    @Override
    public String toString() {
        return "Sak{" +
                "sakID=" + sakID +
                ", tittel='" + tittel + '\'' +
                ", beskrivelse='" + beskrivelse + '\'' +
                ", prioritet=" + prioritet +
                ", kategori=" + kategori +
                ", status=" + status +
                ", rapportør='" + rapportør + '\'' +
                ", mottaker='" + mottaker + '\'' +
                ", utviklerKommentar='" + utviklerKommentar + '\'' +
                ", testerTilbakemelding='" + testerTilbakemelding + '\'' +
                ", tidsstempel=" + tidsstempel +
                ", oppdatertTidspunkt=" + oppdatertTidspunkt +
                '}';
    }
}