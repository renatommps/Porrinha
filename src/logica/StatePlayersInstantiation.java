package logica;

import controle.ControladorDeJogo;

public class StatePlayersInstantiation implements State {

	@Override
	public void process(StateContext context, ControladorDeJogo controladorDeJogo) {
		controladorDeJogo.chamaMenuDeInstanciacaoDeJogadores();
		context.setState(new StatePlayersInstantiation());
	}

}
