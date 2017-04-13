package logica;

import java.util.ArrayList;

public class EuristicaAleatoria extends Euristica {

	public EuristicaAleatoria() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Double> accept(EstadoPorrinha estado) {
		ArrayList<Double> ret = new ArrayList<Double>();
		for(int i = 0; i < estado.numJogadores(); ++i) {
			ret.add(new Double(Math.random()));
		}
		return ret;
	}

	

}
