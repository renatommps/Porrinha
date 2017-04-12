package logica;

public abstract class Jogador {

	private String nome;

	public Jogador(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	abstract public void decidirJogada();

	abstract public void estimarResultado(Rodada rodada);

}
