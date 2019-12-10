package br.com.phcruz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.phcruz.exception.ClienteTituloException;
import br.com.phcruz.exception.CpfUnicoException;
import br.com.phcruz.exception.DataInvalidaException;
import br.com.phcruz.model.Cliente;
import br.com.phcruz.model.Titulo;
import br.com.phcruz.repository.ClientesRepository;
import br.com.phcruz.repository.TitulosRepository;
import br.com.phcruz.util.Constantes;

/**
 * Classe de serviço responsável pelas regras de negócio e acesso ao banco de dados
 * @author paulo
 *
 */
@Service
public class ClienteService {
	
	@Autowired
	private TitulosRepository titulosRepository;
	
	@Autowired
	private ClientesRepository clientesRepository;

	/**
	 * Método responsável por salvar um cliente no banco de dados
	 * @param Cliente
	 */
	public void salvar(Cliente cliente) {
		try {
			boolean isIsert = false;
			if(cliente.getId() == null) {
				isIsert = true;
			}
			
			if (isIsert) {
				Cliente c = clientesRepository.findClienteByCpf(cliente.getCpf());
				
				if(c != null) {
					throw new CpfUnicoException(Constantes.MENSAGEM_CPF_EXCEPTION);
				}
			}
			
			clientesRepository.save(cliente);
			
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains(Constantes.CONSTRAINT_VIOLATION_EXCEPTION)) {
				throw new CpfUnicoException(e.getMessage());
			} else {
				throw new DataInvalidaException(Constantes.MENSAGEM_DATA_EXCEPTION);
			}
		}
	}
	
	public List<Cliente> listar() {
		return clientesRepository.findAll();
	}
	
	/**
	 * Método responsável por deletar um cliente da base de dados
	 * @param id : Long
	 */
	public void excluir(Long id) {
		try {
			List<Titulo> titulos = titulosRepository.findTitulosClienteByClienteId(id);
			if (!titulos.isEmpty()) {
				throw new ClienteTituloException(Constantes.MENSAGEM_IMPOSSIVEL_EXCLUIR_CLIENTE);
			}
			
			clientesRepository.deleteById(id);
		} catch (Exception e) {
			throw new ClienteTituloException(e.getMessage());
		}
	}
	
	/**
	 * Metodo responsável por buscar um cliente pelo id do cliente
	 * @param id : Long
	 * @return Cliente
	 */
	public Cliente buscarClienteId(Long id) {
		Optional<Cliente> c = clientesRepository.findById(id);
		if (c.isPresent()) {
			return c.get();
		}
		return null;
	}
}
