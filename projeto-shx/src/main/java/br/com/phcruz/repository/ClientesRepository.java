package br.com.phcruz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.phcruz.model.Cliente;

/**
 * Classe responsável por prover metodos de acesso ao banco de dados na tabela
 * Cliente
 * 
 * @author paulo
 *
 */
public interface ClientesRepository extends JpaRepository<Cliente, Long> {

	public Cliente findClienteByCpf(String cpf);
}
