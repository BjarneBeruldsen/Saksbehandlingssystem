/**
 * Klasse for søkefunksjonalitet.
 * @author Abdinasir Ali
 */

package com.example.gruppe15eksamen.common;

import java.io.Serializable;

public class Soking implements Serializable{
    private static final long serialVersionUID = 1L;
    private Prioritet prioritet;
    Status status;
    Kategori kategori;

    private Integer opprettetAr;
    private Integer oppdatertAr;
    private String tittel;
    private String reporterNavn;
    private String beskrivelse;

    //konstruktør for søk
    public Soking(Prioritet prioritet, Status status, Kategori kategori, Integer opprettetAr,Integer oppdatertAr, String tittel, String reporterNavn, String beskrivelse) {
        setPrioritet(prioritet);
        setStatus(status);
        setKategori(kategori);
        setOpprettetAr(opprettetAr);
        setOppdatertAr(oppdatertAr);
        setTittel(tittel);
        setReporterNavn(reporterNavn);
        setBeskrivelse(beskrivelse);

    }
    public Soking() {
    }

    // Søking med tittel
    public Soking(String tittel) {
        this.tittel = tittel;
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

    public Integer getOpprettetAr() {
        return opprettetAr;
    }

    public void setOpprettetAr(Integer opprettetAr) {
        this.opprettetAr = opprettetAr;
    }

    public Integer getOppdatertAr() {
        return oppdatertAr;
    }

    public void setOppdatertAr(Integer oppdatertAr) {
        this.oppdatertAr = oppdatertAr;
    }
    public String getTittel() {
        return tittel;
    }
    public void setTittel(String tittel) {
        this.tittel = tittel;
    }
    public String getReporterNavn() {
        return reporterNavn;
    }
    public void setReporterNavn(String reporterNavn) {
        this.reporterNavn = reporterNavn;
    }
    public String getBeskrivelse() {
        return beskrivelse;
    }
    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }


    
}
