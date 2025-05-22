/**
 * Enum klasse
 * @author Bjarne Beruldsen
 */
package com.example.gruppe15eksamen.common;

public enum Prioritet {
    LAV(1),
    MIDDELS(2),
    HØY(3);

    private final int id;

    Prioritet(int id) {
        this.id = id;
    }

    //henter id til prioritet
    public int getId() {
        return id;
    }

    //metode som henter prioritet basert på id
    public static int getPrioritet (int id) {
        for(Prioritet prioritet: values()) {
            if(prioritet.getId() == id) {
                return id;
            }
        }
        throw new IllegalArgumentException("Ugyldig prioritet-id" + id);
    }
}
