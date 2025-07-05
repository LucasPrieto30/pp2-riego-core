package com.riego;

import java.util.List;

public class SistemaRiegoBuilder {

    public static Aspersor crearAspersor() {
        return new Aspersor();
    }

    public static AdministradorRiego crearAdministrador(Aspersor aspersor) {
        return new AdministradorRiego(aspersor);
    }

    public static RegistradorDeObservadores crearRegistrador(List<Evaluador> evaluadores) {
        return new RegistradorDeObservadores(evaluadores);
    }
}
