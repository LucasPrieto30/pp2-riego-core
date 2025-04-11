package com.riego;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class SmartWaterFactory {

    public static SmartWater crear(String rutaPlugins, String rutaConfig) {
        List<Sensor> sensores = SensorDiscoverer.discover(rutaPlugins);
        Map<String, Integer> umbrales = cargarConfiguracionDeUmbrales(rutaConfig);

        Map<Sensor, EvaluadorMediciones> evaluadores = new HashMap<>();
        for (Sensor s : sensores) {
            int umbral = umbrales.getOrDefault(s.getClass().getSimpleName(), 0);
            evaluadores.put(s, new EvaluadorMediciones(umbral, s));
        }

        Aspersor aspersor = new Aspersor();
        AdministradorRiego administradorRiego = new AdministradorRiego(evaluadores, aspersor);

        return new SmartWater(administradorRiego, sensores, aspersor);   
    }

    private static Map<String, Integer> cargarConfiguracionDeUmbrales(String ruta) {
    	Map<String, Integer> umbrales = new HashMap<>();
        try {
            String json = new String(Files.readAllBytes(Paths.get(ruta)));
            JSONObject obj = new JSONObject(json);
            for (String key : obj.keySet()) {
            	umbrales.put(key, obj.getInt(key));
            }
        } catch (Exception e) {
            System.out.println("No se pudo leer la configuración. Se usarán valores por defecto.");
        }
        return umbrales;
    }
}
