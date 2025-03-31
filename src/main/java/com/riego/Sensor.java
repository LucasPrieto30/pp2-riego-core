package com.riego;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Sensor {
    private List<Observer> observadores = new ArrayList<>();
    protected int valorMedido;
    protected int umbral;

    public Sensor(int umbral) {
    	this.umbral = umbral;
    }
    
    public void agregarObservador(Observer o) {
        observadores.add(o);
    }

    public void eliminarObservador(Observer o) {
        observadores.remove(o);
    }

    protected void notificarObservadores() {
        for (Observer o : observadores) {
            o.actualizar(this);
        }
    }

    public abstract void medir();
    
    public int getValorMedido() {
        return valorMedido;
    }
    
    public int getUmbral() {
        return umbral;
    }
    
    public abstract boolean necesitaRiego();
    
    public void iniciarMediciones() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                medir();
            }
        }, 0, 5000);
    }
}