package logica;

import java.util.List;

public class EuristicaAleatoria implements Euristica {

	public EuristicaAleatoria() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int definirJogada(HistoricoDeRodadas historicoDeRodadas, int palitos, List<Jogador> jogadores) {
		// OBS: O histórico não está sendo usado, deixei ai caso alguma
		// euristica vá usar...

		int jogadaMinima = 0;

		// primeira rodada, regra especial que não pode jogar zero
		if (historicoDeRodadas.estaVazio()) {
			jogadaMinima = 1;
		}

		int palistosJogados = jogadaMinima + (int) (Math.random() * ((palitos - jogadaMinima) + 1));

		return palistosJogados;
	}

	@Override
	public int definirApostaDeResultado(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores) {
		// OBS: O histórico não está sendo usado, deixei ai caso alguma
		// euristica vá usar...

		boolean apostaConcretizada = false;
		List<Aposta> apostasDaRodada = historicoDeRodadas.getUltimaRodada().getApostas();
		int totalDePalitosNoJogo = getTotalDePalitosNoJogo(jogadores);
		int apostaMinima = 0;
		int aposta = 0;

		// primeira rodada, regra especial que não pode jogar zero
		if (historicoDeRodadas.estaVazio()) {
			int numeroDeJogadores = jogadores.size();
			apostaMinima = numeroDeJogadores;
		}

		// faz uma aposta aleatória, excluíndo valores já apostados por outros
		// jogadores
		while (!apostaConcretizada) {
			aposta = apostaMinima + (int) (Math.random() * ((totalDePalitosNoJogo - apostaMinima) + 1));

			if (!apostaJafeita(aposta, apostasDaRodada)) {
				apostaConcretizada = true;
			}
		}

		return aposta;
	}

	private boolean apostaJafeita(int aposta, List<Aposta> apostasDaRodada) {
		boolean apostaFeita = true;

		if (!apostasDaRodada.isEmpty()) {
			for (Aposta apostaAtual : apostasDaRodada) {
				if (aposta == apostaAtual.getNumeroDePalitosApostados()) {
					apostaFeita = false;
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
