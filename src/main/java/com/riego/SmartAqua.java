package com.riego;

import java.util.List;
import java.util.stream.Collectors;

public class SmartAqua implements Observer {
	
	private AdministradorRiego administradorRiego;
	private List<Evaluador> evaluadores;
	private Aspersor aspersor;
	private RegistradorDeObservadores registrador;
	
    public SmartAqua(List<Evaluador> evaluadores, AdministradorRiego administradorRiego, Aspersor aspersor, RegistradorDeObservadores registrador) {
        this.administradorRiego = administradorRiego;
        this.evaluadores = evaluadores;
        this.aspersor = aspersor;
        this.registrador = registrador;
        
        registrador.agregarObservador(this);
    }
    
    @Override
    public void actualizar(Evaluador evaluador, boolean debeActivarseRiego) {
    	administradorRiego.procesar(debeActivarseRiego);
    }
        
    public boolean riegoActivado() {
    	return aspersor.estaRegando();
    }
    
    public List<String> getNombresEvaluadores() {
        return evaluadores.stream()
            .map(e -> e.getClass().getSimpleName())
            .collect(Collectors.toList());
    }

    public void agregarObservador(Observer o) {
        registrador.agregarObservador(o);;
    }
    
    public void evaluarTodos() {
        this.evaluadores.forEach(t -> t.evaluar());
    }
}