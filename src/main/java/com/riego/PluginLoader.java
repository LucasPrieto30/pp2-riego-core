package com.riego;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class PluginLoader {
    private static final String PLUGIN_PATH = "plugins/";  // Carpeta donde se almacenan los sensores dinámicos

    public static List<PluginSensor> cargarPlugins() {
        List<PluginSensor> sensores = new ArrayList<>();
        File pluginDir = new File(PLUGIN_PATH);

        if (!pluginDir.exists() || !pluginDir.isDirectory()) {
            System.out.println("No se encontró la carpeta de plugins.");
            return sensores;
        }

        for (File file : pluginDir.listFiles()) {
            if (file.getName().endsWith(".class")) {
                try {
                    URL url = pluginDir.toURI().toURL();
                    URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
                    String className = file.getName().replace(".class", "");
                    Class<?> pluginClass = classLoader.loadClass(className);

                    if (PluginSensor.class.isAssignableFrom(pluginClass)) {
                        PluginSensor sensor = (PluginSensor) pluginClass.getDeclaredConstructor().newInstance();
                        sensor.inicializar();
                        sensores.add(sensor);
                        System.out.println("Sensor dinámico cargado: " + className);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sensores;
    }
}

