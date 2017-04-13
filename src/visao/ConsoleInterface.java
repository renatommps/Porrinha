package visao;

import java.util.Scanner;

import controle.ControladorDeJogo;
import logica.EuristicaAleatoria;
import logica.JogadorIA;

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

	// public void iniciaJogo() {
	//
	// while (true) {
	// exibeMenuInicial();
	// InstanciaJogadoreIA();
	// InstanciaJogadoresHumanos();
	// }
	//
	// // scanner.close();
	// }

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
	public void exibeMenuDeInstanciacaoDeJogadores() {
		InstanciaJogadoreIA();
		InstanciaJogadoresHumanos();
	}

	@Override
	public void exibeMenuPreparaRodada() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exibeRodada() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exibeResultadoDaRodada() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exibeResultadoDoJogo() {
		// TODO Auto-generated method stub

	}

	private void InstanciaJogadoreIA() {
		FabricaDeJogador fabricaDeJogador = new FabricaDeJogador();

		// peça para informar o tipo de IA para cada jogador adicionado, depois mostre na tela o jogador adicionado
		for (int i = 0; i < controlador.getNumeroDeJogadoresIA(); i++) {
			controlador.addJogador(fabricaDeJogador.getJogadorIA(Integer.toString(i), "aleatorio"));
		}
	}

	private void InstanciaJogadoresHumanos() {
		for (int i = 0; i < controlador.getNumeroDeJogadoresHumanos(); i++) {
			// controlador.addJogador(fabricaDeJogador.getJogador("aleatorio"));
			//
			// controlador.addJogador(new JogadorIA(Integer.toString(i), new
			// EuristicaAleatoria()));
		}

	}
}
