package com.riego;

import java.util.Random;

public class SensorHumedad extends Sensor {
    private int umbral;

    public SensorHumedad(int umbral) {
    	super(umbral);
    }

    @Override
    public void medir() {
        Random random = new Random();
        valor = random.nextInt(100);  // Simula un valor entre 0 y 100%
        System.out.println("Sensor de Humedad: " + valor + "%");

        notificarObservadores();
    }

    @Override
    public boolean necesitaRiego() {
        return valor < umbral;
    }
}

