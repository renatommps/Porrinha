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
		if (tipo.equalsIgnoreCase("MINMAX")) {
			return new JogadorIA(nome, new EstrategiaMinMax(new EuristicaCombinada(new EuristicaPossibilidade(), new EuristicaAleatoria(), 6, 4)));
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