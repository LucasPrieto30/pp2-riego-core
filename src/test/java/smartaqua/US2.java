package smartaqua;
import com.riego.LoggerActivaciones;
import com.riego.SmartAqua;
import com.riego.SmartAquaFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class US2 {
	LoggerActivaciones logger;
	
	@BeforeEach
	public void setUp() {
        logger = new LoggerActivaciones();
	}
	
	@Test
    public void ca1ActivacionRegistrada() throws InterruptedException {
        SmartAqua smartAqua = SmartAquaFactory.crear("src/test/java/resources/config/loggerRegistraActivacionConfig.json");
        smartAqua.agregarObservador(logger);
        smartAqua.evaluarTodos();

        List<String> logs = logger.getLogs();
        assertTrue(logs.get(0).contains("Riego activado por EvaluadorHumedad"), "El log debe contener la clase evaluadora.");
    }

    @Test
    public void ca2NoSeRegistraActivacion() throws InterruptedException {
        SmartAqua smartAqua = SmartAquaFactory.crear("src/test/java/resources/config/loggerNoRegistraActivacionConfig.json");
        smartAqua.agregarObservador(logger);
        smartAqua.evaluarTodos();

        List<String> logs = logger.getLogs();
        assertTrue(logs.isEmpty(), "No debe haber activaciones registradas si no hay evaluadores.");
    }
}
