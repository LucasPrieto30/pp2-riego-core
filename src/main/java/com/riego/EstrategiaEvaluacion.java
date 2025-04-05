package com.riego;

@FunctionalInterface
public interface EstrategiaEvaluacion {
    boolean necesitaRiego(int valor, int umbral);
}
