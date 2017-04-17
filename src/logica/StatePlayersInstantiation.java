package logica;

import controle.ControladorDeJogo;

public class StatePlayersInstantiation implements State {

	@Override
	public void process(StateContext context, ControladorDeJogo controladorDeJogo) {
		FabricaDeJogador fabricaDeJogador = new FabricaDeJogador();

		for (int i = 0; i < controladorDeJogo.getNumeroDeJogadoresIA(); i++) {
			Jogador novoJogadorIA = fabricaDeJogador.getJogadorIA(Integer.toString(i), "aleatorio");
			controladorDeJogo.addJogador(novoJogadorIA);
		}

		for (int i = 0; i < controladorDeJogo.getNumeroDeJogadoresHumanos(); i++) {
			String nomeJogadorHumano = controladorDeJogo.chamaMenuDeEntradaDeDadosJogadorHumano(i + 1);
			Jogador novoJogadorHumano = fabricaDeJogador.getJogadorHumano(nomeJogadorHumano);
			controladorDeJogo.addJogador(novoJogadorHumano);
		}

		context.setState(new StateDefineJogadasDaRodada());
	}

}
