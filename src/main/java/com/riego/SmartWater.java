package com.riego;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SmartWater implements Observer {
	
	private List<EvaluadorRiego> evaluadores;
    private Aspersor aspersor;

    public SmartWater(List<EvaluadorRiego> evaluadores, Aspersor aspersor) {
        this.evaluadores = evaluadores;
        this.aspersor = aspersor;

        for (EvaluadorRiego e : evaluadores) {
        	e.getSensor().agregarObservador(this);
        	e.getSensor().iniciarMediciones();
        }
    }

    @Override
    public void actualizar(Sensor sensor) {
        boolean debeActivarRiego = evaluadores.stream().anyMatch(EvaluadorRiego::necesitaRiego);

        if (debeActivarRiego) {
            aspersor.activar();
        } else {
            aspersor.desactivar();
        }
    }
    
    public Aspersor getAspersor() {
        return aspersor;
    }
    
    public boolean riegoActivado() {
    	return aspersor.estaActivo();
    }
    
    public List<Sensor> getSensores() {
        return evaluadores.stream().map(EvaluadorRiego::getSensor).collect(Collectors.toList());
    }
}