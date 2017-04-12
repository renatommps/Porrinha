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

//	public void iniciaJogo() {
//
//		while (true) {
//			exibeMenuInicial();
//			InstanciaJogadoreIA();
//			InstanciaJogadoresHumanos();
//		}
//
//		// scanner.close();
//	}

	@Override
	public void exibeMenuInicial() {
		System.out.println("****************************************************");
		System.out.println("**** Bem vindo ao maravilhoso jogo de Porrinha! ****");
		System.out.println("****************************************************");

		System.out.println("Quantos jogadores de IA o jogo irá ter ? (mínimo 2, máximo 5)");
		numeroDeJogadoresIA = scanner.nextInt();

		System.out.println("Quantos jogadores humanos o jogo irá ter ? (mínimo 0, máximo 5)");
		numeroDeJogadoresHumanos = scanner.nextInt();
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
		for (int i = 0; i < numeroDeJogadoresIA; i++) {
			// jogadores.add(new Jogador());
		}
	}

	private void InstanciaJogadoresHumanos() {
		System.out.println("Quantos jogadores de IA o jogo irá ter ? (mínimo 2, máximo 5)");

	}
}
