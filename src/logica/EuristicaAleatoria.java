package logica;

public class EuristicaAleatoria extends Euristica {

	public EuristicaAleatoria() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double accept(EstadoPorrinha estado) {
		int maxJogadores = estado.getMaoAposta().size() - 1;
		assert(maxJogadores != 0);
		int jogadorAtual = estado.getJogadorAtual();
		int diff = maxJogadores - jogadorAtual;
		double peso = (double) diff/ maxJogadores;
		return Math.random() * peso;
	}
}
