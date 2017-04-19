package logica;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaMinMax implements Estrategia {

	// aqui eu tenho a minha arvore
	// aqui eu tenho a minha euristica para a arvore
	// aqui eu tenho quaisquer outras coisas que precisar
	
	public EstrategiaMinMax(Euristica euristica) {
		super();
		this.euristica = euristica;
		// aqui eu instancio a minha euristica
		// aqui eu instancio a minha arvore minmax passando a euristica criada e mais o que precisar
		// aqui eu fa��o quaisquer outras coisas que precisar
	}

	// aqui eu implemento quaisquer outros m��todos privados que precisar
	
	@Override
	public int definirJogada(HistoricoDeRodadas historicoDeRodadas, int palitosDoJogador, List<Jogador> jogadores) {
		/*
		 * OBS: O hist��rico n��o est�� sendo usado, deixei ai caso alguma
		 * euristica v�� usar...
		 */

		int jogadaMinima = 0;

		// primeira rodada, regra especial que n��o pode jogar zero
		if (historicoDeRodadas.estaVazio()) {
			jogadaMinima = 1;
		}

		int palistosJogados = jogadaMinima + (int) (Math.random() * ((palitosDoJogador - jogadaMinima) + 1));

		return palistosJogados;
	}

	@Override
	public int definirApostaDeResultado(HistoricoDeRodadas historicoDeRodadas, List<Jogador> jogadores,
			int palitosJogados, int palitosDoJogador) {
		Rodada atual = historicoDeRodadas.getUltimaRodada();
		ArrayList<ArrayList<Integer>> situacaoAtual = new ArrayList<ArrayList<Integer>>();
		for(Jogador juninhoPlayer : jogadores) {
			ArrayList<Integer> maoApostaJogador = new ArrayList<Integer>();
			maoApostaJogador.add(juninhoPlayer.getPalitos());
			maoApostaJogador.add(-1);
			situacaoAtual.add(maoApostaJogador);
		}
		for(int i = 0; i < atual.getApostas().size(); ++i) {
			Aposta aposta = atual.getApostas().get(i);
			ArrayList<Integer> juninhoPlayer = situacaoAtual.get(i);
			juninhoPlayer.set(1, aposta.getNumeroDePalitosApostados());
			situacaoAtual.set(i, juninhoPlayer);
		}
		EstadoPorrinha estado = new EstadoPorrinha(situacaoAtual, palitosJogados, atual.getApostas().size(), jogadores.size());
		try {
			ArvoreMiniMax arvore = new ArvoreMiniMax(estado, euristica, 8, jogadores.size(), atual.getApostas().size());
			EstadoPorrinha proximo = (EstadoPorrinha)arvore.getJogada();
			ArrayList<ArrayList<Integer>> juninhos = proximo.getMaoAposta();
			
			return juninhos.get(atual.getApostas().size()).get(1);
		} catch (NumeroDeJogadoresException e) {
			e.printStackTrace();
			System.out.println("Se chegar aqui você fez coisa errada ulmanu indiota");
			return -1;
		}
		
	}

	private Euristica euristica;
	
}
