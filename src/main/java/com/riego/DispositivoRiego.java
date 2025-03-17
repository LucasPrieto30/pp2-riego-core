package com.riego;

import com.riego.Observer;
import com.riego.SensorHumedad;

public class DispositivoRiego implements Observer {
    private boolean activo = false;
    private SensorHumedad sensor;

    public DispositivoRiego(SensorHumedad sensor) {
        this.sensor = sensor;
        this.sensor.agregarObservador(this);  // 📌 Se suscribe como observador del sensor
    }

    @Override
    public void actualizar(Sensor sensor) {  // 📌 Ahora recibe un Sensor genérico
        if (sensor instanceof SensorHumedad) {
            SensorHumedad sensorHumedad = (SensorHumedad) sensor;
            if (sensorHumedad.necesitaRiego() && !activo) {
                activar();
            } else if (!sensorHumedad.necesitaRiego() && activo) {
                desactivar();
            }
        }
    }

    public void activar() {
        if (!activo) {
            activo = true;
            System.out.println("💧 Riego ACTIVADO");
        }
    }

    public void desactivar() {
        if (activo) {
            activo = false;
            System.out.println("💧 Riego DESACTIVADO");
        }
    }

    public boolean estaActivo() {
        return activo;
    }
}

