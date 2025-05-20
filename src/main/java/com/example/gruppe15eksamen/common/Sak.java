package com.example.gruppe15eksamen.common;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Sak implements Serializable {
    //Viktig siden vi bruker sockets
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
    private LocalDateTime tidsstempel; //Bjarne endret til localdatetime (usikker på om det er riktig)

    //Deserialisering (standardkonstruktør)
    public Sak() { }

    //Konstruktør
    public Sak(int sakID, String tittel, String beskrivelse, Prioritet prioritet, Kategori kategori, Status status,
               String rapportør, String mottaker,  LocalDateTime tidsstempel)
    {
        this.sakID       = sakID;
        this.tittel      = tittel;
        this.beskrivelse = beskrivelse;
        this.prioritet   = prioritet;
        this.kategori    = kategori;
        this.status      = status;
        this.rapportør   = rapportør;
        this.mottaker    = mottaker;
        this.tidsstempel = tidsstempel;
    }

    //Gettere og settere
    public int getSakID() { return sakID; }

    public void setSakID(int sakID) { this.sakID = sakID; }

    public String getTittel() { return tittel; }

    public void setTittel(String tittel) { this.tittel = tittel; }

    public String getBeskrivelse() { return beskrivelse; }

    public void setBeskrivelse(String beskrivelse) { this.beskrivelse = beskrivelse; }

    public Prioritet getPrioritet() { return prioritet; }

    public void setPrioritet(Prioritet prioritet) { this.prioritet = prioritet; }

    public Kategori getKategori() { return kategori; }

    public void setKategori(Kategori kategori) { this.kategori = kategori; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    public String getRapportør() { return rapportør; }

    public void setRapportør(String rapportør) { this.rapportør = rapportør; }

    public String getMottaker() { return mottaker; }

    public void setMottaker(String mottaker) { this.mottaker = mottaker; }

    public LocalDateTime getTiddsstempel() { return tidsstempel; }

    public void setTidsstempel(LocalDateTime tidsstempel) { this.tidsstempel = tidsstempel; }
}