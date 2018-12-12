package com.ciessa.museum.bz.legacy;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FUNCIONESBzService {
	
	public String replacerURL(String outBuffer) {
	      String data = outBuffer.toString();
	      try {
	         data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
	         data = data.replaceAll("\\+", "%2B");
	         data = URLDecoder.decode(data, "utf-8");
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return data;
	   }
	
	public Boolean ValidarAammdd(String input) {
		try {
			if (input.length() != 6) {
				return false;
			}else {
				Date valor = new SimpleDateFormat("yyMMdd").parse(input);
				return true;			
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public Boolean ValidarDdmmAAAA(String input) {
		try {
			if (input.length() != 8) {
				return false;
			}else {
				Date valor = new SimpleDateFormat("ddMMyyyy").parse(input);
				return true;			
			}
		} catch (Exception e) {
			return false;
		}
	}
	//Convert Fecha Año corto 810424 a 19810424
	public String ConvertAmdToDma(String input) {
		if (input.length() != 6) {
			return "19000101";
		}else {
			Integer anioCorto = Integer.parseInt( input.substring(0, 2) );
			
			if (anioCorto > 50) {
				anioCorto = 1900+anioCorto;
			}else {
				anioCorto = 2000+anioCorto;
			}
			return anioCorto.toString()+input.substring(2,6);			
		}	
	}
	
	public String ConvertCamdToAmd(String input) {
		Long fecha = 19000000 + Long.parseLong(input); 
		/*
		Integer yearC = Integer.parseInt(input.substring(0, 1));
		Integer year = 0;
		if (yearC == 1) {
			year = 2000 + Integer.parseInt(input.substring(1, 2));
		}
		if (yearC == 0) {
			year = 1900 + Integer.parseInt(input.substring(1, 2));
		}
		//year = year + 1900;
		//year = year + Integer.parseInt(input.substring(1, 2));
		return year.toString() + input.substring(3);
		*/
		return fecha.toString(); 
	}
	
	public String StringToArrayString(String txtInicial, Integer iniciar, String txtInput) {
		
		char[] txtCaracteresI = txtInicial.toCharArray();
		//convertir txtinput a chars
		char[] txtCaracteres = txtInput.toCharArray();
		
		for (int x=0; x < txtCaracteres.length; x++ ) {
			txtCaracteresI[(iniciar-1)+x] = txtCaracteres[0];						
		}
		String resultado = new String(txtCaracteresI);
			
		
		return resultado;
	}
	
	public String StringToStringPosition(String txtFinal, Integer indexFinal, String txtInicial, Integer indexInicial) {
		
		char[] CaracteresF = txtFinal.toCharArray();
		//convertir txtinput a chars
		char[] CaracteresI = txtInicial.toCharArray();
		CaracteresF[(indexFinal-1)] = CaracteresI[(indexInicial-1)];
		String resultado = new String(CaracteresF);
		return resultado;
	}
	
	public String FormatoFechaHora() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		return dateFormat.format(date);
	}
	/*
	 * 
		dd.MM.yy 09.04.98
		yyyy.MM.dd G 'at' hh:mm:ss z 1998.04.09 AD at 06:15:55 PDT
		EEE, MMM d, ''yy Thu, Apr 9, '98
		h:mm a 6:15 PM
		H:mm 18:15
		H:mm:ss:SSS 18:15:55:624
		K:mm a,z 6:15 PM,PDT
		yyyy.MMMMM.dd GGG hh:mm aaa
	 */
	public String FormatoFechaHora(String Formato) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat(Formato);
		return dateFormat.format(date);
	}
	
	public Boolean BigDecimalComparar(String valor1, String valor2, String simbolo) {
		 BigDecimal bg1 = new BigDecimal(valor1);
		 BigDecimal bg2 = new BigDecimal(valor2);
		 int res = bg1.compareTo(bg2); // 0 iguales, 1 el 1ero es mayor y -1 el segundo es mayor
		 
		 int simbo=0;
		 if (simbolo == "=") simbo = 1;
		 if (simbolo == "==") simbo = 2;
		 if (simbolo == "!=") simbo = 3;
		 if (simbolo == ">") simbo = 4;
		 if (simbolo == "<") simbo = 5;
		 if (simbolo == ">=") simbo = 6;
		 if (simbolo == "<=") simbo = 7;
		 
		 
		switch (simbo) {
		case 1:
		case 2:
			if (res == 0) {
				return true;
			}
			break;
		case 3:
			if (res != 0) {
				return true;
			}
			break;
		case 4:
			if (res == 1) {
				return true;
			}
			break;
		case 5:
			if (res == -1) {
				return true;
			}
			break;
		case 6:
			if (res == 0) {
				return true;
			}
			if (res == 1) {
				return true;
			}
			break;
		case 7:
			if (res == 0) {
				return true;
			}
			if (res == -1) {
				return true;
			}
			break;

		default:
			 throw new IllegalArgumentException("Argumento Inválido en: " + simbolo);
		}


		return false;
	}
	
	
	
	
	
	
	

}


