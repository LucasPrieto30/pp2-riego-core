package com.riego;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Sensor {
    private List<Observer> observadores = new ArrayList<>();
    protected int valorMedido;
    
    public Sensor () {
    	iniciarMediciones();
    }
   
    protected abstract int medir();
    
    public int getValorMedido() {
    	return valorMedido;
    }
    
    public abstract EstrategiaEvaluacion getEstrategiaEvaluacion();
    
    public void iniciarMediciones() {
    	Timer timer = new Timer();
    	timer.scheduleAtFixedRate(new TimerTask() {
    		@Override
    		public void run() {
    			medir();
    		}
    	}, 0, 3000);
    }
    
    public void agregarObservador(Observer o) {
        observadores.add(o);
    }

    public void eliminarObservador(Observer o) {
        observadores.remove(o);
    }

    protected void notificarObservadores() {
        for (Observer o : observadores) {
            o.actualizar(this, this.valorMedido);
        }
    }

}