package core;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import com.riego.Sensor;

public class SensorDiscovererStub {

    public static List<Sensor> discover(String pluginPath, String configMedicionesPath) {
        List<Sensor> sensores = new ArrayList<>();

        Map<String, Integer> valores = cargarConfiguracion(configMedicionesPath);

        File pluginDir = new File(pluginPath);

        if (!pluginDir.exists() || !pluginDir.isDirectory()) {
            System.out.println("No se encontró la carpeta de plugins.");
            return sensores;
        }

        try {
            URL pluginUrl = pluginDir.toURI().toURL();
            URLClassLoader classLoader = null;
            classLoader = URLClassLoader.newInstance(new URL[]{pluginUrl});

            for (File file : pluginDir.listFiles()) {
                if (file.getName().endsWith(".class")) {
                	String className ="mock." + file.getName().replace(".class", "");
                    try {
                        Class<?> pluginClass = Class.forName(className, true, classLoader);
                        if (Sensor.class.isAssignableFrom(pluginClass)) {
                        	String simpleName = pluginClass.getSimpleName();
                        	Integer valor = valores.getOrDefault(simpleName, 0);
                            Sensor sensor;
                            if (valor != null) {
                                Constructor<?> constructor = pluginClass.getConstructor(int.class);
                                sensor = (Sensor) constructor.newInstance(valor);
                            } else {
                                sensor = (Sensor) pluginClass.getDeclaredConstructor().newInstance();
                            }

                            sensores.add(sensor);
                            System.out.println("Sensor mock cargado: " + className);
                        }
                    } catch (Exception e) {
                        System.out.println("Error al cargar clase: " + className);
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

    private static Map<String, Integer> cargarConfiguracion(String ruta) {
        Map<String, Integer> config = new HashMap<>();
        try {
            String json = new String(Files.readAllBytes(Paths.get(ruta)));
            JSONObject obj = new JSONObject(json);
            for (String key : obj.keySet()) {
                config.put(key, obj.getInt(key));
            }
        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo de mediciones.");
        }
        return config;
    }
}