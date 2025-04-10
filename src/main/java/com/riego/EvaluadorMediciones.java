package com.riego;

public class EvaluadorMediciones {
    private final int umbral;
    private final EstrategiaEvaluacion estrategia;

    public EvaluadorMediciones(int umbral, EstrategiaEvaluacion estrategia) {
        this.umbral = umbral;
        this.estrategia = estrategia;
    }

    public boolean evaluarMedicion(int medicion) {
    	return estrategia.comparar(medicion, umbral);
    }
}
