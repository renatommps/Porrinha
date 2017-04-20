package logica;

import java.util.ArrayList;

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
		
		int apostaDoJogador = maoAposta.get(estado.getJogadorAtual()).get(1);
		
		int contagem = 0;
		for(Integer possibilidade : possiveisValores) {
			if(possibilidade == apostaDoJogador) {
				++contagem;
			}
		}
		double nota = (double) contagem / possiveisValores.size();
		
		return nota;
	}

}
