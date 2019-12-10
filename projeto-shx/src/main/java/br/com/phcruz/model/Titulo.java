package br.com.phcruz.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 * Classe de modelo Titulo
 * @author paulo
 *
 */
@Entity
@Table(name = "titulo")
public class Titulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Min(value = 1, message = "O usuário deve ser selecionado.")
	@Transient
	private Long idCliente;

	@Transient
	private String nome;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Transient
	private Date dataVencimento;

	@NumberFormat(pattern = "#,##0.00")
	@Transient
	private BigDecimal totalReceber;

	@NotNull(message = "A data de emissão é obrigatória.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="data_emissao")
	private Date dataEmissao;

	@NotNull(message = "A quantidade de parcelas é obrigatório.")
	@Range(min = 1, max = 99, message = "O número de parcelas deve ser entre {min} e {max}")
	@Column(name = "parcelas", length = 2)
	private Integer parcelas;

	@NotNull(message = "O dia de vencimento é obrigatório.")
	@Range(min = 1, max = 30, message = "O dia de vencimento deve ser entre os dias {min} e {max}")
	@Column(name = "dia_vencimento", length = 2)
	private Integer diaVencimento;

	@NotNull(message = "O valor total do título é obrigatório.")
	@DecimalMin(value = "1.00", message = "O valor não pode ser menor que 1,00")
	@DecimalMax(value = "9999999.99", message = "O valor não pode ser maior que 9.999.999,99")
	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@DecimalMin(value = "0.00", message = "O valor não pode ser menor que 0,00")
	@DecimalMax(value = "99.00", message = "O valor não pode ser maior que 99,00")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorMulta;

	@ManyToOne
    @JoinColumn(name="cliente_id", nullable=false)
	private Cliente cliente;
	
	public Titulo() {
	}

	public Titulo(Long id, Long idCliente, Date dataEmissao, Integer parcelas, Integer diaVencimento,
			BigDecimal valorTotal, BigDecimal valorMulta, Cliente cliente) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.dataEmissao = dataEmissao;
		this.parcelas = parcelas;
		this.diaVencimento = diaVencimento;
		this.valorTotal = valorTotal;
		this.valorMulta = valorMulta;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getTotalReceber() {
		return totalReceber;
	}

	public void setTotalReceber(BigDecimal totalReceber) {
		this.totalReceber = totalReceber;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
