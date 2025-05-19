package com.riego;

import java.util.ArrayList;
import java.util.List;

public class LoggerMediciones implements Observer {

    private final List<String> log = new ArrayList<>();

    @Override
    public void actualizar(Evaluador evaluador, boolean activaRiego) {
        String linea = "Evaluador " + evaluador.getClass().getSimpleName() + " activó riego: " + activaRiego;
        log.add(linea);
        System.out.println("[LOG] " + linea);
    }

    public List<String> getLogs() {
        return new ArrayList<>(log);
    }

    public void limpiarLog() {
        log.clear();
    }
}