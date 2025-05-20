package smartaqua;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.riego.*;

public class US1 {
	
    @Test
    public void ca1RiegoActivado() throws InterruptedException {
    	 SmartAqua smartAqua = SmartAquaFactory.crear("src/test/java/resources/config/activaRiegoConfig.json");
         smartAqua.getEvaluadores().forEach(t -> t.evaluar());
         assertTrue(smartAqua.riegoActivado(), "Debería activarse el riego (medición 1, umbral 2)");
    }

    @Test
    public void ca2RiegoNoActivado() throws InterruptedException {
   	 	SmartAqua smartAqua = SmartAquaFactory.crear("src/test/java/resources/config/noActivaRiegoConfig.json");
        smartAqua.getEvaluadores().forEach(t -> t.evaluar());
        assertFalse(smartAqua.riegoActivado(), "No debería activarse el riego (medición 3, umbral 2)");
    }
}
