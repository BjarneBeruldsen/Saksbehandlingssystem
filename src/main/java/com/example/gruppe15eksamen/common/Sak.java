/**
 * Author: Laurent Zogaj, Bjarne Beruldsen & Abdinasir Ali
 */
package com.example.gruppe15eksamen.common;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Sak implements Serializable {
    private static final long serialVersionUID = 1L;

    //Instansvariabler
    protected int sakID;
    private String tittel;
    private String beskrivelse;
    private Prioritet prioritet;
    private Kategori kategori;
    private Status status;
    private String rapportør;
    private String mottaker;
    private LocalDateTime tidsstempel;
    private LocalDateTime oppdatertTidspunkt;

/**
 * Konstruktør for å lage en komplett sak
 */
    public Sak(int sakID, String tittel, String beskrivelse, Prioritet prioritet, Kategori kategori, Status status,
               String rapportør, String mottaker, LocalDateTime tidsstempel, LocalDateTime oppdatertTidspunkt)
    {
        setSakID(sakID);
        setTittel(tittel);
        setBeskrivelse(beskrivelse);
        setPrioritet(prioritet);
        setKategori(kategori);
        setStatus(status);
        setRapportør(rapportør);
        setMottaker(mottaker);
        setTidsstempel(tidsstempel);
        setOppdatertTidspunkt(oppdatertTidspunkt);
    }

    /**
     * Konstruktør for å lage en ny sak
     */
    public Sak(String tittel, String beskrivelse, Prioritet prioritet,
               Kategori kategori, String rapportør) {
        this(0, tittel, beskrivelse, prioritet, kategori, Status.INNSENDT,
                rapportør, "Ikke satt", LocalDateTime.now(), LocalDateTime.now());
    }
    public Sak() {
    }

    /**
     * Henter saks-ID.
     */
    public int getSakID() { return sakID; }

    /**
     * Setter sakId, samtidig som vi sjekker at den ikke er negativ
     */
    public void setSakID(int sakID) {
        if (sakID < 0) {
            throw new IllegalArgumentException("SakID kan ikke være negativ.");
        }
        this.sakID = sakID;
    }

    /**
     * Henter tittel for saken
     */
    public String getTittel() { return tittel; }

    /**
     * Setter tittel for saken
     * Samtidig som vi validerer for å sikre data
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
     */
    public String getBeskrivelse() { return beskrivelse; }

    /**
     * Setter beskrivelse for saken
     * Samtidig som vi validerer for å sikre data
     */
    public void setBeskrivelse(String beskrivelse) {
        if (beskrivelse == null || beskrivelse.trim().isEmpty()) {
            throw new IllegalArgumentException("Beskrivelse kan ikke være tom.");
        }
        this.beskrivelse = beskrivelse.trim();
    }

    /**
     * Henter prioritet i saken
     */
    public Prioritet getPrioritet() { return prioritet; }

    /**
     * Setter prioritet for saken
     * Samtidig som vi validerer for å sikre data
     */
    public void setPrioritet(Prioritet prioritet) {
        if (prioritet == null) {
            throw new IllegalArgumentException("Prioritet må settes.");
        }
        this.prioritet = prioritet;
    }

    /**
     * Henter Kategori for saken
     */
    public Kategori getKategori() { return kategori; }

    /**
     * Setter kategori for saken
     */
    public void setKategori(Kategori kategori) { this.kategori = kategori; }

    /**
     * Henter status for saken
     */
    public Status getStatus() { return status; }

    /**
     * Setter status for saken
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
     */
    public String getRapportør() { return rapportør; }

    /**
     * Setter rapportør for saken
     * Validering. Rapportør kan ikke være tom
     */
    public void setRapportør(String rapportør) {
        if (rapportør == null || rapportør.trim().isEmpty()) {
            throw new IllegalArgumentException("Rapportør kan ikke være tom.");
        }
        this.rapportør = rapportør.trim();
    }

    /**
     * Henter mottaker for saken.
     */
    public String getMottaker() { return mottaker; }

    /**
     * Setter mottaker
     */
    public void setMottaker(String mottaker) { this.mottaker = mottaker; }

    /**
     * Henter dato og tid for sak
     */
    public LocalDateTime getTidsstempel() { return tidsstempel; }

    /**
     * Setter dato og tid for sak
     */
    public void setTidsstempel(LocalDateTime tidsstempel) { this.tidsstempel = tidsstempel; }

    /**
     * Henter oppdatert dato og tid
     */
    public LocalDateTime getOppdatertTidspunkt() { return oppdatertTidspunkt; }

    /**
     * Setter oppdatert dato og tid
     */
    public void setOppdatertTidspunkt(LocalDateTime oppdatertTidspunkt) { this.oppdatertTidspunkt = oppdatertTidspunkt; }

    /**
     * toString metode
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
                ", tidsstempel=" + tidsstempel +
                ", oppdatertTidspunkt=" + oppdatertTidspunkt +
                '}';
    }
}