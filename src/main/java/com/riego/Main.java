package com.riego;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Iniciando Sistema de Riego ===");
        
        PluginLoader.cargarPlugins();
        
        SensorHumedad sensor = new SensorHumedad(40);
        DispositivoRiego riego = new DispositivoRiego(sensor);

        sensor.agregarObservador(riego);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sensor.medir();
            }
        }, 0, 3000);
    }
}

