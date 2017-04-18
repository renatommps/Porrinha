package logica;

import java.util.List;

public class EstrategiaAleatoria implements Estrategia {

	public EstrategiaAleatoria() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int definirJogada(HistoricoDeRodadas historicoDeRodadas, int palitosDoJogador, List<Jogador> jogadores) {
		// OBS: O histórico não está sendo usado, deixei ai caso alguma
		// euristica vá usar...

		int jogadaMinima = 0;

		// primeira rodada, regra especial que não pode jogar zero
		if (historicoDeRodadas.estaVazio()) {
			jogadaMinima = 1;
		}

		int palistosJogados = jogadaMinima + (int) (Math.random() * ((palitosDoJogador - jogadaMinima) + 1));

		return palistosJogados;
	}

	@Override
	public int definirApostaDeResultado(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores,
			int palitosDoJogador) {
		// OBS: O histórico não está sendo usado, deixei ai caso alguma
		// euristica vá usar...

		boolean apostaConcretizada = false;
		List<Aposta> apostasDaRodada = historicoDeRodadas.getUltimaRodada().getApostas();
		int totalDePalitosNoJogo = getTotalDePalitosNoJogo(jogadores);
		int apostaMinima = 0;
		int valorDaAposta = 0;

		// primeira rodada, regra especial que não pode jogar zero
		if (historicoDeRodadas.estaVazio()) {
			int numeroDeJogadores = jogadores.size();
			apostaMinima = numeroDeJogadores;
		}

		// faz uma aposta aleatória, excluíndo valores já apostados por outros
		// jogadores
		while (!apostaConcretizada) {

//			// utiliza o valor de palitos jogados pelo jogador para iniciar o
//			// valor total das apostas
//			valorDaAposta += palitosDoJogador;
//
//			// analisa as apostas já feitas para tentar inferir valores mais
//			// concretos para os jogadores que já apostaram
//			for (Aposta aposta : apostasDaRodada) {
//
//			}
//			
//			// para cada jogador (que já não fez uma aposta), faz um chute do
//			// valor que o mesmo jogou, e soma com o valor dos demais jogadores
//			// para obter o valor total da aposta
//			for (Jogador jogador : jogadores) {
//				// verifica se o jogador não está na lista de apostas já feitas
//				if (jogador não ta na lista){ // IMPLEMENTAR DEPOIS!!!!!
//					int apostaDeJogada = apostaMinima + (int) (Math.random() * ((jogador.getPalitos() - apostaMinima) + 1));
//					valorDaAposta += apostaDeJogada;
//				}
//			}
			
			valorDaAposta = apostaMinima + (int) (Math.random() * ((totalDePalitosNoJogo - apostaMinima) + 1));

			if (!apostaJafeita(valorDaAposta, apostasDaRodada)) {
				apostaConcretizada = true;
			}
		}

		return valorDaAposta;
	}

	private boolean apostaJafeita(int aposta, List<Aposta> apostasDaRodada) {
		boolean apostaFeita = false;

		if (!apostasDaRodada.isEmpty()) {
			for (Aposta apostaAtual : apostasDaRodada) {
				if (aposta == apostaAtual.getNumeroDePalitosApostados()) {
					apostaFeita = true;
					break;
				}
			}
		}

		return apostaFeita;
	}

	private int getTotalDePalitosNoJogo(List<Jogador> jogadores) {
		int totalDePalitos = 0;

		for (Jogador jogador : jogadores) {
			totalDePalitos += jogador.getPalitos();
		}

		return totalDePalitos;
	}

}
