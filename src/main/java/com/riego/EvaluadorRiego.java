package com.riego;

public interface EvaluadorRiego {
    void agregarObservador(Observer observador);
    void configurarUmbral(int umbral);
}
