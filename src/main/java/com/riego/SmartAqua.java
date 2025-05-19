package com.riego;

import java.util.List;

public class SmartAqua implements Observer {
	
	private AdministradorRiego administradorRiego;
	private List<Evaluador> evaluadores;
	private Aspersor aspersor;
	private LoggerActivaciones logger;

    public SmartAqua(List<Evaluador> evaluadores, AdministradorRiego administradorRiego, Aspersor aspersor, LoggerActivaciones logger) {
        this.administradorRiego = administradorRiego;
        this.evaluadores = evaluadores;
        this.aspersor = aspersor;
        this.logger = logger;
        
        for (Evaluador e : evaluadores) {
        	e.agregarObservador(this);
        	e.agregarObservador(logger);
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
    
    public LoggerActivaciones getLogger() {
        return logger;
    }
}