package com.riego;

import java.util.List;

public class SmartAqua implements Observer {
	
	private AdministradorRiego administradorRiego;
	private List<EvaluadorRiego> evaluadores;
	private Aspersor aspersor;

    public SmartAqua(List<EvaluadorRiego> evaluadores, AdministradorRiego administradorRiego, Aspersor aspersor) {
        this.administradorRiego = administradorRiego;
        this.evaluadores = evaluadores;
        this.aspersor = aspersor;

        for (EvaluadorRiego e : evaluadores) {
        	e.agregarObservador(this);
        }
    }

    @Override
    public void actualizar(EvaluadorRiego sensor, boolean debeActivarseRiego) {
    	administradorRiego.procesar(debeActivarseRiego);
    }
        
    public boolean riegoActivado() {
    	return aspersor.estaRegando();
    }
    
    public List<EvaluadorRiego> getEvaluadores() {
        return evaluadores;
    }
}