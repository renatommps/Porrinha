package logica;

public class Aposta {

	private Jogador jogador;
	private int numeroDePalitosApostados;

	public Aposta() {
		// TODO Auto-generated constructor stub
	}

	public Aposta(Jogador jogador) {
		this.jogador = jogador;
	}

	public Aposta(Jogador jogador, int numeroDePalitosApostados) {
		this.jogador = jogador;
		this.numeroDePalitosApostados = numeroDePalitosApostados;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public int getNumeroDePalitosApostados() {
		return numeroDePalitosApostados;
	}

	public void setNumeroDePalitosApostados(int numeroDePalitosApostados) {
		this.numeroDePalitosApostados = numeroDePalitosApostados;
	}

}
