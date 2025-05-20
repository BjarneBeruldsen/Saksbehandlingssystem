package com.example.gruppe15eksamen.common;

import java.io.Serializable;

public class SocketRespons implements Serializable {
    private boolean godkjent;
    private String status;

    public SocketRespons(boolean godkjent, String status) {
        this.godkjent = godkjent;
        this.status = status;
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
}
