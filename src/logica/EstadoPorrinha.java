package logica;

import java.util.ArrayList;

public class EstadoPorrinha implements EstadoArvore {

	public EstadoPorrinha(ArrayList<ArrayList<Integer>> maoAposta, int jogados, int jogadorAtual, int maxJogadores) {
		this.maoAposta = maoAposta;
		this.jogados = jogados;
		this.jogadorAtual = jogadorAtual;
		this.maxJogadores = maxJogadores;
	}
	
	@Override
	public ArrayList<EstadoArvore> transicoes() {
		ArrayList<EstadoArvore> ret = new ArrayList<EstadoArvore>();
		int proxJogador = (jogadorAtual + 1) % maxJogadores;
		int sum = 0;
		for (ArrayList<Integer> jogador : maoAposta) {
			sum += jogador.get(0);
		}
		for (int i = 0; i <= sum; ++i) {
			ArrayList<ArrayList<Integer>> novo = new ArrayList<ArrayList<Integer>>();
			boolean add = true;
			for (int j = 0; j < maoAposta.size(); ++j) {
				ArrayList<Integer> valores = new ArrayList<Integer>();
				int toAdd = 0;

				if (j == jogadorAtual) {
					add = unique(j, i);
					toAdd = i;
				} else {
					toAdd = maoAposta.get(j).get(1);
				}

				valores.add(maoAposta.get(j).get(0));
				valores.add(toAdd);
				novo.add(valores);
			}
			if (add) {
				ret.add(new EstadoPorrinha(novo, jogados, proxJogador, maxJogadores));
			}
		}
		return ret;
	}
	
	public int numJogadores() {
		return maxJogadores;
	}

	@Override
	public boolean isFinal() { 
		for(ArrayList<Integer> jogador : maoAposta) {
			if(jogador.get(1) == -1) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public ArrayList<Double> visit(Euristica euristica) {
		return euristica.accept(this);		
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
	
}
