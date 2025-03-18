package com.riego;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class PluginLoader {
    private static final String PLUGIN_PATH = "plugins/";  //Carpeta de plugins

    public static List<PluginSensor> cargarPlugins() {
        List<PluginSensor> sensores = new ArrayList<>();
        File pluginDir = new File(PLUGIN_PATH);

        if (!pluginDir.exists() || !pluginDir.isDirectory()) {
            System.out.println("No se encontró la carpeta de plugins.");
            return sensores;
        }

        try {
            URL pluginUrl = pluginDir.toURI().toURL();
            URLClassLoader classLoader = null;
            classLoader = URLClassLoader.newInstance(new URL[]{pluginUrl});
            
           // (new URL[]{pluginUrl}, PluginSensor.class.getClassLoader()) ;

            System.out.println("Rutas de búsqueda en classLoader:");
            for (URL url : classLoader.getURLs()) {
                System.out.println("   : " + url);
            }

            for (File file : pluginDir.listFiles()) {
                if (file.getName().endsWith(".class")) {
                    String className = file.getName().replace("/", ".").replace(".class", "");
                    System.out.println("Intentando cargar: " + className);

                    try {
                        Class<?> pluginClass  = Class.forName(className, true, classLoader);
                        if (PluginSensor.class.isAssignableFrom(pluginClass)) {
                            PluginSensor sensor = (PluginSensor) pluginClass.getDeclaredConstructor().newInstance();
                            sensor.inicializar();
                            sensores.add(sensor);
                            System.out.println("Sensor dinámico cargado: " + className);
                        } else {
                            System.out.println("" + className + " no implementa PluginSensor.");
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
}