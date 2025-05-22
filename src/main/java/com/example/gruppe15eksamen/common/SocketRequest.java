package com.example.gruppe15eksamen.common;

import java.io.Serializable;

public class SocketRequest implements Serializable {
    private String handling;
    private Sak sak;
    private int brukerID;
    private String brukernavn;
    private int sakID;
    private Status status;
    private String kommentar;
    private String type;
    private Object data;
    
    

    public SocketRequest(String handling, Status status, int sakID, String kommentar) {
        this.handling = handling;
        this.status = status;
        this.sakID = sakID;
        this.kommentar = kommentar;
    }
    public SocketRequest(String type, Soking soking) {
        this.type = type;
        this.data = soking;
    }

    public SocketRequest(String handling, Sak sak) {
        this.handling = handling;
        this.sak = sak;
    }

    public SocketRequest(String handling, String brukernavn, int sakID) {
        this.handling = handling;
        this.brukernavn = brukernavn;
        this.sakID = sakID;
    }

    public SocketRequest(int brukerID, String handling) {
        this.brukerID = brukerID;
        this.handling = handling;
    }

    public SocketRequest(String handling) {
    this.handling = handling;
    this.data = null; 
}
   
    public SocketRequest(String handling, Status status, int sakID) {
        this.handling = handling;
        this.status = status;
        this.sakID = sakID;
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

    public String getBrukernavn() {
        return brukernavn;
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

    public Status getStatus() {
        return status;
    }

    public String getKommentar() {
        return kommentar;
    }
     public SocketRequest(String type, Object data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
  
}
