package com.riego;


public class DispositivoRiego implements Observer {
    private boolean activo = false;

    @Override
    public void actualizar(Sensor sensor) {
        if (sensor instanceof SensorHumedad) {
            SensorHumedad sh = (SensorHumedad) sensor;
            if (sh.necesitaRiego() && !activo) {
                activar();
            } else if (!sh.necesitaRiego() && activo) {
                desactivar();
            }
        }
    }

    public void activar() {
        activo = true;
        System.out.println("💧 Riego ACTIVADO");
    }

    public void desactivar() {
        activo = false;
        System.out.println("💧 Riego DESACTIVADO");
    }
}
