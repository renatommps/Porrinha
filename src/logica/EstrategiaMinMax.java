package logica;

import java.util.List;

public class EstrategiaMinMax implements Estrategia {

	// aqui eu tenho a minha arvore
	// aqui eu tenho a minha euristica para a arvore
	// aqui eu tenho quaisquer outras coisas que precisar
	
	public EstrategiaMinMax() {
		super();
		// aqui eu instancio a minha euristica
		// aqui eu instancio a minha arvore minmax passando a euristica criada e mais o que precisar
		// aqui eu faço quaisquer outras coisas que precisar
	}

	// aqui eu implemento quaisquer outros métodos privados que precisar
	
	@Override
	public int definirJogada(HistoricoDeRodadas historicoDeRodadas, int palitos, List<Jogador> jogadores) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int definirApostaDeResultado(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores) {
		// TODO Auto-generated method stub
		return 0;
	}

}
