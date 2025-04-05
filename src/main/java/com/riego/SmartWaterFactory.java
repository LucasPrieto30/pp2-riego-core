package com.riego;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class SmartWaterFactory {

    public static SmartWater crear(String rutaPlugins, String rutaConfig) {
        List<Sensor> sensores = PluginLoader.cargarPlugins(rutaPlugins);
        Map<String, Integer> umbrales = cargarConfiguracionDeUmbrales(rutaConfig);

        List<EvaluadorRiego> evaluadores = new ArrayList<>();
        
        for (Sensor sensor : sensores) {
        	String tipo = sensor.getClass().getSimpleName();
            int umbral = umbrales.getOrDefault(tipo, 50);
            evaluadores.add(new EvaluadorRiego(sensor, umbral));
        }

        Aspersor aspersor = new Aspersor(evaluadores);
        return new SmartWater(evaluadores, aspersor);
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
