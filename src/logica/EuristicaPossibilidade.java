package logica;

import java.util.ArrayList;
import java.util.List;

public class EuristicaPossibilidade extends Euristica {


	@Override
	public ArrayList<Double> accept(EstadoPorrinha estado) {
		ArrayList<ArrayList<Integer>> maoAposta = estado.getMaoAposta();
		int jogados = estado.jogadosPeloAtual();
		int sum = 0;
		for(ArrayList<Integer> jogador : maoAposta) {
			sum += jogador.get(0);
		}
		int atual = estado.getJogadorAtual();
		sum -= jogados;
		int possibilities = 0;
		//TODO
		///////////////
		//TODO
		
		ArrayList<Double> ret = new ArrayList<Double>();
		for(int i = 0; i < estado.numJogadores(); ++i) {
			ret.add(new Double(Math.random()));
		}
		return ret;
	}

}
