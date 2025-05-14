package smartaqua;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.riego.LoggerMediciones;
import com.riego.Sensor;
import com.riego.SmartAqua;
import com.riego.SmartAquaFactory;

public class US2 {

    private LoggerMediciones logger;

    @BeforeEach
    public void setUp() {
        logger = new LoggerMediciones();
    }

    @Test
    public void ca1_RegistraMedicion() throws InterruptedException {
        SmartAqua smartWater = SmartAquaFactory.crear("src/test/java/resources/config/registraMedicionConfig.json");
        smartWater.getEvaluadores().forEach(evaluador -> evaluador.agregarObservador(logger));
        Thread.sleep(4000);
        List<String> logs = logger.getLogs();

        assertTrue(logs.size() < 0, "El log debería contener al menos una medición.");
    }

    @Test
    public void ca2_NoRegistraMedicion() throws InterruptedException {
        SmartAqua smartWater = SmartAquaFactory.crear("src/test/java/resources/config/noRegistraMedicionConfig.json");
        smartWater.getEvaluadores().forEach(evaluador -> evaluador.agregarObservador(logger));
        Thread.sleep(4000);
        List<String> logs = logger.getLogs();

        assertTrue(logs.size() == 0, "No se registraron mediciones.");
    }
}