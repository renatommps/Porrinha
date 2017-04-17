package visao;

import java.util.List;

import logica.HistoricoDeRodadas;
import logica.Jogador;

public interface Interface {

	public void exibeMenuInicial();

	public String exibeMenuDeEntradaDeDadosJogadorHumano(int indiceJogador);

	public int exibeMenuDeDefinicaoDeJogadaJogadorHumano(Jogador jogador, List<Jogador> jogadores,
			HistoricoDeRodadas historicoDeRodadas);

	public int exibeMenuDeDefinicaoDeApostaJogadorHumano(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores,
			Jogador jogador);

	public void exibeTelaDeResultadoDoJogo(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores);

	public boolean exibeMenuDefinicaoDeReinicioDeJogo();

	public void exibeTelaDeSaidaDoJogo();

}