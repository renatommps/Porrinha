package logica;

import java.util.ArrayList;

public class EstadoPorrinha implements EstadoArvore {

	
	public EstadoPorrinha(ArrayList<ArrayList<Integer>> maoAposta, int jogados, int jogadorAtual, int maxJogadores) {
		this.maoAposta = maoAposta;
		this.jogados = jogados;
		this.jogadorAtual = jogadorAtual;
		this.jogadorArvore = jogadorAtual;
		this.maxJogadores = maxJogadores;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<EstadoArvore> transicoes() {
		ArrayList<EstadoArvore> ret = new ArrayList<EstadoArvore>();
		int proxJogador = (jogadorAtual + 1) % maxJogadores;
		int sum = 0;
		for (ArrayList<Integer> jogador : maoAposta) {
			sum += jogador.get(0);
		}
		for (int i = jogados; i <= sum; ++i) {
			if (unique(jogadorAtual, i)) {
				ArrayList<ArrayList<Integer>> novo = new ArrayList<ArrayList<Integer>>();
				for(ArrayList<Integer> toClone: maoAposta) {
					ArrayList<Integer> juninho = new ArrayList<Integer>();
					juninho.add(toClone.get(0));
					juninho.add(toClone.get(1));
					novo.add(juninho);
				}
				ArrayList<Integer> apostaJogadorAtual = novo.get(jogadorAtual);
				apostaJogadorAtual.set(1, i);
				novo.set(jogadorAtual, apostaJogadorAtual);
				EstadoPorrinha toAdd = new EstadoPorrinha(novo, jogados, proxJogador, maxJogadores);
				toAdd.jogadorArvore = this.jogadorArvore;
				ret.add(toAdd);
			}
		}
		for (int i = 0; i < jogados; ++i) {
			if (unique(jogadorAtual, i)) {
				ArrayList<ArrayList<Integer>> novo = new ArrayList<ArrayList<Integer>>();
				for(ArrayList<Integer> toClone: maoAposta) {
					ArrayList<Integer> juninho = new ArrayList<Integer>();
					juninho.add(toClone.get(0));
					juninho.add(toClone.get(1));
					novo.add(juninho);
				}
				ArrayList<Integer> apostaJogadorAtual = novo.get(jogadorAtual);
				apostaJogadorAtual.set(1, i);
				novo.set(jogadorAtual, apostaJogadorAtual);
				EstadoPorrinha toAdd = new EstadoPorrinha(novo, jogados, proxJogador, maxJogadores);
				toAdd.jogadorArvore = this.jogadorArvore;
				ret.add(toAdd);
			}
		}
		return ret;
	}

	public int numJogadores() {
		return maxJogadores;
	}

	@Override
	public boolean isFinal() {
		for (ArrayList<Integer> jogador : maoAposta) {
			if (jogador.get(1) == -1) {
				return false;
			}
		}
		return true;
	}

	@Override
	public double visit(Euristica euristica) {
		return euristica.accept(this);
	}

	public ArrayList<ArrayList<Integer>> getMaoAposta() {
		return maoAposta;
	}

	public int jogadosPeloAtual() {
		return jogados;
	}

	public int getJogadorAtual() {
		return jogadorArvore;
	}

	private boolean unique(int curIndex, int value) {
		for (int i = 0; i < curIndex; ++i) {
			if (maoAposta.get(i).get(1) == value) {
				return false;
			}
		}
		return true;
	}

	private ArrayList<ArrayList<Integer>> maoAposta;
	private int jogados;
	private int jogadorAtual;
	private int maxJogadores;
	private int jogadorArvore;

}
