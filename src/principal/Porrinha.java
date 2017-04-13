package principal;

import controle.ControladorDeJogo;
import logica.ArvoreMiniMax;
import logica.EstadoPorrinha;
import logica.EuristicaAleatoria;
//import visao.ConsoleInterface;
//import visao.Interface;

public class Porrinha {

	public static void main(String[] args) {
		ArvoreMiniMax teste = new ArvoreMiniMax(new EstadoPorrinha(), new EuristicaAleatoria(), 1);
		teste.getJogada();
		//ControladorDeJogo controlador = new ControladorDeJogo();
		//controlador.iniciaJogo();
		
//		Interface userInterface = new ConsoleInterface();
//		userInterface.exibeMenuInicial();
	}

}
