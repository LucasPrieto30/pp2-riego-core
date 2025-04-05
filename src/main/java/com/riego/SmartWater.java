package com.riego;

import java.util.ArrayList;
import java.util.List;

public class SmartWater {
	
	private List<EvaluadorRiego> evaluadores;
    private Aspersor aspersor;

    public SmartWater(List<EvaluadorRiego> evaluadores, Aspersor aspersor) {
        this.evaluadores = evaluadores;
        this.aspersor = aspersor;

        for (EvaluadorRiego e : evaluadores) {
            e.getSensor().agregarObservador(aspersor);
        }
    }

    public Aspersor getAspersor() {
        return aspersor;
    }
}