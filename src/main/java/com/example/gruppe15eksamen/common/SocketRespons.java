/**
 * Klasse for socket funksjonalitet.
 * @author Bjarne Beruldsen
 */
package com.example.gruppe15eksamen.common;

import java.io.Serializable;
import java.util.ArrayList;

public class SocketRespons implements Serializable {
    private boolean godkjent;
    private String status;
    private ArrayList<Sak> saker;

    public SocketRespons(boolean godkjent, String status) {
        this.godkjent = godkjent;
        this.status = status;
    }

    public SocketRespons(String status, ArrayList<Sak> saker, boolean godkjent) {
        this.status = status;
        this.saker = saker;
        this.godkjent = godkjent;
    }

    public boolean isGodkjent() {
        return godkjent;
    }

    public void setGodkjent(boolean godkjent) {
        this.godkjent = godkjent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Sak> getSaker() {
        return saker;
    }


    @Override
    public String toString() {
        return "SocketRespons{" +
                "godkjent=" + godkjent +
                ", status='" + status + '\'' +
                '}';
    }
}
