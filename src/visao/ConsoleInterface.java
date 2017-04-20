package visao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import controle.ControladorDeJogo;
import logica.Aposta;
import logica.EstrategiaAleatoria;
import logica.HistoricoDeRodadas;
import logica.Jogada;
import logica.Jogador;
import logica.JogadorHumano;
import logica.JogadorIA;
import logica.Partida;
import logica.Rodada;

public class ConsoleInterface extends UserInterface {

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

		scanner.nextLine(); // lê o enter para não atrapalhar a próxima leitura
		controlador.setNumeroDeJogadoresHumanos(numeroDeJogadoresHumanos);
	}

	@Override
	public String exibeMenuDeEntradaDeDadosJogadorHumano(int indiceJogador) {
		String nomeJogadorHumano = null;
		System.out.println("Informe um nome para o jogador humano " + indiceJogador);
		nomeJogadorHumano = scanner.nextLine();

		return nomeJogadorHumano;
	}

	@Override
	public int exibeMenuDeDefinicaoDeJogadaJogadorHumano(Jogador jogador, List<Jogador> jogadores,
			HistoricoDeRodadas historicoDeRodadas) {

		// exibe o nome dos jogadores participantes e a quantidade de palitos de
		// cada um deles
		System.out.println("Jogadores participantes:");
		for (Jogador jogadorAtual : jogadores) {
			System.out.println(
					"Jogador: " + jogadorAtual.getNome() + ", palitos restantes: " + jogadorAtual.getPalitos());
		}
		System.out.println(); // deixa uma linha em branco

		// pergunta quantos palitos deseja jogar
		System.out.println("Jogador " + jogador.getNome() + ", quantos palitos deseja jogar? ");

		// lê a jogada do jogador
		int palitosJogados = scanner.nextInt();

		scanner.nextLine(); // lê o enter para não atrapalhar a próxima leitura

		return palitosJogados;
	}

	@Override
	public int exibeMenuDeDefinicaoDeApostaJogadorHumano(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores,
			Jogador jogador) {

		int quantidadeDeJogadores = jogadores.size();
		int totalDePalitosNoJogo = getTotalDePalitosNoJogo(jogadores);
		List<Aposta> apostasDaRodada = historicoDeRodadas.getUltimaRodada().getApostas();
		int palistosApostados = 0;

		// exibe a quantidade de jogadores e o total de palitos em jogo
		System.out.println("Quantidade de jogadores em jogo: " + quantidadeDeJogadores);
		System.out.println("Quantidade de palitos em jogo: " + totalDePalitosNoJogo);

		// exibe cada uma das apostas já feitas
		if (!apostasDaRodada.isEmpty()) {
			System.out.print("Apostas já feitas na rodada: ");
			for (Aposta aposta : apostasDaRodada) {
				System.out.print(aposta.getNumeroDePalitosApostados() + " ");
			}
			System.out.println(); // deixa uma linha em branco
		}

		// pergunta qual aposta o jogador fará (até fazer uma aposta válida)
		boolean apostaValida = false;
		while (!apostaValida) {
			System.out.println("Jogador " + jogador.getNome() + ", qual será a sua aposta para a rodada ? ");

			// lê a aposta do jogador
			palistosApostados = scanner.nextInt();

			// verifica se é uma aposta válida
			if (!apostasDaRodada.isEmpty()) {
				for (Aposta aposta : apostasDaRodada) {
					if (aposta.getNumeroDePalitosApostados() == palistosApostados) {
						System.out.println("Aposta \"" + palistosApostados + "\"já feita, faça outra!");
						scanner.nextLine();
					} else {
						apostaValida = true;
					}
				}
			} else {
				apostaValida = true; // se ninguém mais apostou, então não
										// precisa checar
			}
		}

		return palistosApostados;
	}

	private int getTotalDePalitosNoJogo(List<Jogador> jogadores) {
		int totalDePalitos = 0;

		for (Jogador jogador : jogadores) {
			totalDePalitos += jogador.getPalitos();
		}

		return totalDePalitos;
	}

	@Override
	public void exibeTelaDeResultadoDoJogo(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores) {
		Jogador vencedor = historicoDeRodadas.getUltimaRodada().getVencedor();

		System.out.println("Jogo finalizado!");
		System.out.println("Jogadores participantes: ");
		for (Jogador jogador : jogadores) {
			System.out.println("Jogador: " + jogador.getNome() + ", palitos restastes: " + jogador.getPalitos());
		}
		System.out.println("Número de rodadas: " + historicoDeRodadas.getRodadas().size());

		if (vencedor instanceof JogadorIA) {
			System.out.println("Vencedor IA: " + vencedor.getNome());
			System.out.println("Treinem mais humanos ;)");
		} else {
			System.out.println("Vencedor Humano: " + vencedor.getNome());
			System.out.println("Parabéns humano! good monkey ;)");
		}
	}

	@Override
	public boolean exibeMenuDefinicaoDeReinicioDeJogo() {
		boolean reinicioDeJogo = false;
		boolean escolhaDefinida = false;
		do {
			try {
				System.out.println("Deseja recomeçar o jogo? (\"true\", se sim, \"false\", se não)");
				reinicioDeJogo = scanner.nextBoolean();
				escolhaDefinida = true;
			} catch (InputMismatchException e) {
				System.out.println("Opçao inválida! Digite corretamente humano.");
				scanner.nextLine();
			}
		} while (!escolhaDefinida);

		return !reinicioDeJogo;
	}

	@Override
	public void exibeTelaDeSaidaDoJogo() {
		imprimeRankingDeVencedores();
		System.out.println("Adeus humanos, foi um prezer o entreter :)");
		System.out.println("Volte sempre ao maravilhoso jogo de Porrinha!");
	}

	private void imprimeRankingDeVencedores() {

		List<Partida> historicoDePartidas = controlador.getHistoricoDePartidas();
		int vitoriasHumanas = 0;
		int vitoriasIAaleatoria = 0;
		int vitoriasIAminmax = 0;

		for (Partida partida : historicoDePartidas) {
			Jogador vencedor = partida.getVencedor();
			if (vencedor instanceof JogadorHumano) {
				vitoriasHumanas++;
			} else if (JogadorIA.class.cast(vencedor).getEstrategia() instanceof EstrategiaAleatoria) {
				vitoriasIAaleatoria++;
			} else {
				vitoriasIAminmax++;
			}
		}

		System.out.println();
		System.out.println("***** Ranking de vitórias *****");
		System.out.println("Jogadores humanos: " + vitoriasHumanas);
		System.out.println("Jogadores IA aleatoria: " + vitoriasIAaleatoria);
		System.out.println("Jogadores IA mimmax: " + vitoriasIAminmax);
		System.out.println();
	}

	@Override
	public void exibeTelaResultadoDaRodada(Jogador vencedor, Rodada rodadaAtual, List<Jogador> jogadores,
			int numeroDaRodada) {
		List<Jogada> jogadas = rodadaAtual.getJogadas();
		List<Aposta> apostas = rodadaAtual.getApostas();

		System.out.println();
		System.out.println("****** Resultado da rodada " + numeroDaRodada + " ******");

		System.out.println("Jogadores participantes: ");
		for (Jogador jogador : jogadores) {
			System.out.println("Jogador: " + jogador.getNome() + ", palitos restastes: " + jogador.getPalitos());
		}

		System.out.println("Jogadas:");
		for (Jogada jogada : jogadas) {
			System.out
					.println("Jogador: " + jogada.getJogador().getNome() + ", jogada: " + jogada.getPalistosJogados());
		}

		System.out.println("Apostas:");
		for (Aposta aposta : apostas) {
			System.out.println(
					"Jogador: " + aposta.getJogador().getNome() + ", aposta: " + aposta.getNumeroDePalitosApostados());
		}

		if (vencedor != null) {
			System.out.println("Jogador " + vencedor.getNome() + " acertou o resultado!");
		} else {
			System.out.println("Ninguém acertou!");
		}

		System.out.println("***********************************");
	}
}