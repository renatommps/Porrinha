package logica;

import java.util.ArrayList;

public class EuristicaAleatoria implements Euristica {

	public EuristicaAleatoria() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Double> eval(EstadoArvore estado) {
		ArrayList<Double> ret = new ArrayList<Double>();
		for(int i = 0; i < 3; ++i) {
			ret.add(new Double(Math.random()));
		}
		return ret;
	}

	

}
