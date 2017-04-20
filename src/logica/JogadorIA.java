package logica;

import java.util.List;

public class JogadorIA extends Jogador {

	private Estrategia estrategia;

	public JogadorIA(String nome, Estrategia euristica) {
		super(nome);
		this.estrategia = euristica;
	}

	@Override
	public Jogada decidirJogada(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores) {
		Jogada jogada = new Jogada(this);
		int palistosJogados = estrategia.definirJogada(historicoDeRodadas, palitos, jogadores);

		jogada.setPalistosJogados(palistosJogados);

		return jogada;
	}

	@Override
	public Aposta decidirApostaDeResultado(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores,
			int palitosJogados) {
		Aposta aposta = new Aposta(this);
		int totalDePalitosApostados = estrategia.definirApostaDeResultado(historicoDeRodadas, jogadores, palitosJogados,
				palitos);

		aposta.setNumeroDePalitosApostados(totalDePalitosApostados);

		return aposta;
	}

	public Estrategia getEstrategia() {
		return estrategia;
	}

	@Override
	public String getNome() {
		return super.getNome() + " " + estrategia.getClass().getSimpleName();
	}
}