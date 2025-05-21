package com.example.gruppe15eksamen.common;

public enum Status {
    INNSENDT(1),
    TILDELT(2),
    PÅGÅR(3),
    RETTET(4),
    LØST(5),
    TEST_MISLYKTES(6),
    LUKKET(7);

    private final int id;


    Status(int id) {
        this.id = id;
    }

    //henter id til status
    public  int getId() {
        return id;
    }

    //metode som  henter status basert på status id
    public static Status getStatusNavn (int id) {
        for(Status status: values()) {
            if(status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Ugyldig status-id" + id);
    }
}
