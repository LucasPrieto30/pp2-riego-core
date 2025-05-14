package com.riego;

public class Aspersor {
    private boolean activo = false;
  
    public void regar() {
    	activo = true;
    }
    
    public void apagarRiego() {
    	activo = false;
    }
    
    public boolean estaRegando() {
    	return activo;
    }
}

