package com.example.gruppe15eksamen.common;

import java.io.Serializable;

public class Soking implements Serializable{
    
    private Prioritet prioritet;
    Status status;
    Kategori kategori;

    private int opprettetAr;
    private int oppdatertAr;
    private String tittel;

    //konstruktør for søk
    public Soking(Prioritet prioritet, Status status, Kategori kategori, int opprettetAr,int oppdatertAr, String tittel) {
        this.prioritet = prioritet;
        this.status = status;
        this.kategori = kategori;
        this.opprettetAr = opprettetAr;
        this.oppdatertAr = oppdatertAr;
        this.tittel = tittel;
    }


    public Soking() {
    }

    //Gettere og settere
    public Prioritet getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(Prioritet prioritet) {
        this.prioritet = prioritet;
    }   
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public int getOpprettetAr() {
        return opprettetAr;
    }

    public void setOpprettetAr(int opprettetAr) {
        this.opprettetAr = opprettetAr;
    }

    public int getOppdatertAr() {
        return oppdatertAr;
    }

    public void setOppdatertAr(int oppdatertAr) {
        this.oppdatertAr = oppdatertAr;
    }
    public String getTittel() {
        return tittel;
    }
    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    
}
