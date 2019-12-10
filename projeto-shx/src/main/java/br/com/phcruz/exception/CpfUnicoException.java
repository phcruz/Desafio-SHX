package br.com.phcruz.exception;

/**
 * Classe responsável pela exceção quando o usuário tenta cadastrar um novo
 * cliente com o mesmo CPF de um cliente ja cadastrado
 * 
 * @author paulo
 *
 */
public class CpfUnicoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CpfUnicoException(String message, Throwable cause) {
		super(message, cause);
	}

	public CpfUnicoException(String message) {
		super(message);
	}

}
