package logica;

import java.util.ArrayList;

public interface EstadoArvore {
	public ArrayList<EstadoArvore> transicoes();
	
	public boolean isFinal();
	
	public double visit(Euristica euristica);

}