package logica;

import java.util.ArrayList;
import java.util.List;

public class HistoricoDeRodadas {

	private List<Rodada> rodadas;

	public HistoricoDeRodadas() {
		setRodadas(new ArrayList<Rodada>());
	}

	public List<Rodada> getRodadas() {
		return rodadas;
	}

	public void setRodadas(List<Rodada> rodadas) {
		this.rodadas = rodadas;
	}

	public void addRodada(Rodada rodada) {
		rodadas.add(rodada);
	}

	public Rodada getUltimaRodada() {
		if (rodadas == null || rodadas.isEmpty()) {
			return null;
		}
		return rodadas.get(rodadas.size() - 1);
	}

	public boolean estaVazio() {
		return rodadas.isEmpty();
	}
}