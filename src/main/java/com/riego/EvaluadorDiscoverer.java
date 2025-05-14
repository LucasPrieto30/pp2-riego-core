package com.riego;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class EvaluadorDiscoverer {
    
    public static List<EvaluadorRiego> discover(String path) {
        List<EvaluadorRiego> evaluadores = new ArrayList<>();
        File pluginDir = new File(path);
        
        if (!pluginDir.exists() || !pluginDir.isDirectory()) {
            System.out.println("No se encontró la carpeta de plugins.");
            return evaluadores;
        }

        try {
            URL pluginUrl = pluginDir.toURI().toURL();
            URLClassLoader classLoader = null;
            classLoader = URLClassLoader.newInstance(new URL[]{pluginUrl});
            
            for (File file : pluginDir.listFiles()) {
                if (file.getName().endsWith(".class")) {
                    String className = file.getName().replace("/", ".").replace(".class", "");
                    System.out.println("Intentando cargar: " + className);
                    
                    try {
                        Class<?> pluginClass  = Class.forName(className, true, classLoader);
                        if (EvaluadorRiego.class.isAssignableFrom(pluginClass)) {
                        	EvaluadorRiego evaluador = (EvaluadorRiego) pluginClass.getDeclaredConstructor().newInstance();
                        	evaluadores.add(evaluador);
                            System.out.println("Evaluador cargado: " + className);
                        } else {
                            System.out.println("" + className + " no implementa EvaluadorRiego.");
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
        return evaluadores;
    }
}