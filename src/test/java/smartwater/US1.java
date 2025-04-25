package smartwater;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.riego.*;

public class US1 {
	
	private String ruta_sensor_mide_1;
	private String ruta_sensor_mide_3;
	private String ruta_umbral;

    @BeforeEach
    public void setUp() {
    	ruta_sensor_mide_1 = "src/test/java/resources/plugins/Sensor1";
    	ruta_sensor_mide_3 = "src/test/java/resources/plugins/Sensor3";
    	ruta_umbral = "src/test/java/resources/config/umbral_2.json";
    }
    
    @Test
    public void ca1RiegoActivado() throws InterruptedException {
    	 SmartWater smartWater = SmartWaterFactory.crear(ruta_sensor_mide_1, ruta_umbral);
         Thread.sleep(4000); // esperar que mida
         assertTrue(smartWater.riegoActivado(), "Debería activarse el riego (medición 1, umbral 2)");
    }

    @Test
    public void ca2RiegoNoActivado() throws InterruptedException {
   	 	SmartWater smartWater = SmartWaterFactory.crear(ruta_sensor_mide_3, ruta_umbral);
        Thread.sleep(4000); // esperar que mida
        assertFalse(smartWater.riegoActivado(), "No debería activarse el riego (medición 3, umbral 2)");
    }
}
