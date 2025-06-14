package com.riego;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.json.JSONObject;

public class SmartAquaFactory {

    public static SmartAqua crear(String rutaConfig) {
    	
        String configJson;
        try {
            configJson = new String(Files.readAllBytes(Paths.get(rutaConfig)));
            System.out.println(configJson);
            JSONObject config = new JSONObject(configJson);

            String rutaPlugins = config.getString("ruta_evaluadores");
            JSONObject umbralesJSON = config.getJSONObject("umbrales");

            List<Evaluador> evaluadores = EvaluadorDiscoverer.discover(rutaPlugins);

            for (Evaluador evaluador : evaluadores) {
                String nombreClase = evaluador.getClass().getSimpleName();
                int umbral = umbralesJSON.optInt(nombreClase, 0);
                evaluador.configurarUmbral(umbral);
            }

            Aspersor aspersor = new Aspersor();
            AdministradorRiego administrador = new AdministradorRiego(aspersor);
            RegistradorDeObservadores registrador = new RegistradorDeObservadores(evaluadores);
            return new SmartAqua(evaluadores, administrador, aspersor, registrador);

        } catch (IOException e) {
            throw new RuntimeException("Error al crear SmartAqua desde la configuración", e);
        }
    }
}
