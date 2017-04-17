package logica;

import java.util.ArrayList;
import java.util.List;

public class Rodada {

	private Jogador vencedor;
	private int numeroDePalitosJogados;
	private List<Jogada> jogadas;
	private List<Aposta> apostas;

	public Rodada() {
		jogadas = new ArrayList<Jogada>();
		apostas = new ArrayList<Aposta>();
		numeroDePalitosJogados = 0;
		vencedor = null;
	}

	public Jogador getVencedor() {
		return vencedor;
	}

	public void setVencedor(Jogador vencedor) {
		this.vencedor = vencedor;
	}

	public int getNumeroDePalitosJogados() {
		return numeroDePalitosJogados;
	}

	public void setNumeroDePalitosJogados(int numeroDePalitosJogados) {
		this.numeroDePalitosJogados = numeroDePalitosJogados;
	}

	public List<Jogada> getJogadas() {
		return jogadas;
	}

	public void addJogada(Jogada jogada) {
		jogadas.add(jogada);
	}

	public List<Aposta> getApostas() {
		return apostas;
	}

	public void addAposta(Aposta aposta) {
		apostas.add(aposta);
	}
}
