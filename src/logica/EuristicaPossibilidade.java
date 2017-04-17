package logica;

import java.util.ArrayList;
import java.util.List;

public class EuristicaPossibilidade extends Euristica {

	@Override
	public ArrayList<Double> accept(EstadoPorrinha estado) {
		ArrayList<Double> notas = new ArrayList<Double>();
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
		
		for (ArrayList<Integer> juninhoPlayer : maoAposta) {
			int apostaDoJogador = juninhoPlayer.get(1);
			int contagem = 0;
			for(Integer possibilidade : possiveisValores) {
				if(possibilidade == apostaDoJogador) {
					++contagem;
				}
			}
			double nota = (double) contagem / possiveisValores.size();
			notas.add(nota);
		}
		
		return notas;
	}

}
