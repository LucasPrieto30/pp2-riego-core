package mock;

import com.riego.Sensor;

public class SensorHumedadMock extends Sensor {

    @Override
    public void medir() {
    	valorMedido = 1;
        notificarObservadores();
    }

    @Override
    public int getValorMedido() {
        return valorMedido;
    }

}
