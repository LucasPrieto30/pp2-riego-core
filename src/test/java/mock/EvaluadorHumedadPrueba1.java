package mock;

import java.util.Timer;
import java.util.TimerTask;

import com.riego.Evaluador;

public class EvaluadorHumedadPrueba1 extends Evaluador {

    public EvaluadorHumedadPrueba1() {
        iniciarEvaluaciones();
    }

    @Override
    protected int obtenerMedicion() {
        return 1;
    }

    @Override
    protected void iniciarEvaluaciones() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	ultimaMedicion = obtenerMedicion();
                debeRegar = ultimaMedicion < umbral;
                notificarObservadores();
            }
        }, 0, 3000);
    }
}