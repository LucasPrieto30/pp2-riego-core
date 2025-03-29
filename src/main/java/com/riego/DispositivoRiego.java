package com.riego;

import com.riego.Observer;
import com.riego.SensorHumedad;

public class DispositivoRiego implements Observer {
    private boolean activo = false;

    @Override
    public void actualizar(Sensor sensor) {
    	System.out.println("Actualizacion recibida de " + sensor.getClass().getSimpleName() + " valor: " + sensor.getValor());
    	System.out.println(sensor.necesitaRiego());
        if (sensor.necesitaRiego()) {
        	System.out.println("entro al if activar");
            activar();
        } else {
        	System.out.println("entro al else desactivar");

            desactivar();
        }
    }

    public void activar() {
        if (!activo) {
            activo = true;
        }
        System.out.println("Riego ACTIVADO");
    }

    public void desactivar() {
        if (activo) {
            activo = false;
        }
        System.out.println("Riego DESACTIVADO");
    }

    public boolean estaActivo() {
        return activo;
        
    }
}

