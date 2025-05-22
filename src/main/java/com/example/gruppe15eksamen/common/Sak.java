/**
 * Sak klassen som representerer en sak i saksbehandlingssystemet.
 * En Sak har informasjon om tittel, beskrivelse, prioritet, kategori, status, rapportør, mottaker,
 * opprettelsestidspunkt og oppdatert tidspunkt.
 * @author Laurent Zogaj, Bjarne Beruldsen & Abdinasir Ali
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
     * Konstruktør for å lage en komplett sak.
     * @param sakID                 Saks-ID
     * @param tittel                Sakens tittel
     * @param beskrivelse           Beskrivelse av saken
     * @param prioritet             Prioritet på saken
     * @param kategori              Kategori for saken
     * @param status                Status for saken
     * @param rapportør             Navn på rapportør
     * @param mottaker              Navn på mottaker
     * @param tidsstempel           Tidspunkt saken ble opprettet
     * @param oppdatertTidspunkt    Tidspunkt saken sist ble oppdatert
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
     * @param tittel        Sakens tittel
     * @param beskrivelse   Beskrivelse av saken
     * @param prioritet     Prioritet på saken
     * @param kategori      Kategori for saken
     * @param rapportør     Navn på rapportør
     */
    public Sak(String tittel, String beskrivelse, Prioritet prioritet,
               Kategori kategori, String rapportør) {
        this(0, tittel, beskrivelse, prioritet, kategori, Status.INNSENDT,
                rapportør, "Ikke satt", LocalDateTime.now(), LocalDateTime.now());

    } //Tom konstruktør for serialisering
    public Sak() {
    }

    /**
     * Henter saks-ID
     * @return sakID 
     */
    public int getSakID() { return sakID; }

    /**
     * Setter saks-ID
     * Validering: Sjekker at ID ikke er negativ.
     * @param sakID Sak ID
     * @throws IllegalArgumentException hvis sakID er negativ
     */
    public void setSakID(int sakID) {
        if (sakID < 0) {
            throw new IllegalArgumentException("SakID kan ikke være negativ.");
        }
        this.sakID = sakID;
    }

    /**
     * Henter sakens tittel.
     * @return sakens tittel
     */
    public String getTittel() { return tittel; }

    /**
     * Setter tittel for saken, validerer tittelen for tom string og lengde.
     * @param tittel Sakens tittel
     * @throws IllegalArgumentException hvis tittel er ugyldig
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
     * Henter beskrivelse av saken.
     * @return sakens beskrivelse
     */
    public String getBeskrivelse() { return beskrivelse; }

    /**
     * Setter beskrivelse for saken. 
     * Validering: Kan ikke være tom.
     * @param beskrivelse Sakens beskrivelse
     * @throws IllegalArgumentException hvis beskrivelse er tom
     */
    public void setBeskrivelse(String beskrivelse) {
        if (beskrivelse == null || beskrivelse.trim().isEmpty()) {
            throw new IllegalArgumentException("Beskrivelse kan ikke være tom.");
        }
        this.beskrivelse = beskrivelse.trim();
    }

    /**
     * Henter prioritet.
     * @return prioritet
     */
    public Prioritet getPrioritet() { return prioritet; }

    /**
     * Setter prioritet. 
     * Validering: Kan ikke være null.
     * @param prioritet Sakens prioritet
     * @throws IllegalArgumentException hvis prioritet er null
     */
    public void setPrioritet(Prioritet prioritet) {
        if (prioritet == null) {
            throw new IllegalArgumentException("Prioritet må settes.");
        }
        this.prioritet = prioritet;
    }

    /**
     * Henter kategori.
     * @return kategori
     */
    public Kategori getKategori() { return kategori; }

     /**
     * Setter kategori for saken.
     * @param kategori Sakens kategori
     */
    public void setKategori(Kategori kategori) { this.kategori = kategori; }

    /**
     * Henter status for saken.
     * @return status
     */
    public Status getStatus() { return status; }

    /**
     * Setter status. 
     * Validering: Kan ikke være null.
     * @param status Sakens status
     * @throws IllegalArgumentException hvis status er null
     */
    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status kan ikke være null.");
        }
        this.status = status;
    }

    /**
     * Henter rapportør.
     * @return rapportør
     */
    public String getRapportør() { return rapportør; }

    /**
     * Setter rapportør.
     * @param rapportør Navn på rapportør
     */
    public void setRapportør(String rapportør) {
        this.rapportør = rapportør;
    }

     /**
     * Henter mottaker.
     * @return mottaker
     */
    public String getMottaker() { return mottaker; }

    /**
     * Setter mottaker.
     * @param mottaker Navn på mottaker
     */
    public void setMottaker(String mottaker) { this.mottaker = mottaker; }

    /**
     * Henter tidsstempel.
     * @return tidsstempel
     */
    public LocalDateTime getTidsstempel() { return tidsstempel; }

    /**
     * Setter tidsstempel.
     * @param tidsstempel Tidspunkt for opprettelse
     */
    public void setTidsstempel(LocalDateTime tidsstempel) { this.tidsstempel = tidsstempel; }

    /**
     * Henter oppdatert tidspunkt.
     * @return oppdatert tidspunkt
     */
    public LocalDateTime getOppdatertTidspunkt() { return oppdatertTidspunkt; }

    /**
     * Setter oppdatert tidspunkt.
     * @param oppdatertTidspunkt Tidspunkt for siste oppdatering
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
                ", tidsstempel=" + tidsstempel +
                ", oppdatertTidspunkt=" + oppdatertTidspunkt +
                '}';
    }
}