package br.com.phcruz.exception;

/**
 * Classe responsável pela exceção quando o usuário tenta deletar um cliente que
 * possui titulos cadastrados
 * 
 * @author paulo
 *
 */
public class ClienteTituloException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteTituloException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClienteTituloException(String message) {
		super(message);
	}

}
