package logica;

import controle.ControladorDeJogo;

public class StateExit implements State {

	@Override
	public void process(StateContext context, ControladorDeJogo controladorDeJogo) {
		// mostra resultado do jogo
		controladorDeJogo.chamaTelaDeSaidaDoJogo();
	}
}
