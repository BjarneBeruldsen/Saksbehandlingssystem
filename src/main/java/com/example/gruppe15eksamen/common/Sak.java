package com.example.gruppe15eksamen.common;

import java.io.Serializable;

public class Sak implements Serializable {
    //Viktig siden vi bruker sockets
    private static final long serialVersionUID = 1L;
    //Instansvariabler
    protected int sakID;
    private String tittel;
    private String beskrivelse;
    private String prioritet;
    private String kategori;
    private String status;
    private String rapportør;
    private String mottaker;
    private String tidsstempel; //Usikker på hva slags datatype, regner med string går fint?

    //Deserialisering (standardkonstruktør)
    public Sak() { }

    //Konstruktør
    public Sak(int sakID, String tittel, String beskrivelse, String prioritet, String kategori, String status, String rapportør, String mottaker,  String tidsstempel)
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

    public String getPrioritet() { return prioritet; }

    public void setPrioritet(String prioritet) { this.prioritet = prioritet; }

    public String getKategori() { return kategori; }

    public void setKategori(String kategori) { this.kategori = kategori; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getRapportør() { return rapportør; }

    public void setRapportør(String rapportør) { this.rapportør = rapportør; }

    public String getMottaker() { return mottaker; }

    public void setMottaker(String mottaker) { this.mottaker = mottaker; }

    public String getTiddsstempel() { return tidsstempel; }

    public void setTidsstempel(String tidsstempel) { this.tidsstempel = tidsstempel; }
}