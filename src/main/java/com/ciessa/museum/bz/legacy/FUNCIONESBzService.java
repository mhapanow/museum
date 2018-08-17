package com.ciessa.museum.bz.legacy;

public class FUNCIONESBzService {
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
	
	
	
	
	
	
	
	

}


