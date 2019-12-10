package br.com.phcruz.util;

/**
 * Classe de constantes utilizadas no sistema
 * @author paulo
 *
 */
public class Constantes {

	/* UtilData */
	public static final int ADICIONA_DIAS_SABADO = 2;
	public static final int ADICIONA_DIAS_DOMINGO = 1;
	
	/* TituloService */
	public static final int NUMERO_DIAS_SOMA = 30;
	public static final String MENSAGEM_PARCELA_EXCEPTION = "O valor da parcela não pode ser inferior a 10,00.";
	
	/* ClienteService */
	public static final String MENSAGEM_CPF_EXCEPTION = "CPF já cadastrado para outro usuário.";
	public static final String CONSTRAINT_VIOLATION_EXCEPTION = "org.hibernate.exception.ConstraintViolationException";
	
	/* TituloController */
	public static final String CADASTRO_TITULO_VIEW = "CadastroTitulo";
	public static final String PESQUISA_TITULO_VIEW = "PesquisaTitulos";
	public static final String TITULOS = "titulos";
	public static final String MENSAGEM_TITULO_SALVO = "Título salvo com sucesso!";
	public static final String LINK_VOLTAR = "linkVoltar";
	public static final String MENSAGEM_LINK_VOLTAR = "Habilita link voltar";
	
	/* ClienteController */
	public static final String CADASTRO_CLIENTE_VIEW = "CadastroCliente";
	public static final String PESQUISA_CLIENTE_VIEW = "PesquisaClientes";
	public static final String CLIENTES = "clientes";
	public static final String MENSAGEM_CLIENTE_SALVO = "Cliente salvo com sucesso!";
	public static final String MENSAGEM_CLIENTE_EXCLUIDO = "Cliente excluído com sucesso!";
	public static final String MENSAGEM_IMPOSSIVEL_EXCLUIR_CLIENTE = "Não foi possível excluir o cliente, pois o mesmo possuí titulos em seu nome!";
	
	/* Comuns*/
	public static final String MENSAGEM = "mensagem";
	public static final String MENSAGEM_DATA_EXCEPTION = "Formato de data inválido.";
}
