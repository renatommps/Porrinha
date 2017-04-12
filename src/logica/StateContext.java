package logica;

import controle.ControladorDeJogo;

public class StateContext {

	private ControladorDeJogo controlador;

	private State myState;

	public StateContext(ControladorDeJogo controladorDeJogo) {
		controlador = controladorDeJogo;
		setState(new StateStart());
	}

	void setState(final State newState) {
		myState = newState;
	}

	public void process() {
		myState.process(this, controlador);
	}

	public State getState() {
		return myState;
	}
}
