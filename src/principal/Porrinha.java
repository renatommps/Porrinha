package principal;

import java.util.ArrayList;

import controle.ControladorDeJogo;
import logica.ArvoreMiniMax;
import logica.EstadoArvore;
//import visao.ConsoleInterface;
//import visao.Interface;
import logica.EstadoPorrinha;
import logica.EuristicaAleatoria;
import logica.EuristicaPossibilidade;
import logica.NumeroDeJogadoresException;

public class Porrinha {

	public static void main(String[] args) throws NumeroDeJogadoresException {
		ArrayList<ArrayList<Integer>> inicio = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 3; ++i) {
			ArrayList<Integer> toAdd = new ArrayList<Integer>();
			toAdd.add(3);
			toAdd.add(-1);
			inicio.add(toAdd);
		}
		ArvoreMiniMax teste = new ArvoreMiniMax(new EstadoPorrinha(inicio, 2, 0, 3), new EuristicaPossibilidade(), 2, 3);
		EstadoArvore result = teste.getJogada();
		//ControladorDeJogo controlador = new ControladorDeJogo();
		//controlador.iniciaJogo();
		
//		Interface userInterface = new ConsoleInterface();
//		userInterface.exibeMenuInicial();
	}

}
