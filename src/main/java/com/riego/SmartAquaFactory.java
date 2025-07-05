package com.riego;
import java.util.List;

public class SmartAquaFactory {

    public static SmartAqua crear(String rutaConfig) {

        ConfigLoader configLoader = new ConfigLoader(rutaConfig);

        List<Evaluador> evaluadores = EvaluadorInitializer.inicializar(
                configLoader.getRutaEvaluadores(),
                configLoader.getUmbrales()
        );

        Aspersor aspersor = SistemaRiegoBuilder.crearAspersor();
        AdministradorRiego administrador = SistemaRiegoBuilder.crearAdministrador(aspersor);
        RegistradorDeObservadores registrador = SistemaRiegoBuilder.crearRegistrador(evaluadores);

        return new SmartAqua(evaluadores, administrador, aspersor, registrador);
    }
}
