package com.riego;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoggerActivaciones implements Observer {

    private final List<String> logs = new ArrayList<>();

    @Override
    public void actualizar(Evaluador evaluador, boolean activaRiego) {
    	String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());
    	String mensaje = hora + " - Riego activado por " 
    		    + evaluador.getClass().getSimpleName()
    		    + " (medición: " + evaluador.getUltimaMedicion()
    		    + ", umbral: " + evaluador.getUmbral() + ")";
    	if (activaRiego) {
    		logs.add(mensaje);    		
    	}
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }

    public void limpiarLog() {
        logs.clear();
    }
}