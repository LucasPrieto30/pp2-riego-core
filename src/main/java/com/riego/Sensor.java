package com.riego;
import java.util.ArrayList;
import java.util.List;

public abstract class Sensor {
    private List<Observer> observadores = new ArrayList<>();
    protected int valor;

    public void agregarObservador(Observer o) {
        observadores.add(o);
    }

    public void eliminarObservador(Observer o) {
        observadores.remove(o);
    }
    
    public void notificarObservadores() {
        for (Observer o : observadores) {
            o.actualizar();
        }
    }

    public abstract void medir();
    
    public int getValor() {
        return valor;
    }
}