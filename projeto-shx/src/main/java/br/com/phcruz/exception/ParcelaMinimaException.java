package br.com.phcruz.exception;

/**
 * Classe responsável pela exceção quando o usuário tenta cadastrar um titulo
 * com o valor abaixo da parcela minima
 * 
 * @author paulo
 *
 */
public class ParcelaMinimaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParcelaMinimaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParcelaMinimaException(String message) {
		super(message);
	}
}
