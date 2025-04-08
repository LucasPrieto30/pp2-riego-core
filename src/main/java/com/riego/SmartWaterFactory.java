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

        Map<Sensor, EvaluadorRiego> mapaEvaluadores = sensores.stream()
        	    .collect(Collectors.toMap(
        	        sensor -> sensor,
        	        sensor -> new EvaluadorRiego(sensor, umbrales.get(sensor.getClass().getSimpleName()))
        	    ));

        Aspersor aspersor = new Aspersor();
        return new SmartWater(mapaEvaluadores, aspersor);   
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
