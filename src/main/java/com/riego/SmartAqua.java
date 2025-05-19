package com.riego;

import java.util.List;

public class SmartAqua implements Observer {
	
	private AdministradorRiego administradorRiego;
	private List<Evaluador> evaluadores;
	private Aspersor aspersor;

    public SmartAqua(List<Evaluador> evaluadores, AdministradorRiego administradorRiego, Aspersor aspersor) {
        this.administradorRiego = administradorRiego;
        this.evaluadores = evaluadores;
        this.aspersor = aspersor;

        for (Evaluador e : evaluadores) {
        	e.agregarObservador(this);
        }
    }

    @Override
    public void actualizar(Evaluador evaluador, boolean debeActivarseRiego) {
    	administradorRiego.procesar(debeActivarseRiego);
    }
        
    public boolean riegoActivado() {
    	return aspersor.estaRegando();
    }
    
    public List<Evaluador> getEvaluadores() {
        return evaluadores;
    }
}