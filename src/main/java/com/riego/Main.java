package com.riego;

public class Main {
    public static void main(String[] args) {
    	String rutaPlugins = args[0];
        String rutaConfiguracion = args[1];

        System.out.println("Inicializando SmartWater...");

        SmartWater smartWater = SmartWaterFactory.crear(rutaPlugins, rutaConfiguracion);

        System.out.println("Sensores activos:");
        for (Sensor s : smartWater.getSensores()) {
            System.out.println(" - " + s.getClass().getSimpleName());
        }

        // Esperar 10 segundos para que se realicen algunas mediciones
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("¿Riego activado? " + (smartWater.riegoActivado() ? "Sí 💧" : "No ❌"));
    }
}
