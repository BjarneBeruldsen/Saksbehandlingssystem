/**
 * Enum klasse
 * @author Bjarne Beruldsen
 */
package com.example.gruppe15eksamen.common;

public enum Kategori {
    UI_FEIL(1),
    BACKEND_FEIL(2),
    FUNKSJONSFORESPØRSEL(3);

    private final int id;

    Kategori(int id) {
        this.id = id;
    }

    //henter id til kategori
    public int getId() {
        return id;
    }

    //metode som henter kategori basert på id
    public static Kategori getKategoriNavn (int id) {
        for(Kategori kategori : values()) {
            if(kategori.id == id) {
                return kategori;
            }
        }
        throw new IllegalArgumentException("Ugyldig kategori-id" + id);
    }
}
