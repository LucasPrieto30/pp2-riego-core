package mock;

import java.util.Timer;
import java.util.TimerTask;

import com.riego.Evaluador;

public class EvaluadorHumedadPrueba1 extends Evaluador {

    @Override
    protected int obtenerMedicion() {
        return 1;
    }
    
    public void evaluar() {
    	ultimaMedicion = obtenerMedicion();
        debeRegar = ultimaMedicion < umbral;
        notificarObservadores();

    }
}