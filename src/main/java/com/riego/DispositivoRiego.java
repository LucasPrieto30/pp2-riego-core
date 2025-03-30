package com.riego;

public class DispositivoRiego implements Observer {
    private boolean activo = false;

    @Override
    public void actualizar(Sensor sensor) {
        if (sensor.necesitaRiego()) {
            activar();
        } else {

            desactivar();
        }
    }

    public void activar() {
        if (!activo) {
            activo = true;
            System.out.println("Riego ACTIVADO");
        }
    }

    public void desactivar() {
        if (activo) {
            activo = false;
            System.out.println("Riego DESACTIVADO");
        }
    }

    public boolean estaActivo() {
        return activo;
        
    }
}

