package logica;

import java.util.ArrayList;

public class EuristicaPossibilidade extends Euristica {


	@Override
	public ArrayList<Double> accept(EstadoPorrinha estado) {
		//TODO
		System.out.println("Olar");
		ArrayList<Double> ret = new ArrayList<Double>();
		for(int i = 0; i < estado.numJogadores(); ++i) {
			ret.add(new Double(Math.random()));
		}
		return ret;
	}
	

}
