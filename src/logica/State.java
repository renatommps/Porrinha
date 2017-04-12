package logica;

import controle.ControladorDeJogo;

public interface State {
	void process(StateContext context, ControladorDeJogo controladorDeJogo);
}
