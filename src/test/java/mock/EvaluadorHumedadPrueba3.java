package mock;

import java.util.Timer;
import java.util.TimerTask;

import com.riego.Evaluador;

public class EvaluadorHumedadPrueba3 extends Evaluador {

    public EvaluadorHumedadPrueba3() {
        iniciarEvaluaciones();
    }

    @Override
    protected int obtenerMedicion() {
        return 3;
    }

    @Override
    protected void iniciarEvaluaciones() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int medicion = obtenerMedicion();
                debeRegar = medicion < umbral;
                notificarObservadores();
            }
        }, 0, 3000);
    }
}
