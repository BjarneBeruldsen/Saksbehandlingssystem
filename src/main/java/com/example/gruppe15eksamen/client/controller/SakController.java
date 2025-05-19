package com.example.gruppe15eksamen.client.controller;
/*Denne filen kobler sammen SakView med sak og rolle fra common
* mappen. Denne filen fungerer på samme måte som main*/

import com.example.gruppe15eksamen.common.Sak;
import com.example.gruppe15eksamen.server.dao.BrukerDAO;

public class SakController {
    private Sak sak;
    private BrukerDAO brukerDAO = new BrukerDAO();

    public SakController() {
        lagTabeller();
    }

    private void lagTabeller() {
        BrukerDAO.lagBrukereTabell();
    }


}
