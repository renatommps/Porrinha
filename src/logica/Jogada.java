package logica;

public class Jogada {

	private Jogador jogador;
	private int palistosJogados;

	public Jogada() {
		// TODO Auto-generated constructor stub
	}

	public Jogada(Jogador jogador) {
		this.jogador = jogador;
	}

	public Jogada(Jogador jogador, int palistosJogados) {
		this.jogador = jogador;
		this.palistosJogados = palistosJogados;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public int getPalistosJogados() {
		return palistosJogados;
	}

	public void setPalistosJogados(int palistosJogados) {
		this.palistosJogados = palistosJogados;
	}

}