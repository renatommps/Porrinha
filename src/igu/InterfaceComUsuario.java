package igu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import logica.Jogador;

public class InterfaceComUsuario {

	private List<Jogador> jogadores;
	private int numeroDeJogadoresIA;
	private int numeroDeJogadoresHumanos;
	private Scanner scanner;
	private boolean exit;

	public InterfaceComUsuario() {
		jogadores = new ArrayList<Jogador>();
		scanner = new Scanner(System.in);
		exit = false;

		iniciaJogo();
	}

	private void iniciaJogo() {

		while (!exit) {
			exibeMenuInicial();
			InstanciaJogadoreIA();
		}

		scanner.close();
	}

	private void exibeMenuInicial() {
		System.out.println("****************************************************");
		System.out.println("**** Bem vindo ao maravilhoso jogo de Porrinha! ****");
		System.out.println("****************************************************");

		System.out.println("Quantos jogadores de IA o jogo irá ter ? (mínimo 2, máximo 5)");
		numeroDeJogadoresIA = scanner.nextInt();

		System.out.println("Quantos jogadores humanos o jogo irá ter ? (mínimo 0, máximo 5)");
		numeroDeJogadoresHumanos = scanner.nextInt();
	}

	private void InstanciaJogadoreIA() {
		for (int i = 0; i < numeroDeJogadoresIA; i++) {
			jogadores.add(new Jogador());
		}
	}
}
