package com.riego;

public class EvaluadorRiego {
    private final int umbral;
    private final EstrategiaEvaluacion estrategia;

    public EvaluadorRiego(Sensor sensor, int umbral) {
        this.umbral = umbral;
        this.estrategia = sensor.getEstrategiaEvaluacion();
    }

    public boolean evaluarRiego(int medicion) {
    	return estrategia.necesitaRiego(medicion, umbral);
    }
}
