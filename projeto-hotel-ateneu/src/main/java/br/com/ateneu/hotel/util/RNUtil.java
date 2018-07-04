package br.com.ateneu.hotel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RNUtil {

	public String dataParaString(Date data) throws ParseException {
		SimpleDateFormat entrada = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat saida = new SimpleDateFormat("dd/MM/yyyy");
		String resultado;
		
		return resultado = saida.format(entrada.parse(data.toString()));
	
		
		
	}
	
	public Date stringParaData(String data) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = formato.parse(data);
		
		return dataFormatada;
	}

	public String definirPeriodo(String data) throws ParseException {
			
			System.out.println("Data String: " + data);
			String diaString = data.substring(0, 2);
			System.out.println("Dia string: " + diaString);
			Integer diaInteiro = Integer.parseInt(diaString);
				
			
			
			String mesString = data.substring(3, 5);
			System.out.println("Mês string: " + mesString);
			Integer mesInteiro = Integer.parseInt(mesString);
			
			System.out.println("Dia convertido: " + diaInteiro);
			System.out.println("Mês convertido: " + mesInteiro);
			
			
			if((diaInteiro >= 1 && diaInteiro <= 31) &&  (mesInteiro == 1 || mesInteiro == 7)){
				return "alta-estacao";
			} else if ((diaInteiro >= 1 && diaInteiro <= 22) &&  (mesInteiro == 6)) {
				return "sao-joao-tarifa-1";
			} else if ((diaInteiro >= 23 && diaInteiro <= 30) &&  (mesInteiro == 6)) {
				return "sao-joao-tarifa-2";
			} else if ((diaInteiro >= 1 && diaInteiro <= 23) &&  (mesInteiro == 12)) {
				return "alta-estacao";
			} else if ((diaInteiro >= 24 && diaInteiro <= 31) &&  (mesInteiro == 12)) {
				return "natal-reveillon";
			} else {
				return "baixa-estacao";
			}
	}
	
	public long diferençaDatas(Date dt1, Date dt2) {
		long dia = 24L * 60L * 60L * 1000L;
		long diferenca = ((dt1.getTime() - dt2.getTime())/dia);
		
		return diferenca;
	}

}
