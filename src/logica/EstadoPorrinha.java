package logica;

import java.util.ArrayList;

public class EstadoPorrinha implements EstadoArvore {

	@Override
	public ArrayList<EstadoArvore> transicoes() {
		//TODO
		ArrayList<EstadoArvore> ret = new ArrayList<EstadoArvore>();
		for(int i = 0; i < 4; ++i) {
			ret.add(new EstadoPorrinha());
		}
		return ret;
	}

	@Override
	public boolean isFinal() {
		//TODO
		return false;
	}

}
