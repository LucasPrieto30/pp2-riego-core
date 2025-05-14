package com.riego;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Sensor {
    protected int valorMedido;
    
    public Sensor () {
    	iniciarMediciones();
    }
   
    protected abstract int medir();
    
    public int getValorMedido() {
    	return valorMedido;
    }
        
    public void iniciarMediciones() {
    	Timer timer = new Timer();
    	timer.scheduleAtFixedRate(new TimerTask() {
    		@Override
    		public void run() {
    			medir();
    		}
    	}, 0, 3000);
    }    
}