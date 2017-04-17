package logica;

import java.util.ArrayList;

public class EuristicaCombinada extends Euristica {
	
	public EuristicaCombinada(Euristica lhs, Euristica rhs) {
		this.esquerda = lhs;
		this.direita = rhs;
	}

	@Override
	public ArrayList<Double> accept(EstadoPorrinha estado) {
		ArrayList<Double> heuristicas = new ArrayList<>();
		ArrayList<Double> heuristicaEsquerda = esquerda.accept(estado);
		ArrayList<Double> heuristicaDireita = direita.accept(estado);
		for(int i = 0; i < heuristicaEsquerda.size(); ++i) {
			double combinada = heuristicaEsquerda.get(i) + heuristicaDireita.get(i);
			combinada /= 2;
			heuristicas.add(combinada);
		}
		return heuristicas;
	}
	
	private Euristica esquerda;
	
	private Euristica direita;
	

}
