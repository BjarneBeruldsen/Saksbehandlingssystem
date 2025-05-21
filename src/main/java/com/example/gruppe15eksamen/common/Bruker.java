/**
 * Author: Bjarne Beruldsen og Laurent Zogaj
 */

package com.example.gruppe15eksamen.common;

import java.io.Serializable;

/**
* Superklasse som inneholder felles instansvariabler
 * og abstrakte metoder som hver enkelt bruker må ha
 */
public class Bruker implements Serializable {
    private static final long serialVersionUID = 1L;

    //Instansvariabler
    protected int brukerID;
    private String brukernavn;
    private Rolle rolle;

    /**
     * Konstruktør metode for bruker
     */
    public Bruker(int brukerID, String brukernavn, Rolle rolle) {
        setBrukernavn(brukernavn);
        setRolle(rolle);
        setBrukerID(brukerID);
    }

    /**
     * Validering
     * Setter brukerens brukernavn etter validering.
     * Bruker regex for å unngå andre tegn
     */
    public void setBrukernavn(String brukernavn) {
        if (brukernavn == null || brukernavn.trim().isEmpty()) {
            throw new IllegalArgumentException("Brukernavn kan ikke være tomt.");
        }
        if (!brukernavn.matches("[a-zA-Z0-9_æøåÆØÅ]{2,30}")) {
            throw new IllegalArgumentException("Ugyldig brukernavn.");
        }
        this.brukernavn = brukernavn.trim();
    }

    /**
     * Setter brukerens ID
     * Sjekker også om brukerID er positivt
     */
    public void setBrukerID(int brukerID) {
        if (brukerID < 1) {
            throw new IllegalArgumentException("BrukerID må være et positivt tall.");
        }
        this.brukerID = brukerID;
    }

    /**
     * Avgir rolle
     * Sjekker også om rolle er NULL
     */
    public void setRolle(Rolle rolle) {
        if(rolle == null) {
            throw new IllegalArgumentException("Rolle kan ikke være null");
        }
        this.rolle = rolle;
    }

    /**
     * toString metode
     */
    @Override
    // Viser kun brukernavn, pga. da vises kun brukernavn i ComboBox (velg Bruker)
    public String toString() {
        return brukernavn;
    }
    public String toStringAll() {
        return "bruker:"+brukerID+";"+brukernavn+";"+rolle;
    }

    /**
     * Ulike get metoder
     */
    public int getBrukerID() { return brukerID; }
    public String getBrukernavn() { return brukernavn; }
    public Rolle getRolle() { return rolle; }
}
