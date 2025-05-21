package com.example.gruppe15eksamen.common;

public enum Rolle {
    LEDER(1),
    TESTER(2),
    UTVIKLER(3);

    private final int id;

    Rolle(int id) {
        this.id = id;
    }

    //henter id til rolle
    public int getId() {
        return id;
    }

    //metode som henter rolle basert på rolle id
    public static Rolle getRolleNavn (int id) {
        for(Rolle rolle : values()) {
            if(rolle.id == id){
                return rolle;
            }
        }
        throw new IllegalArgumentException("Ugyldig rolle-id" + id);
    }
}
