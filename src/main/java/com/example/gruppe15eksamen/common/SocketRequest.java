package com.example.gruppe15eksamen.common;

import java.io.Serializable;

public class SocketRequest implements Serializable {
    private String handling;
    private Sak sak;

    public SocketRequest(String handling, Sak sak) {
        this.handling = handling;
        this.sak = sak;
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
}
