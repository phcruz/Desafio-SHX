package br.com.phcruz.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe de modelo Cliente
 * @author paulo
 *
 */
@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") 
	private Long id;

	@CPF
	@Size(max = 11, message = "Informe apenas os números do CPF.")
	@Column(name = "cpf", length = 11, unique = true)
	private String cpf;

	@NotBlank(message = "O nome do ciente é obrigatório.")
	@Size(max = 100, message = "O nome não pode ter mais de 100 caracteres.")
	@Column(name = "nome", length = 100)
	private String nome;

	@NotNull(message = "A data de nascimento do cliente é obrigatória.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento")
	private Date dataNascimento;

	@OneToMany(mappedBy="cliente", fetch = FetchType.LAZY)
	private List<Titulo> titulo;
	
	public Cliente() {
	}

	public Cliente(Long id, String cpf, String nome, Date dataNascimento) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}
	
	public Cliente(Long id, String cpf, String nome, Date dataNascimento, List<Titulo> titulo) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.titulo = titulo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Titulo> getTitulo() {
		return titulo;
	}

	public void setTitulo(List<Titulo> titulo) {
		this.titulo = titulo;
	}
}
