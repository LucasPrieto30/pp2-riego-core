package com.riego;

import java.util.List;

public class SmartAqua implements Observer {
	
	private AdministradorRiego administradorRiego;
	private List<Sensor> sensores;
	private Aspersor aspersor;

    public SmartAqua(AdministradorRiego administradorRiego, List<Sensor> sensores, Aspersor aspersor) {
        this.administradorRiego = administradorRiego;
        this.sensores = sensores;
        this.aspersor = aspersor;

        for (Sensor s : sensores) {
            s.agregarObservador(this);
        }
    }

    @Override
    public void actualizar(Sensor sensor, int medicion) {
    	administradorRiego.procesarMedicion(sensor, medicion);
    }
        
    public boolean riegoActivado() {
    	return aspersor.estaActivo();
    }
    
    public List<Sensor> getSensores() {
        return sensores;
    }
}