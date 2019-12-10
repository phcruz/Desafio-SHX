package br.com.phcruz.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Classe utilitária para datas
 * @author paulo
 *
 */
public class UtilData {
	
	/**
	 * Metodo que verifica se a data informada cai de fim de semana (sabado ou domingo)
	 * @param data
	 * @return boolean
	 */
	public static boolean isFimSemana(LocalDate data) {
		switch (data.getDayOfWeek()) {
		case SATURDAY:
			return true;
		case SUNDAY:
			return true;
		default:
			return false;
		}
	}
	
	/**
	 * Método responsavel por adicionar 1 ou 2 dias na data para que os vencimentos não ocorrão aos finais de semana
	 * @param data
	 * @return LocalDate
	 */
	public static LocalDate corrigeDataFimSemana(LocalDate data) {
		switch (data.getDayOfWeek()) {
		case SATURDAY:
			data = adicionaDiasData(data, Constantes.ADICIONA_DIAS_SABADO);
			break;
		case SUNDAY:
			data = adicionaDiasData(data, Constantes.ADICIONA_DIAS_DOMINGO);
			break;
		default:
			return data;
		}
		return data;
	}
	
	/**
	 * Método responsável por adicionar dias em uma data
	 * @param data
	 * @param dias
	 * @return LocalDate
	 */
	public static LocalDate adicionaDiasData(LocalDate data, int dias) {
		return data.plusDays(dias);
	}
	
	/**
	 * Método responsável por adicionar meses em uma data
	 * @param data : LocalDate
	 * @param meses : int
	 * @return LocalDate
	 */
	public static LocalDate adicionaMesData(LocalDate data, int meses) {
		return verificaDataFimSemana(data.plusMonths(meses));
	}
	
	/**
	 * Método responsavel por verificar se a data cai em fim de semana, fazer a correção para o próximo dia útil
	 * @param data : LocalDate
	 * @return LocalDate
	 */
	private static LocalDate verificaDataFimSemana(LocalDate data) {
		if(isFimSemana(data)) {
			data = corrigeDataFimSemana(data);
		}
		return data;
	}
	
//	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
//	    return dateToConvert.toInstant()
//	      .atZone(ZoneId.systemDefault())
//	      .toLocalDate();
//	}
	
	/**
	 * Método responsável por converter um Local Date em Date
	 * @param dateToConvert : LocalDate
	 * @return Date
	 */
	public static Date convertToDateViaInstant(LocalDate dateToConvert) {
	    return java.util.Date.from(dateToConvert.atStartOfDay()
	      .atZone(ZoneId.systemDefault())
	      .toInstant());
	}
	
	/**
	 * Método responsável por converter um Date em LocalDate usando a classe java.sql.Date
	 * @param dateToConvert : Date
	 * @return localDate
	 */
	public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
	    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}

	/**
	 * Método responsável por verificar se a data de vencimento é anterior a data atual
	 * @param dataVencimento : LocalDate
	 * @return boolean
	 */
	public static boolean verificaDataVencimento(LocalDate dataVencimento) {
		LocalDate dataAtual = LocalDate.now();
		return dataVencimento.isBefore(dataAtual);
	}
	
	/**
	 * Método responsavel por atualizar a data de emissão para o dia do vencimento escolhido pelo usuário
	 * @param dataEmissao : LocalDate
	 * @param diaVencimento : Integer
	 * @return LocalDate
	 */
	public static LocalDate atualizaDiaVencimento(LocalDate dataEmissao, Integer diaVencimento) {
		int mes = dataEmissao.getMonthValue();
		int ano = dataEmissao.getYear();

		return LocalDate.of(ano, mes, diaVencimento);
	}
}
