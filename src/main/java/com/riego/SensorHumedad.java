package com.riego;


import java.util.Random;

public class SensorHumedad extends Sensor {
    private int umbral;

    public SensorHumedad(int umbral) {
        this.umbral = umbral;
    }

    @Override
    public void medir() {
        valor = new Random().nextInt(100);  // Simula humedad del suelo (0-100%)
        System.out.println("🌱 Sensor de Humedad: " + valor + "%");
        notificarObservadores();
    }

    public boolean necesitaRiego() {
        return valor < umbral;
    }
}
