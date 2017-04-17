package logica;

import java.util.List;

import controle.ControladorDeJogo;

public class StateEncerraRodada implements State {

	public StateEncerraRodada() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(StateContext context, ControladorDeJogo controladorDeJogo) {

		HistoricoDeRodadas historicoDeRodadas = controladorDeJogo.getHistoricoDeRodadas();
		Rodada rodadaAtual = historicoDeRodadas.getUltimaRodada();
		List<Aposta> apostas = rodadaAtual.getApostas();

		int palitosJogadosNaRodada = getPalitosJogadosNaRodada(rodadaAtual);

		for (Aposta aposta : apostas) {
			if (aposta.getNumeroDePalitosApostados() == palitosJogadosNaRodada) {
				// define o vencedor da rodada
				rodadaAtual.setVencedor(aposta.getJogador());

				// decrementa o número de palitos do vencedor
				aposta.getJogador().decrementPalitos();
				break;
			}
		}

		Jogador vencedor = rodadaAtual.getVencedor(); // vencedor da rodada

		if (vencedor != null) { // se alguém tiver vencido a rodada
			// verifica se o vencedor não tem mais palitos
			if (vencedor.getPalitos() == 0) {
				// termina o jogo
				context.setState(new StateFimDeJogo());
			} else { // vai para a próxima rodada
				context.setState(new StateDefineApostasDaRodada());
			}
		} else { // se ninguém tiver vencido, vai para a próxima rodada
			context.setState(new StateDefineApostasDaRodada());
		}
	}

	private int getPalitosJogadosNaRodada(Rodada rodadaAtual) {
		int palitosJogados = 0;
		List<Jogada> jogadas = rodadaAtual.getJogadas();

		for (Jogada jogada : jogadas) {
			palitosJogados += jogada.getPalistosJogados();
		}

		return palitosJogados;
	}

}
