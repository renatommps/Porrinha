package logica;

import java.util.List;

public class EstrategiaAleatoria implements Estrategia {

	public EstrategiaAleatoria() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int definirJogada(HistoricoDeRodadas historicoDeRodadas, int palitosDoJogador, List<Jogador> jogadores) {
		/*
		 * OBS: O histórico não está sendo usado, deixei ai caso alguma
		 * euristica vá usar...
		 */

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
			int palitosJogados, int palitosDoJogador) {
		/*
		 * OBS: O histórico não está sendo usado, deixei ai caso algumab
		 * euristica vá usar...
		 */

		List<Aposta> apostasDaRodada = historicoDeRodadas.getUltimaRodada().getApostas();
		int totalDePalitosNoJogo = getTotalDePalitosNoJogo(jogadores);
		int numeroDeJogadores = jogadores.size();
		int apostaMinimaPorJogador = 0;
		int valorDaAposta = 0;

		/*
		 * primeira rodada, regra especial que não pode jogar zero, então, neste
		 * caso, cada jogador deve jogar ao menos 1
		 */
		if (historicoDeRodadas.estaVazio()) {
			apostaMinimaPorJogador = 1;
		}

		/*
		 * utiliza o valor de palitos jogados pelo jogador para iniciar o valor
		 * total das apostas
		 */
		valorDaAposta += palitosJogados;

		/*
		 * analisa as apostas já feitas para tentar inferir valores mais
		 * concretos para os jogadores que já apostaram
		 */
		valorDaAposta += valorTotalEstimadoComApostasDaRodada(apostasDaRodada, apostaMinimaPorJogador, palitosDoJogador,
				totalDePalitosNoJogo, numeroDeJogadores);

		/*
		 * para cada jogador (que já não fez uma aposta), faz um chute do valor
		 * que o mesmo jogou, e soma com o valor dos demais jogadores para obter
		 * o valor total da aposta
		 */
		for (Jogador jogador : jogadores) {
			/*
			 * verifica se o jogador não está na lista de apostas já feitas, se
			 * está, vai para o jogador seguinte, pois a jogada do atual já foi
			 * contabilizada
			 */
			if (jogadorJaApostou(jogador, apostasDaRodada)) {
				continue;
			}

			valorDaAposta += apostaMinimaPorJogador
					+ (int) (Math.random() * ((jogador.getPalitos() - apostaMinimaPorJogador) + 1));
		}

		// verifica se a aposta já foi feita por outro jogador
		if (apostaJafeita(valorDaAposta, apostasDaRodada)) {
			// ajusta o a aposta para um valor válido
			valorDaAposta = ajustaAposta(valorDaAposta, totalDePalitosNoJogo, apostasDaRodada);
		}

		// valorDaAposta = apostaMinima + (int) (Math.random() *
		// ((totalDePalitosNoJogo - apostaMinima) + 1));

		// // verifica se a aposta foi feita com sucesso
		// if (!apostaJafeita(valorDaAposta, apostasDaRodada)) {
		// apostaConcretizada = true;
		// }

		return valorDaAposta;
	}

	private int ajustaAposta(int valorDaAposta, int totalDePalitosNoJogo, List<Aposta> apostasDaRodada) {
		boolean apostaConcretizada = false;
		int novaAposta = valorDaAposta;

		/*
		 * tenta ajustar o valor da aposta para o próximo valor válido maior que
		 * o apostado
		 */
		while (!apostaConcretizada) {
			if ((novaAposta + 1) <= totalDePalitosNoJogo) {
				novaAposta++;
				if (!apostaJafeita(novaAposta, apostasDaRodada)) {
					apostaConcretizada = true;
				}
			} else {
				// não da pra aumentar mais, sai do loop
				break;
			}
		}

		/*
		 * caso a aposta não tenha sido concretizada tenta ajustar o valor da
		 * aposta para o próximo valor válido menor que o apostado
		 */
		if (!apostaConcretizada) {
			// reseta o valor para o apostado originalmente
			novaAposta = valorDaAposta;
			while (!apostaConcretizada) {
				novaAposta--;
				if (!apostaJafeita(novaAposta, apostasDaRodada)) {
					apostaConcretizada = true;
				}
			}
		}

		return novaAposta;
	}

	private boolean jogadorJaApostou(Jogador jogador, List<Aposta> apostasDaRodada) {
		boolean jogadorJaApostou = false;

		for (Aposta aposta : apostasDaRodada) {
			if (aposta.getJogador().equals(jogador)) {
				jogadorJaApostou = true;
				break;
			}
		}

		return jogadorJaApostou;
	}

	private int valorTotalEstimadoComApostasDaRodada(List<Aposta> apostasDaRodada, int apostaMinimaPorJogador,
			int palitosDoJogador, int totalDePalitosNoJogo, int numeroDeJogadores) {
		// se não tem apostas já feitas na rodada, então returna zero
		if (apostasDaRodada.isEmpty()) {
			return 0;
		}

		int apostaTotal = 0;
		int totalPalitosDosOponentes = totalDePalitosNoJogo - palitosDoJogador;

		for (Aposta aposta : apostasDaRodada) {
			int totalPalitosOponentesRestantes = totalPalitosDosOponentes - aposta.getJogador().getPalitos();
			int apostaOponenteAtual = aposta.getNumeroDePalitosApostados();

			if (apostaMinimaPorJogador == 0) {
				/*
				 * neste caso, para a aposta do oponente fazer sentido, ele
				 * necessariamente apostou que todos os outros oponentes jogaram
				 * 0, se ainda assim a aposta dele for 0, então isso significa
				 * que ele jogou zero, e neste caso, não somamos nada a nossa
				 * estimativa para a aposta
				 */
				if (apostaOponenteAtual == 0) {
					continue; // vai para o próximo oponente
				}
			} else {
				/*
				 * neste caso, se a aposta do oponente é igual a aposta mínima
				 * te todos jogadores somados, significa que ele apostou que
				 * todos os jogadores apostaram a quantidade mínima permitida,
				 * incluíndo ele próprio
				 */
				if (apostaOponenteAtual == (apostaMinimaPorJogador * numeroDeJogadores)) {
					apostaTotal += apostaMinimaPorJogador;
					continue; // vai para o próximo oponente
				}
			}

			/*
			 * neste caso, para a aposta do oponente fazer sentido, ele
			 * necessariamente apostou que todos os outros oponentes jogaram
			 * todos os palitos que tinham, neste caso, se por exemplo, tiver 3
			 * oponentes, dois deles jogando 3, e a aposta do oponente atual for
			 * 8, então ele deve ter jogado 2.
			 */
			if (apostaOponenteAtual > totalPalitosOponentesRestantes + palitosDoJogador) {
				int estimativaPalitosDoOponente = apostaOponenteAtual - (totalPalitosOponentesRestantes + palitosDoJogador);
				apostaTotal += estimativaPalitosDoOponente;
				continue; // vai para o próximo oponente
			}

			/*
			 * se nenhum dos dois casos extremos acima acontecerem, então chuta
			 * um valor aleatório para a jogada do jogador atual
			 */
			apostaTotal += apostaMinimaPorJogador
					+ (int) (Math.random() * ((aposta.getJogador().getPalitos() - apostaMinimaPorJogador) + 1));
		}

		return apostaTotal;
	}

	private boolean apostaJafeita(int aposta, List<Aposta> apostasDaRodada) {
		// se não tem apostas já feitas na rodada, então returna false
		if (apostasDaRodada.isEmpty()) {
			return false;
		}

		boolean apostaFeita = false;

		for (Aposta apostaAtual : apostasDaRodada) {
			if (aposta == apostaAtual.getNumeroDePalitosApostados()) {
				apostaFeita = true;
				break;
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
