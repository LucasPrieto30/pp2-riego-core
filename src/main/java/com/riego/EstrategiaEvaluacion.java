package com.riego;

@FunctionalInterface
public interface EstrategiaEvaluacion {
    boolean comparar(int valor, int umbral);
}
