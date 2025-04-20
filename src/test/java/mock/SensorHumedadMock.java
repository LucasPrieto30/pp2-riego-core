package mock;

import com.riego.EstrategiaEvaluacion;
import com.riego.Sensor;

public class SensorHumedadMock extends Sensor {
	private int medicionFija;
	
	public SensorHumedadMock(int medicionFija) {
		this.medicionFija = medicionFija;
	}
	
    @Override
    public int medir() {
        notificarObservadores();
        this.valorMedido = medicionFija;
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
