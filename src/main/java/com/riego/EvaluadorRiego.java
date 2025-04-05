package com.riego;

public class EvaluadorRiego {
    private final Sensor sensor;
    private final int umbral;

    public EvaluadorRiego(Sensor sensor, int umbral) {
        this.sensor = sensor;
        this.umbral = umbral;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public boolean necesitaRiego() {
        return sensor.getValorMedido() < umbral;
    }
}
