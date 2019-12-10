package br.com.phcruz.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.phcruz.exception.ClienteTituloException;
import br.com.phcruz.exception.CpfUnicoException;
import br.com.phcruz.exception.DataInvalidaException;
import br.com.phcruz.model.Cliente;
import br.com.phcruz.service.ClienteService;
import br.com.phcruz.util.Constantes;

/**
 * Classe de controller responsável por receber requests e prover responses de clientes
 * @author paulo
 *
 */
@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	/**
	 * Método responsável por prover a tela de novo cliente
	 * @return ModelAndView
	 */
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(Constantes.CADASTRO_CLIENTE_VIEW);
		mv.addObject(new Cliente());
		return mv;
	}
	
	/**
	 * Método responsável por listar todos os clientes cadastrados
	 * @return ModelAndView
	 */
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Cliente> todosClientes = clienteService.listar();
		
		ModelAndView mv = new ModelAndView(Constantes.PESQUISA_CLIENTE_VIEW);
		mv.addObject(Constantes.CLIENTES, todosClientes);
		return mv;
	}
	
	/**
	 * Método responsável por receber e validar o Cliente para salvar no banco de dados
	 * @param titulo : Titulo
	 * @param errors : Errors
	 * @param attributes : RedirectAttributes
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Valid Cliente cliente, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return Constantes.CADASTRO_CLIENTE_VIEW;
		}
		
		try {
			clienteService.salvar(cliente);
			attributes.addFlashAttribute(Constantes.MENSAGEM, Constantes.MENSAGEM_CLIENTE_SALVO);
			return "redirect:/clientes/novo"; 
		} catch (DataInvalidaException e) {
			errors.rejectValue("dataNascimento", null, e.getMessage());
			return Constantes.CADASTRO_CLIENTE_VIEW;
		} catch (CpfUnicoException e) {
			errors.rejectValue("cpf", null, e.getMessage());
			return Constantes.CADASTRO_CLIENTE_VIEW;
		}
	}
	
	/**
	 * Método responsável por recuperar informações do cliente e prover a tela de edição
	 * @param cliente : Cliente
	 * @return ModelAndView
	 */
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public ModelAndView edicao(@PathVariable("id") Cliente cliente) {
		ModelAndView mv = new ModelAndView(Constantes.CADASTRO_CLIENTE_VIEW); 
		mv.addObject(cliente);
		return mv;
	}
	
	/*
	 * Problemas com o metodo DELETE junto ao thymeleaf, contornado com o metodo abaixo
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		clienteService.excluir(id);
		
		attributes.addFlashAttribute("mensagem", "Cliente excluído com sucesso!");
		return "redirect:/clientes";
	}*/
	
	/**
	 * Método responsável por responder requisições de deletar clientes
	 * @param id : Long
	 * @param attributes : RedirectAttributes
	 * @return String
	 */
	@RequestMapping(value="delete/{id}", method = RequestMethod.GET)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		try {
			clienteService.excluir(id);
		
			attributes.addFlashAttribute(Constantes.MENSAGEM, Constantes.MENSAGEM_CLIENTE_EXCLUIDO);
			return "redirect:/clientes";
		} catch (ClienteTituloException e) {
			attributes.addFlashAttribute(Constantes.MENSAGEM, Constantes.MENSAGEM_IMPOSSIVEL_EXCLUIR_CLIENTE);
			return "redirect:/clientes";
		}		
	}
}
