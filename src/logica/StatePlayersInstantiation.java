package logica;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import controle.ControladorDeJogo;

public class StatePlayersInstantiation implements State {

	@Override
	public void process(StateContext context, ControladorDeJogo controladorDeJogo) {
		FabricaDeJogador fabricaDeJogador = new FabricaDeJogador();

		for (int i = 0; i < controladorDeJogo.getNumeroDeJogadoresIA(); i++) {
			Jogador novoJogadorIA = fabricaDeJogador.getJogadorIA(Integer.toString(i), "minmax");
			controladorDeJogo.addJogador(novoJogadorIA);
		}

		for (int i = 0; i < controladorDeJogo.getNumeroDeJogadoresHumanos(); i++) {
			String nomeJogadorHumano = controladorDeJogo.chamaMenuDeEntradaDeDadosJogadorHumano(i + 1);
			Jogador novoJogadorHumano = fabricaDeJogador.getJogadorHumano(nomeJogadorHumano);
			controladorDeJogo.addJogador(novoJogadorHumano);
		}

		// embaralha a ordem inicial dos jogadores
		List<Jogador> jogadores = controladorDeJogo.getJogadores();
		long semente = System.nanoTime();
		Collections.shuffle(jogadores, new Random(semente));

		// define pr��ximo estado
		context.setState(new StateDefineJogadasDaRodada());
	}

}
