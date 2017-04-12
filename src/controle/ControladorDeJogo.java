package controle;

import java.util.ArrayList;
import java.util.List;

import logica.StateExit;
import logica.JogadorIA;
import logica.StateContext;
import visao.ConsoleInterface;
import visao.Interface;

public class ControladorDeJogo {

	// visão
	Interface userInterface;

	// lógica
	protected List<JogadorIA> jogadores;
	protected int numeroDeJogadoresIA;
	protected int numeroDeJogadoresHumanos;

	// lógica, controle de estados
	final StateContext gameState;

	public ControladorDeJogo() {
		jogadores = new ArrayList<JogadorIA>();
		
		userInterface = new ConsoleInterface(this);

		gameState = new StateContext(this);
	}

	public void iniciaJogo() {

		while(!(gameState.getState() instanceof StateExit)){
			gameState.process();
		}
	}

	public void exibeMenuInicial() {
		// TODO Auto-generated method stub
		
	}
}
