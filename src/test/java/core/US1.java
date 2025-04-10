package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.riego.AdministradorRiego;
import com.riego.Aspersor;
import com.riego.EvaluadorMediciones;
import com.riego.Sensor;
import com.riego.SmartWater;
import mock.SensorHumedadMock;

public class US1 {
	
    private SensorHumedadMock sensor;
    private Aspersor aspersor;

    @BeforeEach
    public void setUp() {
        sensor = new SensorHumedadMock(); // mide siempre 2
        aspersor = new Aspersor();
    }
    
    @Test
    public void ca1RiegoActivado() throws InterruptedException {
    	int umbralActivacionAlto = 3;
    	EvaluadorMediciones evaluador = new EvaluadorMediciones(umbralActivacionAlto, sensor.getEstrategiaEvaluacion());
    	Map<Sensor, EvaluadorMediciones> evaluadores = new HashMap<Sensor, EvaluadorMediciones>();
    	evaluadores.put(sensor, evaluador);
        AdministradorRiego adminastrador = new AdministradorRiego(evaluadores, aspersor);

        SmartWater smartWater = new SmartWater(adminastrador, List.of(sensor), aspersor);

        Thread.sleep(5000); // esperar a que mida el sensor
        
        assertTrue(smartWater.riegoActivado());
    }

    @Test
    public void ca2RiegoNoActivado() throws InterruptedException {
    	int umbralActivacionBajo = 1;
    	EvaluadorMediciones evaluador = new EvaluadorMediciones(umbralActivacionBajo, sensor.getEstrategiaEvaluacion());
    	Map<Sensor, EvaluadorMediciones> evaluadores = new HashMap<Sensor, EvaluadorMediciones>();
    	evaluadores.put(sensor, evaluador);
        AdministradorRiego adminastrador = new AdministradorRiego(evaluadores, aspersor);

        SmartWater smartWater = new SmartWater(adminastrador, List.of(sensor), aspersor);
        Thread.sleep(5000);
        
        assertFalse(smartWater.riegoActivado());
    }
}
