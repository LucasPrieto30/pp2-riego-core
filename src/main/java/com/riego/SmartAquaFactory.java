package com.riego;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class SmartAquaFactory {

    public static SmartAqua crear(String rutaConfig) {
    	
        String configJson;
		try {
			configJson = new String(Files.readAllBytes(Paths.get(rutaConfig)));
		
	        JSONObject config = new JSONObject(configJson);
	
	        String rutaPlugins = config.getString("ruta_plugins");
	        JSONObject umbralesJSON = config.getJSONObject("umbrales");

	        List<Sensor> sensores = SensorDiscoverer.discover(rutaPlugins);
	        
	        Map<Sensor, EvaluadorMediciones> evaluadores = new HashMap<>();
	        for (Sensor s : sensores) {
	            int umbral = umbralesJSON.optInt(s.getClass().getSimpleName(), 0);
	            evaluadores.put(s, new EvaluadorMediciones(umbral, s));
	        }
	
	        Aspersor aspersor = new Aspersor();
	        AdministradorRiego administradorRiego = new AdministradorRiego(evaluadores, aspersor);
	
	        return new SmartAqua(administradorRiego, sensores, aspersor);
		} catch (IOException e) {
			throw new RuntimeException("Error al crear SmartAqua desde la configuración", e);
		}
    }
}
