package com.riego;

public class AdministradorRiego {
    private final Aspersor aspersor;
    
    public AdministradorRiego(Aspersor aspersor) {
        this.aspersor = aspersor;
    }
    
    public void procesar(boolean seDebeActivarRiego) {
        if (seDebeActivarRiego) {
            aspersor.activar();
        } else {
            aspersor.desactivar();
        }
    }
}
