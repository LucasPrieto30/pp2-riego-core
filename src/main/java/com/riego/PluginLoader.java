package com.riego;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class PluginLoader {
    private static final String PLUGIN_PATH = "plugins/";
    private static final String CONFIG_PATH = PLUGIN_PATH + "config/sensores.json";
    
    public static List<Sensor> cargarPlugins() {
        List<Sensor> sensores = new ArrayList<>();
        File pluginDir = new File(PLUGIN_PATH);

        Map<String, Integer> config = leerConfiguracion();
        
        if (!pluginDir.exists() || !pluginDir.isDirectory()) {
            System.out.println("No se encontró la carpeta de plugins.");
            return sensores;
        }

        try {
            URL pluginUrl = pluginDir.toURI().toURL();
            URLClassLoader classLoader = null;
            classLoader = URLClassLoader.newInstance(new URL[]{pluginUrl});
            
            System.out.println("Rutas de búsqueda en classLoader:");
            for (URL url : classLoader.getURLs()) {
                System.out.println("   : " + url);
            }

            for (File file : pluginDir.listFiles()) {
                if (file.getName().endsWith(".class")) {
                    String className = file.getName().replace("/", ".").replace(".class", "");
                    System.out.println("Intentando cargar: " + className);
                    int umbral = config.getOrDefault(className, 50);
                    
                    try {
                        Class<?> pluginClass  = Class.forName(className, true, classLoader);
                        if (Sensor.class.isAssignableFrom(pluginClass)) {
                        	Constructor<?> constructor = pluginClass.getConstructor(int.class);
                        	Sensor sensor = (Sensor) constructor.newInstance(umbral);
                            sensores.add(sensor);
                            System.out.println("Sensor dinámico cargado: " + className);
                        } else {
                            System.out.println("" + className + " no implementa Sensor.");
                        }
                    } catch (ClassNotFoundException e) {
                        System.out.println("ERROR: No se encontró la clase " + className);
                        e.printStackTrace();
                    }
                }
            }

            classLoader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sensores;
    }
    
    private static Map<String, Integer> leerConfiguracion() {
        Map<String, Integer> config = new HashMap<>();
        try {
            String json = new String(Files.readAllBytes(Paths.get(CONFIG_PATH)));
            JSONObject obj = new JSONObject(json);
            for (String key : obj.keySet()) {
                config.put(key, obj.getInt(key));
            }
        } catch (Exception e) {
            System.out.println("No se pudo leer la configuración. Se usarán valores por defecto.");
        }
        return config;
    }
}