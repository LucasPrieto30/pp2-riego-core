package com.riego;

public class Main {
    public static void main(String[] args) {
    	String rutaConfiguracion = args[0];

        System.out.println("Inicializando SmartWater...");

        SmartAqua smartWater = SmartAquaFactory.crear(rutaConfiguracion);

        System.out.println("Sensores activos:");
        for (EvaluadorRiego s : smartWater.getEvaluadores()) {
            System.out.println(" - " + s.getClass().getSimpleName());
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("¿Riego activado? " + (smartWater.riegoActivado() ? "Sí 💧" : "No ❌"));
    }
}
