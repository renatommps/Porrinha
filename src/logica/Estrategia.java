package logica;

import java.util.List;

public interface Estrategia {

	int definirJogada(HistoricoDeRodadas historicoDeRodadas, int palitos, List<Jogador> jogadores);

	int definirApostaDeResultado(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores);

}
