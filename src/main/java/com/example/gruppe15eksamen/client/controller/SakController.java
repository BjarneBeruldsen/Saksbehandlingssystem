package com.example.gruppe15eksamen.client.controller;
/*Denne filen kobler sammen SakView med sak og rolle fra common
* mappen. Denne filen fungerer på samme måte som main*/

import com.example.gruppe15eksamen.client.network.NetworkClient;
import com.example.gruppe15eksamen.common.Bruker;
import com.example.gruppe15eksamen.common.Rolle;
import com.example.gruppe15eksamen.common.Sak;
import com.example.gruppe15eksamen.server.dao.BrukerDAO;

import java.io.IOException;

public class SakController {
    private Sak sak;
    private BrukerDAO brukerDAO = new BrukerDAO();
    //kobler til server
    private NetworkClient nettverkKlient = new NetworkClient();

    public SakController() {
        lagTabeller();
        kobleTilServer();
    }

    private void kobleTilServer() {
        //harkoder test data.. MÅ FJERNES
        Bruker bruker = new Bruker("Bjarne", Rolle.UTVIKLER);
        try {
            nettverkKlient.snakkMedServer(8000, bruker);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Kunne ikke koble til server");
        }
    }

    private void lagTabeller() {
        BrukerDAO.lagBrukereTabell();
    }


}
