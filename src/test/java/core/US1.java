package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.riego.DispositivoRiego;
import com.riego.SmartWater;
import mock.SensorHumedadMock;

public class US1 {

	private SmartWater smartWater;
	private int umbralActivacion;
	private SensorHumedadMock sensor;
	private DispositivoRiego dispositivoRiego;
	
    @BeforeEach
    public void setUp() {
    	smartWater = new SmartWater();
    	umbralActivacion = 2;
    	sensor = new SensorHumedadMock(umbralActivacion);
    	dispositivoRiego = smartWater.getDispositivoRiego();
    }
    
    @Test
    public void ca1ActivarRiego() {
        smartWater.conectarSensorADispositivoRiego(sensor);
        
        sensor.iniciarMediciones(); //siempre mide 1
        
        int valorMedido = sensor.getValorMedido();
        
        assertTrue(valorMedido < umbralActivacion);
        assertTrue(dispositivoRiego.estaActivo());
    }

    @Test
    public void ca2SensorNoConectadoNoActivaRiego() {
        sensor.iniciarMediciones(); //Esta por debajo del umbral pero no notifica al dispositivo
        
        int valorMedido = sensor.getValorMedido();

        assertTrue(valorMedido < umbralActivacion);
        assertFalse(dispositivoRiego.estaActivo());
    }
}
