package visao;

import controle.ControladorDeJogo;

public abstract class UserInterface implements Interface {

	protected ControladorDeJogo controlador;

	public UserInterface(ControladorDeJogo controladorDeJogo) {
		controlador = controladorDeJogo;
	}

}
