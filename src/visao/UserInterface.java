package visao;

import java.util.ArrayList;
import java.util.List;

import controle.ControladorDeJogo;
import logica.Jogador;

public abstract class UserInterface implements Interface {

	protected ControladorDeJogo controlador;
	
	protected List<Jogador> jogadores;
	protected int numeroDeJogadoresIA;
	protected int numeroDeJogadoresHumanos;

	public UserInterface(ControladorDeJogo controladorDeJogo) {
		controlador = controladorDeJogo;
		jogadores = new ArrayList<Jogador>();
	}

}
