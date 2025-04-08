package mock;

import com.riego.EstrategiaEvaluacion;
import com.riego.Sensor;

public class SensorHumedadMock extends Sensor {

    @Override
    public int medir() {
    	valorMedido = 2;
        notificarObservadores();
        return valorMedido;
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
