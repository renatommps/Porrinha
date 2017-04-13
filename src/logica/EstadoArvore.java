package logica;

import java.util.ArrayList;

public interface EstadoArvore {
	public ArrayList<EstadoArvore> transicoes();
	
	public boolean isFinal();
	
	public ArrayList<Double> visit(Euristica euristica);
	
	public int getJogadorAtual();
}
