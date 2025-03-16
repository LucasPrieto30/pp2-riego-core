package com.riego;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.reflect.Method;

public class PluginLoader {
    private static final String PLUGIN_PATH = "plugins/";

    public static void cargarPlugins() {
        File pluginDir = new File(PLUGIN_PATH);
        if (!pluginDir.exists() || !pluginDir.isDirectory()) {
            System.out.println("No se encontraron plugins.");
            return;
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
                        System.out.println("Valor del sensor: " + sensor.obtenerValor());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
