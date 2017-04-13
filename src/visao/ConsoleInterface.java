package visao;

import java.util.Scanner;

import controle.ControladorDeJogo;

public class ConsoleInterface extends UserInterface {

	// public enum Estados {
	// INICIA_JOGO, PREPARA_PARTIDA, PREPARA_RODADA, INICIA_RODADA,
	// FINALIZA_PARTIDA, SAIR_JOGO;
	// };

	private Scanner scanner;

	public ConsoleInterface(ControladorDeJogo controladorDeJogo) {
		super(controladorDeJogo);
		scanner = new Scanner(System.in);
	}

	@Override
	public void exibeMenuInicial() {
		System.out.println("****************************************************");
		System.out.println("**** Bem vindo ao maravilhoso jogo de Porrinha! ****");
		System.out.println("****************************************************");

		System.out.println("Quantos jogadores de IA o jogo irá ter ? (mínimo 2, máximo 5)");
		int numeroDeJogadoresIA = scanner.nextInt();
		controlador.setNumeroDeJogadoresIA(numeroDeJogadoresIA);

		System.out.println("Quantos jogadores humanos o jogo irá ter ? (mínimo 0, máximo 5)");
		int numeroDeJogadoresHumanos = scanner.nextInt();
		controlador.setNumeroDeJogadoresHumanos(numeroDeJogadoresHumanos);
	}

	@Override
	public void exibeResultadoDaRodada() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exibeResultadoDoJogo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exibeMenuDeDefinicaoDeJogadas() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exibeRodada() {
		// TODO Auto-generated method stub

	}

	@Override
	public String exibeMenuDeEntradaDeDadosJogadorHumano(int indiceJogador) {
		String nomeJogadorHumano = null;
		System.out.println("Informe um nome para o jogador humano " + indiceJogador);
		nomeJogadorHumano = scanner.nextLine();

		return nomeJogadorHumano;
	}
}
