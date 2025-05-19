package com.example.gruppe15eksamen.common;

import java.io.Serializable;

public class Soking implements Serializable{
    
    private String prioritet;
    private String status;
    private String kategori;

    private int opprettetAr;
    private int oppdatertAr;


    public Soking(String prioritet, String status, String kategori, Integer opprettetAr, Integer oppdatertAr) {
        this.prioritet = prioritet;
        this.status = status;
        this.kategori = kategori;
        this.opprettetAr = opprettetAr;
        this.oppdatertAr = oppdatertAr;
    }


    public Soking() {
    }


    public String getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(String prioritet) {
        this.prioritet = prioritet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
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
    
}
