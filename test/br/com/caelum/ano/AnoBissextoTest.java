package br.com.caelum.ano;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AnoBissextoTest {

	@Test
	public void deveSerAnoBissexto() {
		AnoBissexto anoBissexto = new AnoBissexto();
		
		boolean resultado = anoBissexto.ehBissexto(1984);
		assertTrue(resultado);
	}
	
	@Test
	public void naoDeveSerAnoBissexto() {
		AnoBissexto anoBissexto = new AnoBissexto();
		
		boolean resultado = anoBissexto.ehBissexto(2014);
		assertFalse(resultado);
	}
}
