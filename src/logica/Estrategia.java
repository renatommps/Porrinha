package logica;

import java.util.List;

public interface Estrategia {

	int definirJogada(HistoricoDeRodadas historicoDeRodadas, int palitosDoJogador, List<Jogador> jogadores);

	int definirApostaDeResultado(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores, int palitosJogados,
			int palitosDoJogador);

}
