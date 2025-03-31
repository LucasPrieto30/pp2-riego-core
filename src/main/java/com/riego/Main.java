package com.riego;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Riego");
        
        SmartWater smartWater = new SmartWater();

        
        List<Sensor> sensores = PluginLoader.cargarPlugins();
        if (sensores.isEmpty()) {
            System.out.println("No se encontraron sensores en la carpeta de plugins.");
            return;
        }

        for (Sensor sensor : sensores) {
        	smartWater.conectarSensorADispositivoRiego(sensor);
            System.out.println(" Sensor agregado: " + sensor.getClass().getSimpleName());
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println(" Medición automática:");
                for (Sensor sensor : smartWater.getSensores()) {
                    sensor.medir();
                    System.out.println("   → " + sensor.getClass().getSimpleName() + ": " + sensor.getValorMedido());
                }

                if (smartWater.getDispositivoRiego().estaActivo()) {
                    System.out.println(" El riego está ACTIVADO");
                } else {
                    System.out.println(" El riego está DESACTIVADO");
                }

                System.out.println();
            }
        }, 0, 3000);
    }
}
