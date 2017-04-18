package logica;

public class FabricaDeJogador {

	public FabricaDeJogador() {
		// TODO Auto-generated constructor stub
	}

	public Jogador getJogadorIA(String nome, String tipo) {
		if (tipo == null || nome == null) {
			return null;
		}
		if (tipo.equalsIgnoreCase("ALEATORIO")) {
			return new JogadorIA(nome, new EstrategiaAleatoria());
		}

		return null;
	}

	public Jogador getJogadorHumano(String nome) {
		if (nome == null) {
			return null;
		}
		return new JogadorHumano(nome);
	}

}
