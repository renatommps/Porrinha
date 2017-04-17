package visao;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import controle.ControladorDeJogo;
import logica.Aposta;
import logica.HistoricoDeRodadas;
import logica.Jogador;
import logica.JogadorIA;

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
		int numeroDeJogadoresIA = scanner.nextInt(); // checagem de erros seria
														// bom né :)
		controlador.setNumeroDeJogadoresIA(numeroDeJogadoresIA);

		System.out.println("Quantos jogadores humanos o jogo irá ter ? (mínimo 0, máximo 5)");
		int numeroDeJogadoresHumanos = scanner.nextInt(); // checagem de erros
															// seria bom né :)
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

		return palitosJogados;
	}

	@Override
	public int exibeMenuDeDefinicaoDeApostaJogadorHumano(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores,
			Jogador jogador) {

		int quantidadeDeJogadores = jogadores.size();
		int totalDePalitosNoJogo = getTotalDePalitosNoJogo(jogadores);
		List<Aposta> apostasDaRodada = historicoDeRodadas.getUltimaRodada().getApostas();

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

		// pergunta qual aposta o jogador fará
		System.out.println("Jogador " + jogador.getNome() + ", qual será a sua aposta para a rodada ? ");

		// lê a aposta do jogador
		int palistosApostados = scanner.nextInt();

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
			System.out.println("Parabéns humanos! good monkey ;)");
		}
	}

	@Override
	public boolean exibeMenuDefinicaoDeReinicioDeJogo() {
		boolean reinicioDeJogo = false;
		boolean escolhaDefinida = false;
		do {
			try {
				System.out.print("Deseja recomeçar o jogo? (\"true\", se sim, \"false\", se não");
				reinicioDeJogo = scanner.nextBoolean();
				if (reinicioDeJogo == true || reinicioDeJogo == false) {
					escolhaDefinida = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Opçao inválida! Digite corretamente humano.");
			}
		} while (!escolhaDefinida);

		return reinicioDeJogo;
	}

	@Override
	public void exibeTelaDeSaidaDoJogo() {
		System.out.println("Adeus humanos, foi um prezer o entreter :)");
		System.out.println("Volte sempre ao maravilhoso jogo de Porrinha!");
	}
}
