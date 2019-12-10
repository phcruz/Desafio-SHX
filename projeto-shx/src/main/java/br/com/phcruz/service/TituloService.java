package br.com.phcruz.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.phcruz.exception.DataInvalidaException;
import br.com.phcruz.exception.ParcelaMinimaException;
import br.com.phcruz.model.Cliente;
import br.com.phcruz.model.Titulo;
import br.com.phcruz.repository.TitulosRepository;
import br.com.phcruz.util.Constantes;
import br.com.phcruz.util.UtilData;

@Service
public class TituloService {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private TitulosRepository titulosRepository;
	
	public void salvar(Titulo titulo) {
		try {
			if(validaValorMinimoParcela(titulo.getValorTotal(), titulo.getParcelas())) {
				throw new ParcelaMinimaException(Constantes.MENSAGEM_PARCELA_EXCEPTION);	
			}
			
			List<Titulo> listaTitulos = criaListaTitulos(titulo);
			listaTitulos.forEach(item -> titulosRepository.save(item));
	
		} catch (DataIntegrityViolationException e) {
			throw new DataInvalidaException(Constantes.MENSAGEM_DATA_EXCEPTION);
		} catch (ParcelaMinimaException e) {
			throw new ParcelaMinimaException(e.getMessage());
		}
	}
	
	public List<Titulo> listar() {
		List<Titulo> lista = titulosRepository.findAll();
		lista = insereDadosLista(lista);

		return lista;
	}
	
	public List<Titulo> listarTitulosCliente(Long idCliente) {
		List<Titulo> lista = titulosRepository.findTitulosClienteByClienteId(idCliente);
		lista = insereDadosLista(lista);

		return lista;
	}
	
	private List<Titulo> insereDadosLista(List<Titulo> lista) {
		lista.forEach(item -> 
		{
			item.setNome(item.getCliente().getNome());
			LocalDate data = UtilData.convertToLocalDateViaSqlDate(item.getDataEmissao());
			data = UtilData.atualizaDiaVencimento(data, item.getDiaVencimento());
			data = UtilData.adicionaMesData(data, item.getParcelas());

			item.setDataVencimento(UtilData.convertToDateViaInstant(data));
			item.setValorMulta(validaValorMulta(data, item.getValorMulta(), item.getValorTotal()));
			item.setTotalReceber(item.getValorTotal().add(item.getValorMulta()));
		});
		
		return lista;
	}
	
	private List<Titulo> criaListaTitulos(Titulo titulo) {
		List<Titulo> lista = new ArrayList<Titulo>();
		BigDecimal valor = new BigDecimal(0);
		BigDecimal valorSomado = new BigDecimal(0);
		Cliente c = clienteService.buscarClienteId(titulo.getIdCliente());
		for(int i = 0; i< titulo.getParcelas(); i++) {
			Long idCliente = titulo.getIdCliente();
			Date dataEmissao = titulo.getDataEmissao();
			int parcela = i + 1;
			int diaVencimento = titulo.getDiaVencimento();
			BigDecimal valorMulta = titulo.getValorMulta();
			
			if (i == 0) {
				valor = getValorParcela(titulo.getValorTotal(), titulo.getParcelas());
				valorSomado = valorSomado.add(valor);
			} else {
				valor = getValorParcela(titulo.getValorTotal().subtract(valorSomado), titulo.getParcelas() - i);
				valorSomado = valorSomado.add(valor);
			}
			
			Titulo t = new Titulo(null, idCliente, dataEmissao, parcela, diaVencimento, valor, valorMulta, c);
			lista.add(t);
		}
		
		return lista;
	}
	
	private boolean validaValorMinimoParcela(BigDecimal valorTotal, float parcelas) {
		BigDecimal resultadoParcela = valorTotal.divide(BigDecimal.valueOf(parcelas), BigDecimal.ROUND_UP);
		if (resultadoParcela.compareTo(BigDecimal.TEN) == -1) {
			return true;
		}
		return false;
	}
	
	private BigDecimal getValorParcela(BigDecimal valorTotal, float parcelas) {
		BigDecimal resultadoParcela = valorTotal.divide(BigDecimal.valueOf(parcelas), BigDecimal.ROUND_UP);
		return resultadoParcela;
	}
	
	public List<Cliente> todosClientes() {
		return clienteService.listar();
	}
	
	private BigDecimal validaValorMulta(LocalDate data, BigDecimal valorAtual, BigDecimal valorTotal) {
		BigDecimal valorMulta = new BigDecimal(0);
		if (UtilData.verificaDataVencimento(data)) {
			if(valorAtual == null) {
				valorAtual = new BigDecimal(0);
			}
			
			valorMulta = valorTotal.multiply(valorAtual.divide(new BigDecimal(100)));
		}
		return valorMulta;
	}
}
