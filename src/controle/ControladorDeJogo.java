package controle;

import java.util.ArrayList;
import java.util.List;

import logica.Jogador;
import logica.StateContext;
import visao.ConsoleInterface;
import visao.Interface;

public class ControladorDeJogo {

	// visão
	Interface userInterface;

	// lógica
	protected List<Jogador> jogadores;
	protected int numeroDeJogadoresIA;
	protected int numeroDeJogadoresHumanos;

	// lógica, controle de estados
	final StateContext gameState;

	public ControladorDeJogo() {
		jogadores = new ArrayList<Jogador>();
		
		userInterface = new ConsoleInterface(this);

		gameState = new StateContext(this);
	}

	public void iniciaJogo() {

		while(!(gameState.getState() instanceof ExitState)){
			gameState.process();
		}
	}

	public void exibeMenuInicial() {
		// TODO Auto-generated method stub
		
	}
}
