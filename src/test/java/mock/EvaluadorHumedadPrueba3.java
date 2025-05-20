package mock;

import java.util.Timer;
import java.util.TimerTask;

import com.riego.Evaluador;

public class EvaluadorHumedadPrueba3 extends Evaluador {

    @Override
    protected int obtenerMedicion() {
        return 3;
    }
    
    public void evaluar() {
    	ultimaMedicion = obtenerMedicion();
    	debeRegar = ultimaMedicion < umbral;
    	notificarObservadores();
    }

}
