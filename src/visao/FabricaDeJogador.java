package visao;

import logica.EuristicaAleatoria;
import logica.Jogador;
import logica.JogadorIA;

public class FabricaDeJogador {

	public FabricaDeJogador() {
		// TODO Auto-generated constructor stub
	}

	public Jogador getJogadorIA(String nome, String tipo) {
		if (tipo == null || nome == null) {
			return null;
		}
		if (tipo.equalsIgnoreCase("ALEATORIO")) {
			return new JogadorIA(nome, new EuristicaAleatoria());
		}

		return null;
	}

}
