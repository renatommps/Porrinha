package logica;

public class EuristicaCombinada extends Euristica {
	

	public EuristicaCombinada(Euristica lhs, Euristica rhs, int pesoEsquerda, int pesoDireta) {
		assert(pesoEsquerda > 0 && pesoDireita > 0);
		this.esquerda = lhs;
		this.pesoEsquerda = pesoEsquerda;
		this.pesoDireita = pesoDireta;
		this.direita = rhs;
	}
	
	public EuristicaCombinada(Euristica lhs, Euristica rhs) {
		this(lhs, rhs, 1, 1);
	}

	@Override
	public double accept(EstadoPorrinha estado) {
		double heuristicaEsquerda = pesoEsquerda * esquerda.accept(estado);
		double heuristicaDireita = pesoDireita * direita.accept(estado);
		double combinada = heuristicaEsquerda + heuristicaDireita;
		combinada /= pesoEsquerda + pesoDireita;
		return combinada;
	}
	
	private Euristica esquerda;
	
	private Euristica direita;
	
	private int pesoDireita;

	private int pesoEsquerda;

}
