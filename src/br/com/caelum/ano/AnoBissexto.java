package br.com.caelum.ano;

import java.text.DateFormat;
import java.util.Calendar;

public class AnoBissexto {

	public boolean ehBissexto(int ano) {
		if ((ano % 2 == 0) &&
				tem366Dias(ano))
			return true;
		return false;
	}

	private boolean tem366Dias(int ano) {
		
		Calendar c = Calendar.getInstance();
		c.set(ano, 1, 28);
		
		c.add(Calendar.DAY_OF_MONTH, 1);
		
		System.out.println( DateFormat.getDateInstance().format(c.getTime()) );
		
		if (c.get(Calendar.DAY_OF_MONTH) == 29)
			return true;
		
		return false;
	}

}
