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

    /**
     * Henter utviklerKommentar
     * @return utviklerKommentar
     */
    public String getUtviklerKommentar() {
        return utviklerKommentar;
    }

    /**
     * Setter utviklerKommentar
     * @param utviklerKommentar Kommentar fra utvikler
     */
    public void setUtviklerKommentar(String utviklerKommentar) {
        this.utviklerKommentar = utviklerKommentar;
    }

    /**
     * Henter testerTilbakemelding
     * @return testerTilbakemelding
     */
    public String getTesterTilbakemelding() {
        return testerTilbakemelding;
    }

    /**
     * Setter testerTilbakemelding
     * @param testerTilbakemelding Tilbakemelding fra tester
     */
    public void setTesterTilbakemelding(String testerTilbakemelding) {
        this.testerTilbakemelding = testerTilbakemelding;
    }

    /**
     * Henter sakID
     * Validering, sakID kan ikke være negativ.
     * @return sakID
     */
    public int getSakID() { return sakID; }
    public void setSakID(int sakID) {
        if (sakID < 0) {
            throw new IllegalArgumentException("SakID kan ikke være negativ.");
        }
        this.sakID = sakID;
    }
    
    /**
     * Henter tittel
     * @return tittel
     */
    public String getTittel() { return tittel; }

    /**
     * Setter for tittel
     * Validering, tittel kan ikke være null eller tom.
     * @param tittel Tittel på saken
     */
    public void setTittel(String tittel) {
        if (tittel == null || tittel.trim().isEmpty()) {
            throw new IllegalArgumentException("Tittel kan ikke være tom.");
        }
        if (tittel.length() > 100) {
            throw new IllegalArgumentException("Tittel kan ikke være lengre enn 100 tegn.");
        }
        this.tittel = tittel.trim();
    }

    /**
     * Henter beskrivelse
     * @return beskrivelse
     */
    public String getBeskrivelse() { return beskrivelse; }

    /**
     * Setter for beskrivelse
     * @param beskrivelse Beskrivelse av saken
     */
    public void setBeskrivelse(String beskrivelse) {
        if (beskrivelse == null || beskrivelse.trim().isEmpty()) {
            throw new IllegalArgumentException("Beskrivelse kan ikke være tom.");
        }
        this.beskrivelse = beskrivelse.trim();
    }

    /**
     * Henter prioritet
     * @return prioritet
     */
    public Prioritet getPrioritet() { return prioritet; }

    /**
     * Setter for prioritet
     * Validering, prioritet kan ikke være null.
     * @param prioritet Prioritet på saken
     */
    public void setPrioritet(Prioritet prioritet) {
        if (prioritet == null) {
            throw new IllegalArgumentException("Prioritet må settes.");
        }
        this.prioritet = prioritet;
    }

    /**
     * Henter kategori
     * @return kategori
     */
    public Kategori getKategori() { return kategori; }

    /**
     * Setter for kategori
     * @param kategori Kategori for saken
     */
    public void setKategori(Kategori kategori) { this.kategori = kategori; }

    /**
     * Henter status
     * @return status
     */
    public Status getStatus() { return status; }

    /**
     * Setter status for saken
     * @param status Status for saken
     * Validering, status kan ikke være null.
     */
    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status kan ikke være null.");
        }
        this.status = status;
    }

    /**
     * Henter rapportør
     * @return rapportør
     */
    public String getRapportør() { return rapportør; }

    /**
     * Setter for rapportør
     * @param rapportør Navn på rapportør
     */
    public void setRapportør(String rapportør) {
        this.rapportør = rapportør;
    }

    /**
     * Henter mottaker
     * @return mottaker
     */
    public String getMottaker() { return mottaker; }

    /**
     * Setter for mottaker
     * @param mottaker Navn på mottaker
     */
    public void setMottaker(String mottaker) { this.mottaker = mottaker; }

    /**
     * Henter dato og tid for sak
     * @return tidsstempel
     */
    public LocalDateTime getTidsstempel() { return tidsstempel; }

    /**
     * Setter dato og tid for sak
     * @param tidsstempel Dato og tid for sak
     */
    public void setTidsstempel(LocalDateTime tidsstempel) { this.tidsstempel = tidsstempel; }

    /**
     * Henter oppdatert dato og tid
     * @return oppdatertTidspunkt
     */
    public LocalDateTime getOppdatertTidspunkt() { return oppdatertTidspunkt; }

    /**
     * Setter oppdatert dato og tid
     * @param oppdatertTidspunkt Dato og tid for oppdatering
     */
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