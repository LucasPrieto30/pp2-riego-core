package mock;

import com.riego.EstrategiaEvaluacion;
import com.riego.Sensor;

public class SensorHumedadMock extends Sensor {

    @Override
    public void medir() {
    	valorMedido = 2;
        notificarObservadores();
    }

    @Override
    public int getValorMedido() {
        return valorMedido;
    }

    @Override
    public EstrategiaEvaluacion getEstrategiaEvaluacion() {
        return (valor, umbral) -> valor < umbral;
    }

}
