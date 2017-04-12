package principal;

import controle.ControladorDeJogo;
import visao.ConsoleInterface;
import visao.Interface;

public class Porrinha {

	public static void main(String[] args) {
		ControladorDeJogo controlador = new ControladorDeJogo();
		controlador.iniciaJogo();
		
//		Interface userInterface = new ConsoleInterface();
//		userInterface.exibeMenuInicial();
	}

}
