package com.example.gruppe15eksamen.common;

import java.io.Serializable;

/*Superklasse som inneholder felles instansvariabler
* og abstrakte metoder som hver enkelt bruker må ha */
public class Bruker implements Serializable {
    //instansvariabler
    private int brukerID;
    private String brukernavn;
    private Rolle rolle;

    public Bruker(int brukerID, String brukernavn, Rolle rolle) {
        setBrukernavn(brukernavn); //LEGG TIL VALIDERING
        setRolle(rolle);
        this.brukerID = brukerID;
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
    // Viser kun brukernavn, pga. da vises kun brukernavn i ComboBox (velg Bruker)
    public String toString() {
        return brukernavn;
    }

    // Tidligere versjon/full versjon av toString
    public String toStringAll() {
        return "bruker:"+brukerID+";"+brukernavn+";"+rolle;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public Rolle getRolle() {
        return rolle;
    }
}
