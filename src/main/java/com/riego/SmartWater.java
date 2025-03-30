package com.riego;

import java.util.ArrayList;
import java.util.List;

public class SmartWater {
    private DispositivoRiego dispositivoRiego;
    private List<Sensor> sensores = new ArrayList<>();

    public SmartWater() {
        this.dispositivoRiego = new DispositivoRiego();
    }
    
    public List<Sensor> buscarSensores(){
    	List <Sensor> sensoresCargados = PluginLoader.cargarPlugins();

        return sensoresCargados;
    }

    public void conectarSensorADispositivoRiego(Sensor sensor) {
        sensores.add(sensor);
        sensor.agregarObservador(dispositivoRiego);
    }

    public List<Sensor> getSensores() {
        return sensores;
    }

    public DispositivoRiego getDispositivoRiego() {
        return dispositivoRiego;
    }

}
