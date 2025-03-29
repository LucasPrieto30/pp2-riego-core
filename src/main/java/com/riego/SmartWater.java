package com.riego;

import java.util.ArrayList;
import java.util.List;

public class SmartWater {
    private DispositivoRiego dispositivo;
    private List<Sensor> sensores = new ArrayList<>();

    public SmartWater(SensorHumedad sensorHumedad) {
        this.dispositivo = new DispositivoRiego(sensorHumedad);
    }

    public void agregarSensor(Sensor sensor) {
        sensores.add(sensor);
        sensor.agregarObservador(dispositivo);
    }

    public List<Sensor> getSensores() {
        return sensores;
    }

    public DispositivoRiego getDispositivo() {
        return dispositivo;
    }
}
