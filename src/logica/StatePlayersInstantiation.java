package logica;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import controle.ControladorDeJogo;

public class StatePlayersInstantiation implements State {

	@Override
	public void process(StateContext context, ControladorDeJogo controladorDeJogo) {
		FabricaDeJogador fabricaDeJogador = new FabricaDeJogador();

/*		// lista com todos os tipos de estragégias criadas para o jogo
		List<String> estrategias = Arrays.asList("ALEATORIO", "MINMAX");

		// inicializa o index da lista de estrategias
		int estrategiasListIndex = 0;

		// numero total de estragegias criadas
		int numeroDeEstrategias = estrategias.size();

		// numero total de jogadores IA a serem criados
		int numeroDeJogadoresIA = controladorDeJogo.getNumeroDeJogadoresIA();

		
		 * instancia a quantidade de jogadores IA pedidos, variando os tipos de
		 * estragégiasa serem criadas percorrendo a lista com as estrategias
		 * feitas. Se chegar no final da lista, volta para o comeco dela
		 
		for (int i = 0; i < numeroDeJogadoresIA; i++) {
			
		}*/
		
		

		for (int i = 0; i < controladorDeJogo.getNumeroDeJogadoresIA(); i++) {
			Jogador novoJogadorIA = fabricaDeJogador.getJogadorIA(Integer.toString(i), "minmax");
			controladorDeJogo.addJogador(novoJogadorIA);
		}

		for (int i = 0; i < controladorDeJogo.getNumeroDeJogadoresHumanos(); i++) {
			String nomeJogadorHumano = controladorDeJogo.chamaMenuDeEntradaDeDadosJogadorHumano(i + 1);
			Jogador novoJogadorHumano = fabricaDeJogador.getJogadorHumano(nomeJogadorHumano);
			controladorDeJogo.addJogador(novoJogadorHumano);
		}

		// embaralha a ordem inicial dos jogadores
		List<Jogador> jogadores = controladorDeJogo.getJogadores();
		long semente = System.nanoTime();
		Collections.shuffle(jogadores, new Random(semente));

		// define proximo estado
		context.setState(new StateDefineJogadasDaRodada());
	}

}
