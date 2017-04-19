package logica;

import java.util.List;

public abstract class Jogador {

	protected String nome;
	protected int palitos;

	public Jogador(String nome) {
		this.nome = nome;
		palitos = 3;
	}

	public String getNome() {
		return nome;
	}

	public int getPalitos() {
		return palitos;
	}

	public void decrementPalitos() {
		palitos--;
	}

	abstract public Jogada decidirJogada(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores);

	abstract public Aposta decidirApostaDeResultado(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores, int palitosJogados);

}