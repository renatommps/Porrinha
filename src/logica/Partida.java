package logica;

public class Partida {

	private Jogador vencedor;
	private Estrategia estrategiaDoVencedor;

	public Partida() {
		// TODO Auto-generated constructor stub
	}

	public Partida(Jogador vencedor, Estrategia estrategiaDoVencedor) {
		this.vencedor = vencedor;
		this.estrategiaDoVencedor = estrategiaDoVencedor;
	}

	public Jogador getVencedor() {
		return vencedor;
	}

	public void setVencedor(Jogador vencedor) {
		this.vencedor = vencedor;
	}

	public Estrategia getEstrategiaDoVencedor() {
		return estrategiaDoVencedor;
	}

	public void setEstrategiaDoVencedor(Estrategia estrategiaDoVencedor) {
		this.estrategiaDoVencedor = estrategiaDoVencedor;
	}

}
