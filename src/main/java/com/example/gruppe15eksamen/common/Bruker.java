/**
 * Bruker klassen som inneholder felles instansvariabler
 * og abstrakte metoder som hver enkelt bruker må ha
 * @author: Laurent Zogaj og Bjarne Beruldsen
 */
package com.example.gruppe15eksamen.common;

import java.io.Serializable;

public class Bruker implements Serializable {
    private static final long serialVersionUID = 1L;

    //Instansvariabler
    protected int brukerID;
    private String brukernavn;
    private Rolle rolle;

    /**
     * Konstruktør for Bruker
     * @param brukerID    Unik ID for brukeren, må være positiv.
     * @param brukernavn  Brukerens navn. Kan ikke være tom eller inneholde ugyldige tegn.
     * @param rolle       Brukerens rolle (kan ikke være null).
     */
    public Bruker(int brukerID, String brukernavn, Rolle rolle) {
        setBrukernavn(brukernavn);
        setRolle(rolle);
        setBrukerID(brukerID);
    }

    /**
     * Setter brukerens brukernavn etter validering.
     * Kan kun bestå av bokstaver, tall og vanlig norske bokstaver samt _ understrek (2-30 tegn).
     * @param brukernavn Brukernavn
     * @throws IllegalArgumentException hvis brukernavn er ugyldig eller tomt
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
     * Setter brukerens ID. 
     * Validering: Må være et positivt tall.
     * @param brukerID Bruker-ID
     * @throws IllegalArgumentException hvis brukerID er mindre enn 1
     */
    public void setBrukerID(int brukerID) {
        if (brukerID < 1) {
            throw new IllegalArgumentException("BrukerID må være et positivt tall.");
        }
        this.brukerID = brukerID;
    }

    /**
     * Setter brukerens rolle. 
     * Validering: Kan ikke være null.
     * @param rolle Rolle-objektet som settes
     * @throws IllegalArgumentException hvis rolle er null
     */
    public void setRolle(Rolle rolle) {
        if(rolle == null) {
            throw new IllegalArgumentException("Rolle kan ikke være null");
        }
        this.rolle = rolle;
    }

    /**
     * Returnerer brukernavnet.
     * @return brukernavn
     */
    @Override
    public String toString() {
        return brukernavn;
    }

    /**
     * Returnerer alle brukerens data i tekstformat.
     * @return streng med brukerens ID, brukernavn og rolle
     */
    public String toStringAll() {
        return "bruker:" + brukerID + ";" + brukernavn + ";" + rolle;
    }

    /**
     * Returnerer brukerens ID.
     * @return brukerID
     */
    public int getBrukerID() { return brukerID; }

    /**
     * Returnerer brukerens brukernavn.
     * @return brukernavn
     */
    public String getBrukernavn() { return brukernavn; }

    /**
     * Returnerer brukerens rolle.
     * @return rolle
     */
    public Rolle getRolle() { return rolle; }
}
