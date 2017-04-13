package logica;

import java.util.ArrayList;

public class ArvoreMiniMax {
	
	public ArvoreMiniMax(EstadoArvore inicial, Euristica euristica, int profundidade) {
		profundidadeMax = profundidade;
		this.euristica = euristica;
		raiz = new Nodo(inicial, 0);
	}
	
	public EstadoArvore getJogada() {
		ArrayList<Nodo> criaNodos = new ArrayList<Nodo>();
		ArrayList<Nodo> criados = new ArrayList<Nodo>();
		criaNodos.add(raiz);
		while(!criaNodos.isEmpty()) {
			Nodo last = criaNodos.remove(criaNodos.size()-1);
			criados.add(last);
			if(last.criaFilhos()) {
				criaNodos.addAll(last.getFilhos());
			}
		}
		//TODO
		return null;
	}
	
	private class Nodo {
		private Nodo(EstadoArvore estado, int profundidade) {
			this.estado = estado;
			this.profundidade = profundidade;
			this.estadoFinal = estado.isFinal();
			System.out.println(profundidade + " " + profundidadeMax);
			this.folha = (profundidade == profundidadeMax) || estadoFinal;
			this.filhos = new ArrayList<Nodo>();
		}
		
		private boolean criaFilhos() {
			if(folha) {
				System.out.println("Folha");
				this.vetorGanhos = euristica.eval(estado);
				return false;
			}
			for(EstadoArvore novoEstado : estado.transicoes()) {
				System.out.println("Transicao");
				Nodo filho = new Nodo(novoEstado, profundidade + 1);
				filhos.add(filho);
			}
			return true;
		}
		
		private ArrayList<Double> getVetorGanhos() {
			return vetorGanhos;
		}
		
		private void setVetorGanhos(ArrayList<Double> vetor) {
			vetorGanhos = vetor;
		}
		
		private EstadoArvore getEstado() {
			return estado;
		}
		
		private ArrayList<Nodo> getFilhos() {
			return filhos;
		}
		
		private ArrayList<Nodo> filhos;
		
		private ArrayList<Double> vetorGanhos;
		
		private EstadoArvore estado;
		
		private boolean folha;
		
		private int profundidade;
		
		private boolean estadoFinal;
		
	}
	
	private int profundidadeMax;
	
	private Nodo raiz;
	
	private Euristica euristica;
}
