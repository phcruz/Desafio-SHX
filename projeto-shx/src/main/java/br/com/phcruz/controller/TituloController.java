package br.com.phcruz.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.phcruz.exception.ParcelaMinimaException;
import br.com.phcruz.model.Cliente;
import br.com.phcruz.model.Titulo;
import br.com.phcruz.service.TituloService;
import br.com.phcruz.util.Constantes;

/**
 * Classe de controller responsável por receber requests e prover responses de títulos
 * @author paulo
 *
 */
@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private TituloService tituloService;
	
	/**
	 * Método responsável por prover a tela de novo titulo
	 * @return ModelAndView
	 */
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(Constantes.CADASTRO_TITULO_VIEW);
		mv.addObject(new Titulo());
		return mv;
	}
	
	/**
	 * Método responsável por listar todos os titulos cadastrados
	 * @return ModelAndView
	 */
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = tituloService.listar();
		
		ModelAndView mv = new ModelAndView(Constantes.PESQUISA_TITULO_VIEW);
		mv.addObject(Constantes.TITULOS, todosTitulos);
		return mv;
	}
	
	/**
	 * Método responsável por receber e validar o Titulo para salvar no banco de dados
	 * @param titulo : Titulo
	 * @param errors : Errors
	 * @param attributes : RedirectAttributes
	 * @return String
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Valid Titulo titulo, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return Constantes.CADASTRO_TITULO_VIEW;
		}
		
		try {
			tituloService.salvar(titulo);
			attributes.addFlashAttribute(Constantes.MENSAGEM, Constantes.MENSAGEM_TITULO_SALVO);
			return "redirect:/titulos/novo"; 
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataNascimento", null, e.getMessage());
			return Constantes.CADASTRO_TITULO_VIEW;
		} catch (ParcelaMinimaException e) {
			errors.rejectValue("parcelas", null, e.getMessage());
			return Constantes.CADASTRO_TITULO_VIEW;
		}
	}

	/**
	 * Método responsável por receber requisição de listar todos os títulos de um usuário especifico
	 * @param idCliente : Long
	 * @return ModelAndView
	 */
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public ModelAndView edicao(@PathVariable("id") Long idCliente) {
		List<Titulo> todosTitulos = tituloService.listarTitulosCliente(idCliente);
		
		ModelAndView mv = new ModelAndView(Constantes.PESQUISA_TITULO_VIEW);
		mv.addObject(Constantes.TITULOS, todosTitulos);
		mv.addObject(Constantes.LINK_VOLTAR, Constantes.MENSAGEM_LINK_VOLTAR);
		return mv;
	}
	
	/**
	 * Método responsável por prover a lista de clientes para a pagina de titulos, usado como no select
	 * @return List<Cliente>
	 */
	@ModelAttribute("todosClientesCadastrados")
	public List<Cliente> todosClientes() {
		return tituloService.todosClientes();
	}
}
