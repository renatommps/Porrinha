package logica;

import java.util.List;

import controle.ControladorDeJogo;

public class StateDefineApostasDaRodada implements State {

	@Override
	public void process(StateContext context, ControladorDeJogo controladorDeJogo) {

		HistoricoDeRodadas historicoDeRodadas = controladorDeJogo.getHistoricoDeRodadas();
		Rodada rodadaAtual = historicoDeRodadas.getUltimaRodada();
		List<Jogador> jogadores = controladorDeJogo.getJogadores();

		for (Jogador jogador : jogadores) {
			if (jogador instanceof JogadorIA) {
				int palitosJogados = getJogada(jogador, rodadaAtual);
				Aposta aposta = jogador.decidirApostaDeResultado(historicoDeRodadas, jogadores, palitosJogados);
				rodadaAtual.addAposta(aposta);
			} else {
				int palitosJogados = controladorDeJogo.chamaMenuDefinicaoApostaJogadorHumano(historicoDeRodadas,
						jogadores, jogador);
				Aposta aposta = new Aposta(jogador, palitosJogados);
				rodadaAtual.addAposta(aposta);
			}
		}

		context.setState(new StateEncerraRodada());
	}

	private int getJogada(Jogador jogador, Rodada rodadaAtual) {
		int palitosJogados = 0;
		List<Jogada> jogadas = rodadaAtual.getJogadas();

		for (Jogada jogada : jogadas) {
			if (jogada.getJogador().equals(jogador)) {
				palitosJogados = jogada.getPalistosJogados();
				break;
			}
		}

		return palitosJogados;
	}

}
