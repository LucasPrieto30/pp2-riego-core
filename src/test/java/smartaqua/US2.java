package smartaqua;
import com.riego.SmartAqua;
import com.riego.SmartAquaFactory;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class US2 {

    @Test
    public void ca1ActivacionRegistrada() throws InterruptedException {
        SmartAqua smartAqua = SmartAquaFactory.crear("src/test/java/resources/config/loggerRegistraActivacionConfig.json");
        smartAqua.getEvaluadores().forEach(t -> t.evaluar());

        List<String> logs = smartAqua.getLogs();
    
        assertTrue(logs.get(0).contains("EvaluadorHumedad"), "El log debe contener la clase evaluadora.");
    }

    @Test
    public void ca2NoSeRegistraActivacion() throws InterruptedException {
        // Configuración: carpeta vacía, no se cargan evaluadores
        SmartAqua smartAqua = SmartAquaFactory.crear("src/test/java/resources/config/loggerNoRegistraActivacionConfig.json");
        smartAqua.getEvaluadores().forEach(t -> t.evaluar());

        List<String> logs = smartAqua.getLogs();
        assertEquals(0, logs.size(), "No debe haber activaciones registradas si no hay evaluadores.");
    }
}
