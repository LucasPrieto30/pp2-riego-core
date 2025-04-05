package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.riego.Aspersor;
import com.riego.EvaluadorRiego;
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
    	int umbralActivacion = 3;
    	EvaluadorRiego evaluador = new EvaluadorRiego(sensor, umbralActivacion);
        SmartWater smartWater = new SmartWater(List.of(evaluador), aspersor);

        Thread.sleep(5000); // esperar a que mida el sensor
        
        assertTrue(smartWater.riegoActivado());
    }

    @Test
    public void ca2RiegoNoActivado() throws InterruptedException {
    	int umbralActivacion = 1;
    	EvaluadorRiego evaluador = new EvaluadorRiego(sensor, umbralActivacion);
        SmartWater smartWater = new SmartWater(List.of(evaluador), aspersor);

        Thread.sleep(5000);
        
        assertFalse(smartWater.riegoActivado());
    }
}
