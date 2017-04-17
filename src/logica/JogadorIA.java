package logica;

import java.util.List;

public class JogadorIA extends Jogador {

	Euristica euristica;

	public JogadorIA(String nome, Euristica euristica) {
		super(nome);
		this.euristica = euristica;
	}

	@Override
	public Jogada decidirJogada(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores) {
		Jogada jogada = new Jogada(this);
		int palistosJogados = euristica.definirJogada(historicoDeRodadas, palitos, jogadores);

		jogada.setPalistosJogados(palistosJogados);

		return jogada;
	}

	@Override
	public Aposta decidirApostaDeResultado(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores) {
		Aposta aposta = new Aposta(this);
		int totalDePalitosApostados = euristica.definirApostaDeResultado(historicoDeRodadas, jogadores);

		aposta.setNumeroDePalitosApostados(totalDePalitosApostados);

		return aposta;
	}
}
