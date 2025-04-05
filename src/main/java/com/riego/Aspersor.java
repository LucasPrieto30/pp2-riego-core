package com.riego;

public class Aspersor {
    private boolean activo = false;
  
    public void activar() {
    	activo = true;
    }
    
    public void desactivar() {
    	activo = false;
    }
    
    public boolean estaActivo() {
    	return activo;
    }
}

