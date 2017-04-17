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
				Aposta aposta = jogador.decidirApostaDeResultado(historicoDeRodadas, jogadores);
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

}
