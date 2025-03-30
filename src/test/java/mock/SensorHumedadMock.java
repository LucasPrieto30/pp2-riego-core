package mock;

import com.riego.Sensor;

public class SensorHumedadMock extends Sensor {

    public SensorHumedadMock(int umbral) {
    	super(umbral);
    }

    @Override
    public void medir() {
        valor = 1;
        notificarObservadores();
    }

    @Override
    public int getValor() {
        return valor;
    }

    @Override
    public boolean necesitaRiego() {
        return valor < umbral;
    }
}
