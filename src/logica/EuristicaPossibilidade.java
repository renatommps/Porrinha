package logica;

import java.util.ArrayList;
import java.util.HashMap;

public class EuristicaPossibilidade extends Euristica {

	@Override
	public double accept(EstadoPorrinha estado) {
		ArrayList<ArrayList<Integer>> maoAposta = estado.getMaoAposta();

		int jogadosPeloAtual = estado.jogadosPeloAtual();
		ArrayList<Integer> possiveisValores = new ArrayList<Integer>();
		possiveisValores.add(jogadosPeloAtual);
		for(int i = 0; i < maoAposta.size(); ++i) {
			if(i != estado.getJogadorAtual()) {
				ArrayList<Integer> novaLista = new ArrayList<Integer>();
				for(Integer value : possiveisValores) {
					for(int possivel = 0; possivel <= maoAposta.get(i).get(0); ++possivel) {
						novaLista.add(value + possivel);
					}
				}
				possiveisValores = novaLista;
			}
		}
		
		HashMap<Integer, Integer> umaPinha = new HashMap<Integer, Integer>();
		for(Integer elemento : possiveisValores) {
			Integer count = umaPinha.get(elemento);
			umaPinha.put(elemento, count != null ? count + 1 : 1);
		}
		
		int apostaDoJogador = maoAposta.get(estado.getJogadorAtual()).get(1);
		
		int contagem = umaPinha.containsKey(apostaDoJogador) ? umaPinha.get(apostaDoJogador) : 0;
		
		int max = 0;
		
		for(Integer key : umaPinha.keySet()) {
			if(umaPinha.get(key) > max) {
				max = umaPinha.get(key);
			}
		}
		
		double nota = (double) contagem / max;
		
		return nota;
	}

}
