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

	// vis��o
	Interface userInterface;

	// l��gica
	private HistoricoDeRodadas historicoDeRodadas;
	private List<Jogador> jogadores;
	private int numeroDeJogadoresIA;
	private int numeroDeJogadoresHumanos;

	// l��gica, controle de estados do jogo (inicio, insancia����o de jogadores,
	// partida, etc..)
	final StateContext gameState;

	public ControladorDeJogo() {
		historicoDeRodadas = new HistoricoDeRodadas();

		jogadores = new ArrayList<Jogador>();

		userInterface = new ConsoleInterface(this);

		gameState = new StateContext(this);
	}

	public void iniciaJogo() {
		while (!(gameState.getState() instanceof StateExit)) {
			gameState.process();
		}
		gameState.process(); // processa o StateExit
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

	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
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

	public void chamaMenuInicial() {
		userInterface.exibeMenuInicial();
	}

	public String chamaMenuDeEntradaDeDadosJogadorHumano(int indiceJogador) {
		String nomeJogadorHumano = null;

		nomeJogadorHumano = userInterface.exibeMenuDeEntradaDeDadosJogadorHumano(indiceJogador);

		return nomeJogadorHumano;
	}

	public int chamaMenuDefinicaoJogadaJogadorHumano(Jogador jogador, List<Jogador> jogadores,
			HistoricoDeRodadas historicoDeRodadas) {
		int palitosNaMao = jogador.getPalitos();
		int palitosJogados = 0;
		while ((palitosJogados = userInterface.exibeMenuDeDefinicaoDeJogadaJogadorHumano(jogador, jogadores,
				historicoDeRodadas)) > palitosNaMao) {

		}

		return palitosJogados;
	}

	public int chamaMenuDefinicaoApostaJogadorHumano(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores,
			Jogador jogador) {
		int palistosApostados = userInterface.exibeMenuDeDefinicaoDeApostaJogadorHumano(historicoDeRodadas, jogadores,
				jogador);

		return palistosApostados;
	}

	public void chamaTelaDeResultadoDoJogo(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores) {
		userInterface.exibeTelaDeResultadoDoJogo(historicoDeRodadas, jogadores);
	}

	public boolean chamaMenuDefinicaoDeReinicioDeJogo() {
		boolean reiniciJogo = userInterface.exibeMenuDefinicaoDeReinicioDeJogo();

		return reiniciJogo;
	}

	public void chamaTelaDeSaidaDoJogo() {
		userInterface.exibeTelaDeSaidaDoJogo();
	}

	public void chamaTelaResultadoDaRodada(Jogador vencedor, Rodada rodadaAtual) {
		userInterface.exibeTelaResultadoDaRodada(vencedor, rodadaAtual, jogadores,
				historicoDeRodadas.getRodadas().size());
	}
}
