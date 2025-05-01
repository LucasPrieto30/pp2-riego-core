package smartwater;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.riego.*;

public class US1 {
	
    @Test
    public void ca1RiegoActivado() throws InterruptedException {
    	 SmartWater smartWater = SmartWaterFactory.crear("src/test/java/resources/config/activaRiegoConfig.json");
         Thread.sleep(4000); // esperar que mida
         assertTrue(smartWater.riegoActivado(), "Debería activarse el riego (medición 1, umbral 2)");
    }

    @Test
    public void ca2RiegoNoActivado() throws InterruptedException {
   	 	SmartWater smartWater = SmartWaterFactory.crear("src/test/java/resources/config/noActivaRiegoConfig.json");
        Thread.sleep(4000); // esperar que mida
        assertFalse(smartWater.riegoActivado(), "No debería activarse el riego (medición 3, umbral 2)");
    }
}
