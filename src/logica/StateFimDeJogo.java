package logica;

import java.util.ArrayList;
import java.util.List;

import controle.ControladorDeJogo;

public class StateFimDeJogo implements State {

	public StateFimDeJogo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(StateContext context, ControladorDeJogo controladorDeJogo) {

		boolean exit = false;

		HistoricoDeRodadas historicoDeRodadas = controladorDeJogo.getHistoricoDeRodadas();
		List<Jogador> jogadores = controladorDeJogo.getJogadores();

		// mostra resultado do jogo
		controladorDeJogo.chamaTelaDeResultadoDoJogo(historicoDeRodadas, jogadores);

		// define se deve come��ar outra partida, ou abandonar a aplica����o
		exit = controladorDeJogo.chamaMenuDefinicaoDeReinicioDeJogo();

		// salva a partida atual, com seu vencedor e a estrategia do mesmo
		Jogador vencedor = historicoDeRodadas.getUltimaRodada().getVencedor();
		Estrategia estrategiaDoVencedor = null;
		if (vencedor instanceof JogadorIA){
			estrategiaDoVencedor = JogadorIA.class.cast(vencedor).getEstrategia();
		}
		Partida partidaAtual = new Partida(vencedor, estrategiaDoVencedor);
		controladorDeJogo.addHistoricoDePartidas(partidaAtual);
		
		if (exit) {
			context.setState(new StateExit());
		} else { // reseta as rodadas e lista de jogadores

			controladorDeJogo.setHistoricoDeRodadas(new HistoricoDeRodadas());
			controladorDeJogo.setJogadores(new ArrayList<Jogador>());

			// volta para a tela inicial
			context.setState(new StateStart());
		}
		/*
		HistoricoDeRodadas historicoDeRodadas = controladorDeJogo.getHistoricoDeRodadas();
		
		// salva a partida atual, com seu vencedor e a estrategia do mesmo
		Jogador vencedor = historicoDeRodadas.getUltimaRodada().getVencedor();
		Estrategia estrategiaDoVencedor = null;
		if (vencedor instanceof JogadorIA){
			estrategiaDoVencedor = JogadorIA.class.cast(vencedor).getEstrategia();
		}
		Partida partidaAtual = new Partida(vencedor, estrategiaDoVencedor);
		controladorDeJogo.addHistoricoDePartidas(partidaAtual);

		if (controladorDeJogo.getHistoricoDePartidas().size() > 100) {
			context.setState(new StateExit());
		} else {
			// reseta as rodadas e lista de jogadores
			controladorDeJogo.setHistoricoDeRodadas(new HistoricoDeRodadas());
			controladorDeJogo.setJogadores(new ArrayList<Jogador>());

			// volta para a tela inicial
			context.setState(new StatePlayersInstantiation());
		}
		*/
	}
}
