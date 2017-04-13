package logica;

import controle.ControladorDeJogo;

public class StateRoundSetMove implements State {

	@Override
	public void process(StateContext context, ControladorDeJogo controladorDeJogo) {
		
		Rodada rodada = new Rodada();
		 
		for (Jogador jogador : controladorDeJogo.getJogadores()) {
			if(jogador instanceof JogadorIA){
				jogador.decidirJogada();
			} else {
				//controladorDeJogo.chamaMenuDefinicaoJogadaJogadorHumano();
			}
		}
		
		controladorDeJogo.chamaMenuDeDefinicaoDeJogadas();
		context.setState(new StateRoundSetBet());
	}

}
