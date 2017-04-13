package logica;

public abstract class Jogador {

	private String nome;
	private int palitos;
	
	public Jogador(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	abstract public void decidirJogada();

	abstract public void estimarResultado(Rodada rodada);

	public int getPalitos() {
		return palitos;
	}

	public void decrementPalitos() {
		palitos--;
	}

}
