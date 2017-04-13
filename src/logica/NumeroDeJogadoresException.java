package logica;

public class NumeroDeJogadoresException extends Exception {
	
	public NumeroDeJogadoresException(int num) {
		this.num = num;
	}
	
	private int num;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
