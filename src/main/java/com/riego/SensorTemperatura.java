package com.riego;

public class SensorTemperatura implements PluginSensor { //DEBERIA IR FUERA DEL PROYECTO
    private int temperatura;

    @Override
    public void inicializar() {
        System.out.println("Sensor de Temperatura cargado.");
    }

    @Override
    public int obtenerValor() {
        temperatura = (int) (Math.random() * 40); // Simula valores entre 0°C y 40°C
        return temperatura;
    }
}
