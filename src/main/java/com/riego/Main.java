package com.riego;

public class Main {
    public static void main(String[] args) {
    	String rutaConfiguracion = args[0];

        System.out.println("Inicializando SmartWater...");

        SmartAqua smartAqua = SmartAquaFactory.crear(rutaConfiguracion);

        System.out.println("Sensores activos:");
        for (Evaluador s : smartAqua.getEvaluadores()) {
            System.out.println(" - " + s.getClass().getSimpleName());
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("¿Riego activado? " + (smartAqua.riegoActivado() ? "Sí 💧" : "No ❌"));
    }
}
