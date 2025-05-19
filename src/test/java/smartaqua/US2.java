
package smartaqua;
import com.riego.LoggerActivaciones;
import com.riego.SmartAqua;
import com.riego.SmartAquaFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class US2 {

    @Test
    public void ca1ActivacionRegistrada() throws InterruptedException {
        // Configuración: EvaluadorHumedad siempre requiere riego (medición 25, umbral 30)
        SmartAqua smartAqua = SmartAquaFactory.crear("src/test/java/resources/config/loggerRegistraActivacionConfig.json");
        Thread.sleep(4000); 

        List<String> logs = smartAqua.getLogger().getLogs();
    
        assertTrue(logs.size() > 0, "Se esperaba al menos una activación registrada.");
        assertTrue(logs.get(0).contains("EvaluadorHumedad"), "El log debe contener la clase evaluadora.");
    }

    @Test
    public void ca2NoSeRegistraNadaSinEvaluadores() throws InterruptedException {
        // Configuración: carpeta vacía, no se cargan evaluadores
        SmartAqua smartAqua = SmartAquaFactory.crear("src/test/java/resources/config/loggerNoRegistraActivacionConfig.json");
        Thread.sleep(4000);

        List<String> logs = smartAqua.getLogger().getLogs();
        assertEquals(0, logs.size(), "No debe haber activaciones registradas si no hay evaluadores.");
    }
}
