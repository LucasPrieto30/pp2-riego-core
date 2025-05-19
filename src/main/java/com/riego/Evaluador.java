package com.riego;

import java.util.ArrayList;
import java.util.List;

public abstract class Evaluador {
    private List<Observer> observadores = new ArrayList<>();
    protected boolean debeRegar;
    protected int umbral;
    protected int ultimaMedicion;
    
    public void agregarObservador(Observer o) {
        observadores.add(o);
    }

    public void eliminarObservador(Observer o) {
        observadores.remove(o);
    }

    protected void notificarObservadores() {
        for (Observer o : observadores) {
            o.actualizar(this, this.debeRegar);
        }
    }
    public void configurarUmbral(int umbral) {
    	this.umbral = umbral;
    }
    
    public int getUmbral() {
        return umbral;
    }

    public int getUltimaMedicion() {
        return ultimaMedicion;
    }

    
    protected abstract int obtenerMedicion();
    
    protected abstract void iniciarEvaluaciones();
}
