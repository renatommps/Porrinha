package logica;

import java.util.ArrayList;
import java.util.Random;

public class EuristicaApostaDosOutros extends Euristica {

	@Override
	public double accept(EstadoPorrinha estado) {
		int palitosEmJogo = 0;
		for (ArrayList<Integer> juninhoPlayer : estado.getMaoAposta()) {
			palitosEmJogo += juninhoPlayer.get(0);
		}
		int jogados = estado.jogadosPeloAtual();
		int totalMaoOutros = palitosEmJogo - estado.getMaoAposta().get(estado.getJogadorAtual()).get(0);
		for (int i = 0; i < estado.getJogadorAtual(); ++i) {
			
			ArrayList<Integer> juninhoPlayer = estado.getMaoAposta().get(i);
			int apostados = juninhoPlayer.get(1);
			if(apostados == 0) {
				continue;
			}
			int diff = apostados - totalMaoOutros;
			if(diff > 0) {
				jogados += diff;
			}
			else {
				int naMao = juninhoPlayer.get(0);
				Random rand = new Random();
				int value = rand.nextInt(naMao + 1);
				jogados += value;
			}
		}
		for(int i = estado.getJogadorAtual() + 1; i < estado.getMaoAposta().size(); ++i) {
			ArrayList<Integer> juninhoPlayer = estado.getMaoAposta().get(i);
			int naMao = juninhoPlayer.get(0);
			Random rand = new Random();
			int value = rand.nextInt(naMao + 1);
			jogados += value;
		}
		int apostaJogadorAtual = estado.getMaoAposta().get(estado.getJogadorAtual()).get(1);
		int diff = Math.abs(apostaJogadorAtual - jogados);
		int maxDiff = Math.max(jogados, palitosEmJogo - jogados);
		double ret = 1 - ((double)diff/maxDiff);
		return ret;
	}

}
