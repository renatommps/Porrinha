package controle;

import java.util.ArrayList;
import java.util.List;

import logica.StateExit;
import logica.Jogador;
import logica.StateContext;
import visao.ConsoleInterface;
import visao.Interface;

public class ControladorDeJogo {

	// visão
	Interface userInterface;

	// lógica
	private List<Jogador> jogadores;
	private int numeroDeJogadoresIA;
	private int numeroDeJogadoresHumanos;

	// lógica, controle de estados
	final StateContext gameState;

	public ControladorDeJogo() {
		jogadores = new ArrayList<Jogador>();

		userInterface = new ConsoleInterface(this);

		gameState = new StateContext(this);
	}

	public void iniciaJogo() {

		while (!(gameState.getState() instanceof StateExit)) {
			gameState.process();
		}
	}

	public int getNumeroDeJogadoresIA() {
		return numeroDeJogadoresIA;
	}

	public void setNumeroDeJogadoresIA(int numeroDeJogadoresIA) {
		this.numeroDeJogadoresIA = numeroDeJogadoresIA;
	}

	public int getNumeroDeJogadoresHumanos() {
		return numeroDeJogadoresHumanos;
	}

	public void setNumeroDeJogadoresHumanos(int numeroDeJogadoresHumanos) {
		this.numeroDeJogadoresHumanos = numeroDeJogadoresHumanos;
	}

	public void addJogador(Jogador jogador) {
		jogadores.add(jogador);
	}

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public void chamaMenuInicial() {
		userInterface.exibeMenuInicial();
	}

	public void chamaMenuDeInstanciacaoDeJogadores() {
		userInterface.exibeMenuDeInstanciacaoDeJogadores();
	}

}
