package logica;

import java.util.ArrayList;
import java.util.List;

import controle.ControladorDeJogo;

public class StateFimDeJogo implements State {

	public StateFimDeJogo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(StateContext context, ControladorDeJogo controladorDeJogo) {
		boolean exit = false;

		HistoricoDeRodadas historicoDeRodadas = controladorDeJogo.getHistoricoDeRodadas();
		List<Jogador> jogadores = controladorDeJogo.getJogadores();

		// mostra resultado do jogo
		controladorDeJogo.chamaTelaDeResultadoDoJogo(historicoDeRodadas, jogadores);

		// define se deve começar outra partida, ou abandonar a aplicação
		exit = controladorDeJogo.chamaMenuDefinicaoDeReinicioDeJogo();

		if (exit) {
			context.setState(new StateExit());
		} else {
			// reseta as rodadas e lista de jogadores
			controladorDeJogo.setHistoricoDeRodadas(new HistoricoDeRodadas());
			controladorDeJogo.setJogadores(new ArrayList<Jogador>());

			// volta para a tela inicial
			context.setState(new StateStart());
		}
	}

}
