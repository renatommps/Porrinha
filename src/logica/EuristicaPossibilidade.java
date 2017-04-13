package logica;

import java.util.ArrayList;

public class EuristicaPossibilidade extends Euristica {


	@Override
	public ArrayList<Double> accept(EstadoPorrinha estado) {
		ArrayList<ArrayList<Integer>> maoAposta = estado.getMaoAposta();
		int jogados = estado.jogadosPeloAtual();
		
		ArrayList<Double> ret = new ArrayList<Double>();
		for(int i = 0; i < estado.numJogadores(); ++i) {
			ret.add(new Double(Math.random()));
		}
		return ret;
	}
	

}
