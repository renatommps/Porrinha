package logica;

import java.util.List;

public class EstrategiaAleatoria implements Estrategia {

	public EstrategiaAleatoria() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int definirJogada(HistoricoDeRodadas historicoDeRodadas, int palitosDoJogador, List<Jogador> jogadores) {
		/*
		 * OBS: O hist��rico n��o est�� sendo usado, deixei ai caso alguma
		 * euristica v�� usar...
		 */

		int jogadaMinima = 0;

		// primeira rodada, regra especial que n��o pode jogar zero
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
		 * OBS: O hist��rico n��o est�� sendo usado, deixei ai caso algumab
		 * euristica v�� usar...
		 */

		List<Aposta> apostasDaRodada = historicoDeRodadas.getUltimaRodada().getApostas();
		int totalDePalitosNoJogo = getTotalDePalitosNoJogo(jogadores);
		int numeroDeJogadores = jogadores.size();
		int apostaMinimaPorJogador = 0;
		int valorDaAposta = 0;

		/*
		 * primeira rodada, regra especial que n��o pode jogar zero, ent��o, neste
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
		 * analisa as apostas j�� feitas para tentar inferir valores mais
		 * concretos para os jogadores que j�� apostaram
		 */
		valorDaAposta += valorTotalEstimadoComApostasDaRodada(apostasDaRodada, apostaMinimaPorJogador, palitosDoJogador,
				totalDePalitosNoJogo, numeroDeJogadores);

		int jogadorAtual = historicoDeRodadas.getUltimaRodada().getApostas().size();
		/*
		 * para cada jogador (que j�� n��o fez uma aposta), faz um chute do valor
		 * que o mesmo jogou, e soma com o valor dos demais jogadores para obter
		 * o valor total da aposta
		 */
		for (int index = jogadorAtual + 1; index < jogadores.size(); ++index) {
			Jogador jogador = jogadores.get(index);
			/*
			 * verifica se o jogador n��o est�� na lista de apostas j�� feitas,
			 * se est��, vai para o jogador seguinte, pois a jogada do atual j��
			 * foi contabilizada
			 */

			valorDaAposta += apostaMinimaPorJogador
					+ (int) (Math.random() * ((jogador.getPalitos() - apostaMinimaPorJogador) + 1));
		}

		// verifica se a aposta j�� foi feita por outro jogador
		if (apostaJafeita(valorDaAposta, apostasDaRodada)) {
			// ajusta o a aposta para um valor v��lido
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
		 * tenta ajustar o valor da aposta para o pr��ximo valor v��lido maior que
		 * o apostado
		 */
		while (!apostaConcretizada) {
			if ((novaAposta + 1) <= totalDePalitosNoJogo) {
				novaAposta++;
				if (!apostaJafeita(novaAposta, apostasDaRodada)) {
					apostaConcretizada = true;
				}
			} else {
				// n��o da pra aumentar mais, sai do loop
				break;
			}
		}

		/*
		 * caso a aposta n��o tenha sido concretizada tenta ajustar o valor da
		 * aposta para o pr��ximo valor v��lido menor que o apostado
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
		// se n��o tem apostas j�� feitas na rodada, ent��o returna zero
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
				 * 0, se ainda assim a aposta dele for 0, ent��o isso significa
				 * que ele jogou zero, e neste caso, n��o somamos nada a nossa
				 * estimativa para a aposta
				 */
				if (apostaOponenteAtual == 0) {
					continue; // vai para o pr��ximo oponente
				}
			} else {
				/*
				 * neste caso, se a aposta do oponente �� igual a aposta m��nima
				 * te todos jogadores somados, significa que ele apostou que
				 * todos os jogadores apostaram a quantidade m��nima permitida,
				 * inclu��ndo ele pr��prio
				 */
				if (apostaOponenteAtual == (apostaMinimaPorJogador * numeroDeJogadores)) {
					apostaTotal += apostaMinimaPorJogador;
					continue; // vai para o pr��ximo oponente
				}
			}

			/*
			 * neste caso, para a aposta do oponente fazer sentido, ele
			 * necessariamente apostou que todos os outros oponentes jogaram
			 * todos os palitos que tinham, neste caso, se por exemplo, tiver 3
			 * oponentes, dois deles jogando 3, e a aposta do oponente atual for
			 * 8, ent��o ele deve ter jogado 2.
			 */
			if (apostaOponenteAtual > totalPalitosOponentesRestantes + palitosDoJogador) {
				int estimativaPalitosDoOponente = apostaOponenteAtual
						- (totalPalitosOponentesRestantes + palitosDoJogador);
				apostaTotal += estimativaPalitosDoOponente;
				continue; // vai para o pr��ximo oponente
			}

			/*
			 * se nenhum dos dois casos extremos acima acontecerem, ent��o chuta
			 * um valor aleat��rio para a jogada do jogador atual
			 */
			apostaTotal += apostaMinimaPorJogador
					+ (int) (Math.random() * ((aposta.getJogador().getPalitos() - apostaMinimaPorJogador) + 1));
		}

		return apostaTotal;
	}

	private boolean apostaJafeita(int aposta, List<Aposta> apostasDaRodada) {
		// se n��o tem apostas j�� feitas na rodada, ent��o returna false
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
