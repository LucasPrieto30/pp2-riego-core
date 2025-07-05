package com.riego;

import org.json.JSONObject;
import java.util.List;

public class EvaluadorInitializer {

    public static List<Evaluador> inicializar(String rutaPlugins, JSONObject umbralesJSON) {
        List<Evaluador> evaluadores = EvaluadorDiscoverer.discover(rutaPlugins);

        for (Evaluador evaluador : evaluadores) {
            String nombreClase = evaluador.getClass().getSimpleName();
            int umbral = umbralesJSON.optInt(nombreClase, 0);
            evaluador.configurarUmbral(umbral);
        }

        return evaluadores;
    }
}
