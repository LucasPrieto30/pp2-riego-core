package com.riego;

public class EvaluadorRiego {
    private final Sensor sensor;
    private final int umbral;
    private final EstrategiaEvaluacion estrategia;

    public EvaluadorRiego(Sensor sensor, int umbral) {
        this.sensor = sensor;
        this.umbral = umbral;
        this.estrategia = sensor.getEstrategiaEvaluacion();
    }

    public Sensor getSensor() {
        return sensor;
    }

    public boolean necesitaRiego() {
    	return estrategia.necesitaRiego(sensor.getValorMedido(), umbral);
    }
}
