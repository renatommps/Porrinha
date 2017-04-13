package controle;

import java.util.ArrayList;
import java.util.List;

import logica.StateExit;
import logica.HistoricoDeRodadas;
import logica.Jogador;
import logica.Rodada;
import logica.StateContext;
import visao.ConsoleInterface;
import visao.Interface;

public class ControladorDeJogo {

	// visão
	Interface userInterface;

	// lógica
	private HistoricoDeRodadas historicoDeRodadas;
	private List<Jogador> jogadores;
	private int numeroDeJogadoresIA;
	private int numeroDeJogadoresHumanos;

	// lógica, controle de estados
	final StateContext gameState;

	public ControladorDeJogo() {
		setHistoricoDeRodadas(new HistoricoDeRodadas());

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

	public void chamaMenuDeDefinicaoDeJogadas() {
		userInterface.exibeMenuDeDefinicaoDeJogadas();
	}

	public String chamaMenuDeEntradaDeDadosJogadorHumano(int indiceJogador) {
		String nomeJogadorHumano = null;

		nomeJogadorHumano = userInterface.exibeMenuDeEntradaDeDadosJogadorHumano(indiceJogador);

		return nomeJogadorHumano;
	}

	public HistoricoDeRodadas getHistoricoDeRodadas() {
		return historicoDeRodadas;
	}

	public void setHistoricoDeRodadas(HistoricoDeRodadas historicoDeRodadas) {
		this.historicoDeRodadas = historicoDeRodadas;
	}

	public void addHistoricoDeRodadas(Rodada rodada) {
		historicoDeRodadas.addRodada(rodada);
	}

}
