package com.riego;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Iniciando Sistema de Riego ===");
        
        // Cargar sensores dinámicos desde la carpeta plugins/
        PluginLoader.cargarPlugins();
        
        // Simulación normal del sistema
        SensorHumedad sensor = new SensorHumedad(40);
        DispositivoRiego riego = new DispositivoRiego();

        sensor.agregarObservador(riego);

        for (int i = 0; i < 5; i++) {
            sensor.medir();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

