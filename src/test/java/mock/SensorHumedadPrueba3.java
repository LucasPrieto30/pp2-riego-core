//package mock;
//
//import com.riego.EstrategiaEvaluacion;
//import com.riego.Sensor;
//
//public class SensorHumedadPrueba3 extends Sensor {
//	
//    @Override
//    public int medir() {
//        notificarObservadores();
//        this.valorMedido = 3;
//        return this.valorMedido;
//    }
//
//    @Override
//    public int getValorMedido() {
//        return valorMedido;
//    }
//
//    @Override
//    public EstrategiaEvaluacion getEstrategiaEvaluacion() {
//        return (valor, umbral) -> valor < umbral;
//    }
//
//}
