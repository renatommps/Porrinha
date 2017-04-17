package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArvoreMiniMax {

	public ArvoreMiniMax(EstadoArvore inicial, Euristica euristica, int profundidade, int numJogadores)
			throws NumeroDeJogadoresException {
		if (numJogadores < 2) {
			throw new NumeroDeJogadoresException(numJogadores);
		}
		this.profundidadeMax = profundidade;
		this.euristica = euristica;
		this.numJogadores = numJogadores;
		this.raiz = new Nodo(inicial, 0);
	}

	public EstadoArvore getJogada() {
		raiz.criaFilhos();
		return raiz.best.estado;
	}

	private class Nodo {
		private Nodo(EstadoArvore estado, int profundidade) {
			this.estado = estado;
			this.profundidade = profundidade;
			this.estadoFinal = estado.isFinal();
			this.folha = (profundidade == profundidadeMax) || estadoFinal;
			this.filhos = new ArrayList<Nodo>();
		}

		private class NodoCompare implements Comparator<Nodo> {

			private NodoCompare(int index) {
				this.index = index;
			}

			@Override
			public int compare(Nodo lhs, Nodo rhs) {
				double valLeft = lhs.vetorGanhos.get(index).doubleValue();
				double valRight = rhs.vetorGanhos.get(index).doubleValue();
				if(valLeft < valRight) {
					return -1;
				}
				else if(valLeft == valRight) {
					return 0;
				}
				else {
					return 1;
				}
			}

			private int index;

		}

		private boolean criaFilhos() {
			if (folha) {
				this.vetorGanhos = euristica.eval(estado);
				return false;
			}
			for (EstadoArvore novoEstado : estado.transicoes()) {
				Nodo filho = new Nodo(novoEstado, profundidade + 1);
				filho.criaFilhos();
				/////////////
				EstadoPorrinha teste = (EstadoPorrinha) filho.estado;
				//System.out.println(teste.getMaoAposta());
				/////////////
				filhos.add(filho);
			}
			int index = profundidade % numJogadores;
			NodoCompare compare = new NodoCompare(index);
			Nodo max = Collections.max(filhos, compare);
			vetorGanhos = max.getVetorGanhos();
			best = max;
			return true;
		}

		private ArrayList<Double> getVetorGanhos() {
			return vetorGanhos;
		}

		private ArrayList<Nodo> filhos;
		
		private Nodo best;

		private ArrayList<Double> vetorGanhos;

		private EstadoArvore estado;

		private boolean folha;

		private int profundidade;

		private boolean estadoFinal;

	}

	private int profundidadeMax;

	private Nodo raiz;

	private int numJogadores;

	private Euristica euristica;
}
