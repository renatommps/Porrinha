package logica;

import controle.ControladorDeJogo;

public class StateStart implements State {

	@Override
	public void process(StateContext context, ControladorDeJogo controladorDeJogo) {
		controladorDeJogo.exibeMenuInicial();
		context.setState(new StatePlayersInstantiation());
	}

}
