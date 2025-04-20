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
	
	private String ruta_plugins;
	private String ruta_umbral;
	private String ruta_medicion_1;
	private String ruta_medicion_3;

    @BeforeEach
    public void setUp() {
    	ruta_plugins = "src/test/java/resources/plugins";
    	ruta_umbral = "src/test/java/resources/config/umbral_2.json";
    	ruta_medicion_1 = "src/test/java/resources/config/medicion_fija_1.json"; // mide 1
    	ruta_medicion_3 = "src/test/java/resources/config/medicion_fija_3.json"; // mide 3
    }
    
    @Test
    public void ca1RiegoActivado() throws InterruptedException {
    	 SmartWater smartWater = SmartWaterFactoryStub.crear(ruta_plugins, ruta_umbral, ruta_medicion_1);
         Thread.sleep(4000); // esperar que mida
         assertTrue(smartWater.riegoActivado(), "Debería activarse el riego (medición 1, umbral 2)");
    }

    @Test
    public void ca2RiegoNoActivado() throws InterruptedException {
   	 	SmartWater smartWater = SmartWaterFactoryStub.crear(ruta_plugins, ruta_umbral, ruta_medicion_3);
        Thread.sleep(4000); // esperar que mida
        assertFalse(smartWater.riegoActivado(), "No debería activarse el riego (medición 3, umbral 2)");
    }
}
