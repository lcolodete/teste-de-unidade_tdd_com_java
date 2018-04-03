package br.com.caelum.leilao.dominio;

import org.junit.Before;
import org.junit.Test;

public class LanceTest {

	private Usuario joao;

	@Before
	public void setUp() {
		this.joao = new Usuario("João");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAceitarLanceComValorNegativo() {
		//Arrange
		
		//Act
		Lance lance = new Lance(joao, -120.0);
		
		//Assert
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAceitarLanceComValorZero() {
		//Arrange
		
		//Act
		Lance lance = new Lance(joao, 0);
		
		//Assert
	}
}
