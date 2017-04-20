package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArvoreMiniMax {

	

	public ArvoreMiniMax(EstadoArvore inicial, Euristica euristica, int profundidade, int numJogadores, int jogadorAtual)
			throws NumeroDeJogadoresException {
		if (numJogadores < 2) {
			throw new NumeroDeJogadoresException(numJogadores);
		}
		this.jogadorAtual = jogadorAtual;
		this.profundidadeMax = profundidade;
		this.euristica = euristica;
		this.numJogadores = numJogadores;
		this.raiz = new Nodo(inicial, 0, -10, 10);
	}

	public EstadoArvore getJogada() {
		raiz.criaFilhos();
		//Calcular tamanho árvore. Já já tiro
		System.out.println(raiz.getTamanho());
		return raiz.best.estado;
	}

	private class Nodo {
		private Nodo(EstadoArvore estado, int profundidade, double alfa, double beta) {
			this.estado = estado;
			this.profundidade = profundidade;
			this.estadoFinal = estado.isFinal();
			this.folha = (profundidade == profundidadeMax) || estadoFinal;
			this.filhos = new ArrayList<Nodo>();
			this.alfa = alfa;
			this.beta = beta;
		}
		
		private int getTamanho() {
			int count = 1;
			for(Nodo nodo : filhos) {
				count += nodo.getTamanho();
			}
			return count;
		}

		private boolean criaFilhos() {
			if (folha) {
				this.vetorGanhos = euristica.eval(estado);
				return false;
			}
			Nodo max = null;
			int index = (jogadorAtual + profundidade) % numJogadores;
			this.vetorGanhos = index == jogadorAtual ? -10 : 10;
			for (EstadoArvore novoEstado : estado.transicoes()) {
				Nodo filho = new Nodo(novoEstado, profundidade + 1, alfa, beta);
				filho.criaFilhos();
				if(index == jogadorAtual) {
					if(filho.vetorGanhos > this.vetorGanhos) {
						max = filho;
						this.vetorGanhos = filho.vetorGanhos;
						alfa = Math.max(alfa, this.vetorGanhos);
					}
				}
				else {
					if(filho.vetorGanhos < this.vetorGanhos) {
						max = filho;
						this.vetorGanhos = filho.vetorGanhos;
						beta = Math.min(beta, this.vetorGanhos);
					}
				}
				filhos.add(filho);
				if(beta <= alfa) {
					break;
				}
			}
			best = max;
			return true;
		}

		private ArrayList<Nodo> filhos;
		
		private Nodo best;

		private double vetorGanhos;

		private EstadoArvore estado;

		private boolean folha;

		private int profundidade;

		private boolean estadoFinal;
		
		private double alfa;
		
		private double beta;

	}

	private int jogadorAtual;
	
	private int profundidadeMax;

	private Nodo raiz;

	private int numJogadores;

	private Euristica euristica;
	
}
