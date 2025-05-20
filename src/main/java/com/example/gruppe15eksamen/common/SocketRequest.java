package com.example.gruppe15eksamen.common;

import java.io.Serializable;

public class SocketRequest implements Serializable {
    private String handling;
    private Sak sak;
    private int brukerID;
    private int sakID;

    public SocketRequest(String handling, Sak sak) {
        this.handling = handling;
        this.sak = sak;
    }

    public SocketRequest(String handling, int brukerID, int sakID) {
        this.handling = handling;
        this.brukerID = brukerID;
        this.sakID = sakID;
    }

    public SocketRequest(int brukerID, String handling) {
        this.brukerID = brukerID;
        this.handling = handling;
    }

    //for requests uten sak
    public SocketRequest(String handling) {
        this(handling, null);
    }

    public String getHandling() {
        return handling;
    }

    public void setHandling(String handling) {
        this.handling = handling;
    }

    public Sak getSak() {
        return sak;
    }

    public void setSak(Sak sak) {
        this.sak = sak;
    }

    public int getBrukerID() {
        return brukerID;
    }

    public void setBrukerID(int brukerID) {
        this.brukerID = brukerID;
    }

    public int getSakID() {
        return sakID;
    }

    public void setSakID(int sakID) {
        this.sakID = sakID;
    }
}
