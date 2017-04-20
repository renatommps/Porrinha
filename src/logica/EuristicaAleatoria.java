package logica;

public class EuristicaAleatoria extends Euristica {

	public EuristicaAleatoria() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double accept(EstadoPorrinha estado) {
		return Math.random();
	}
}
