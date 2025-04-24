package mock;

import com.riego.EstrategiaEvaluacion;
import com.riego.Sensor;

public class SensorHumedadPrueba1 extends Sensor {
	
    @Override
    public int medir() {
        notificarObservadores();
        this.valorMedido = 1;
        return this.valorMedido;
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
