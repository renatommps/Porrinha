package logica;

import java.util.ArrayList;

public abstract class Euristica {
	public ArrayList<Double> eval(EstadoArvore estado) {
		return estado.visit(this);
	}
	
	public abstract ArrayList<Double> accept(EstadoPorrinha estado);
}
