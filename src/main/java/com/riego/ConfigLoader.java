package com.riego;

import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigLoader {
    private final JSONObject config;

    public ConfigLoader(String rutaConfig) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(rutaConfig)));
            this.config = new JSONObject(json);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar configuración", e);
        }
    }

    public String getRutaPlugins() {
        return config.getString("ruta_evaluadores");
    }

    public JSONObject getUmbrales() {
        return config.getJSONObject("umbrales");
    }
}
