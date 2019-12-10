package br.com.phcruz.exception;

/**
 * Classe responsável pela exceção quando o usuário tenta salvar um registro com
 * uma data inválida
 * 
 * @author paulo
 *
 */
public class DataInvalidaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataInvalidaException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataInvalidaException(String message) {
		super(message);
	}

}
