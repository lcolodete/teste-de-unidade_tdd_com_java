package br.com.caelum.matematica;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatematicaMalucaTest {

	@Test
	public void calculaComNumeroMaiorQue30() {
		//Arrange
		MatematicaMaluca mm = new MatematicaMaluca();
		
		//Act
		
		int resultado = mm.contaMaluca(44);
		
		//Assert
		assertEquals(176, resultado);
	}
	
	@Test
	public void calculaComNumeroMenorQue30EMaiorQue10() {
		//Arrange
		MatematicaMaluca mm = new MatematicaMaluca();
		
		//Act
		int resultado = mm.contaMaluca(25);
		
		//Assert
		assertEquals(75, resultado);
	}
	
	@Test
	public void calculaComNumeroMenorQue10() {
		//Arrange
		MatematicaMaluca mm = new MatematicaMaluca();
		
		//Act
		int resultado = mm.contaMaluca(9);
		
		//Assert
		assertEquals(18, resultado);
	}
	
	@Test
	public void calculaComNumero30() {
		//Arrange
		MatematicaMaluca mm = new MatematicaMaluca();
		
		//Act
		int resultado = mm.contaMaluca(30);
		
		//Assert
		assertEquals(90, resultado);
	}
	
	@Test
	public void calculaComNumero10() {
		//Arrange
		MatematicaMaluca mm = new MatematicaMaluca();
		
		//Act
		int resultado = mm.contaMaluca(10);
		
		//Assert
		assertEquals(20, resultado);
	}
}
