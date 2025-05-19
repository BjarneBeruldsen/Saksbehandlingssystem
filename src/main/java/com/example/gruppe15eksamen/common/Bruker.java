package com.example.gruppe15eksamen.common;

import java.io.Serializable;

/*Superklasse som inneholder felles instansvariabler
* og abstrakte metoder som hver enkelt bruker må ha */
public class Bruker implements Serializable {
    //instansvariabler
    private String brukernavn;
    private Rolle rolle;

    public Bruker(String brukernavn, Rolle rolle) {
        setBrukernavn(brukernavn); //LEGG TIL VALIDERING
        setRolle(rolle);
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public void setRolle(Rolle rolle) {
        if(rolle == null) {
            throw new IllegalArgumentException("Rolle kan ikke være null");
        }
        this.rolle = rolle;
    }

    @Override
    public String toString() {
        return "Bruker{" +
                "brukernavn='" + brukernavn + '\'' +
                ", rolle=" + rolle +
                '}';
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public Rolle getRolle() {
        return rolle;
    }
}
