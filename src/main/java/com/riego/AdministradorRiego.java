package com.riego;

import java.util.Map;

public class AdministradorRiego {
    private final Map<Sensor, EvaluadorMediciones> evaluadores;
    private final Aspersor aspersor;
    
    public AdministradorRiego(Map<Sensor, EvaluadorMediciones> evaluadores, Aspersor aspersor) {
        this.evaluadores = evaluadores;
        this.aspersor = aspersor;
    }
    
    public void procesarMedicion(Sensor sensor, int medicion) {
        EvaluadorMediciones evaluador = evaluadores.get(sensor);
        boolean seDebeActivarRiego = evaluador.evaluarMedicion(medicion);
        
        if (seDebeActivarRiego) {
            aspersor.activar();
        } else {
            aspersor.desactivar();
        }
    }
}
