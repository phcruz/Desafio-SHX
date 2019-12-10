package br.com.phcruz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.phcruz.model.Titulo;

/**
 * Classe responsável por prover metodos de acesso ao banco de dados na tabela
 * Titulo
 * 
 * @author paulo
 *
 */
public interface TitulosRepository extends JpaRepository<Titulo, Long> {

	public List<Titulo> findTitulosClienteByClienteId(Long idCliente);
}
