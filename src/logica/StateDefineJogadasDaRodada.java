package logica;

import java.util.List;

import controle.ControladorDeJogo;

public class StateDefineJogadasDaRodada implements State {

	@Override
	public void process(StateContext context, ControladorDeJogo controladorDeJogo) {

		Rodada rodada = new Rodada();
		HistoricoDeRodadas historicoDeRodadas = controladorDeJogo.getHistoricoDeRodadas();
		List<Jogador> jogadores = controladorDeJogo.getJogadores();

		for (Jogador jogador : jogadores) {
			if (jogador instanceof JogadorIA) {
				Jogada jogada = jogador.decidirJogada(historicoDeRodadas, jogadores);
				rodada.addJogada(jogada);
			} else {
				int palitosJogados = controladorDeJogo.chamaMenuDefinicaoJogadaJogadorHumano(jogador, jogadores,
						historicoDeRodadas);
				Jogada jogada = new Jogada(jogador, palitosJogados);
				rodada.addJogada(jogada);
			}
		}

		historicoDeRodadas.addRodada(rodada);
		context.setState(new StateDefineApostasDaRodada());
	}

}
