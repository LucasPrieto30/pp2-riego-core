package com.riego;

import java.util.List;

public class Aspersor implements Observer {
    private boolean activo = false;
    private List<EvaluadorRiego> evaluadores;
    

    public Aspersor(List<EvaluadorRiego> evaluadores) {
        this.evaluadores = evaluadores;
    }
    
    @Override
    public void actualizar(Sensor sensor) {
        boolean activar = evaluadores.stream()
            .anyMatch(e -> e.getSensor().equals(sensor) && e.necesitaRiego());

        if (activar) {
            activar();
        } else {
            desactivar();
        }
    }

    public void activar() {
    	activo = true;
    }
    
    public void desactivar() {
    	activo = false;
    }
    
    public boolean estaActivo() {
    	return activo;
    }
}

