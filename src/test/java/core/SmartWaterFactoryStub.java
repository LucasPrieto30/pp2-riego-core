package core;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.riego.Sensor;
import com.riego.SmartWater;
import com.riego.Aspersor;
import com.riego.AdministradorRiego;

import com.riego.EvaluadorMediciones;

public class SmartWaterFactoryStub {

    public static SmartWater crear(String rutaPlugins, String rutaUmbrales, String rutaMedicionesFijas) {
        List<Sensor> sensores = SensorDiscovererStub.discover(rutaPlugins, rutaMedicionesFijas);
        Map<String, Integer> umbrales = cargarConfiguracion(rutaUmbrales);
        Map<Sensor, EvaluadorMediciones> evaluadores = new HashMap<>();
        for (Sensor s : sensores) {
            int umbral = umbrales.getOrDefault(s.getClass().getSimpleName(), 0);
            evaluadores.put(s, new EvaluadorMediciones(umbral, s));
        }

        Aspersor aspersor = new Aspersor();
        AdministradorRiego administrador = new AdministradorRiego(evaluadores, aspersor);
        return new SmartWater(administrador, sensores, aspersor);
    }

    private static Map<String, Integer> cargarConfiguracion(String ruta) {
        Map<String, Integer> config = new HashMap<>();
        try {
            String json = new String(Files.readAllBytes(Paths.get(ruta)));
            JSONObject obj = new JSONObject(json);
            for (String key : obj.keySet()) {
                config.put(key, obj.getInt(key));
            }
        } catch (Exception e) {
            System.out.println("No se pudo leer archivo de configuración.");
        }
        return config;
    }
}