package logica;

import java.util.ArrayList;

public abstract class Euristica {
	public double eval(EstadoArvore estado) {
		return estado.visit(this);
	}
	
	public abstract double accept(EstadoPorrinha estado);
}
