package com.riego;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SmartWater implements Observer {
	
	private Map<Sensor, EvaluadorRiego> evaluadores;
    private Aspersor aspersor;

    public SmartWater(Map<Sensor, EvaluadorRiego> evaluadores, Aspersor aspersor) {
        this.evaluadores = evaluadores;
        this.aspersor = aspersor;

        evaluadores.keySet().forEach(sensor -> {
            sensor.iniciarMediciones();
            sensor.agregarObservador(this);
        });
    }

    @Override
    public void actualizar(Sensor sensor, int medicion) {
        EvaluadorRiego evaluador = evaluadores.get(sensor);
        boolean debeActivarRiego = evaluador.evaluarRiego(medicion);

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
        return (List<Sensor>) evaluadores.keySet();
    }
}