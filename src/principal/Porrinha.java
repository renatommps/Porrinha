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

	public static void main(String[] args) {
		ControladorDeJogo controlador = new ControladorDeJogo();
		controlador.iniciaJogo();
	}
}
