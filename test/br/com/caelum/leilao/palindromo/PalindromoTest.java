package br.com.caelum.leilao.palindromo;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.palindromo.Palindromo;

public class PalindromoTest {

	@Test
	public void deveSerPalindromo1() {
		//Arrange
		String s1 = "Socorram-me subi no onibus em Marrocos";
		
		//Act
		Palindromo palindromo = new Palindromo();
		boolean ehPalindromo = palindromo.ehPalindromo(s1);
		
		//Assert
		Assert.assertTrue(ehPalindromo);
	}
	
	@Test
	public void deveSerPalindromo2() {
		//Arrange
		String s1 = "Anotaram a data da maratona";
		
		//Act
		Palindromo palindromo = new Palindromo();
		boolean ehPalindromo = palindromo.ehPalindromo(s1);
		
		//Assert
		Assert.assertTrue(ehPalindromo);
	}
	
	@Test
	public void deveSerPalindromo3() {
		//Arrange
		String s1 = "XAX";
		
		//Act
		Palindromo palindromo = new Palindromo();
		boolean ehPalindromo = palindromo.ehPalindromo(s1);
		
		//Assert
		Assert.assertTrue(ehPalindromo);
	}
	
	@Test
	public void deveSerPalindromo4() {
		//Arrange
		String s1 = "XAAX";
		
		//Act
		Palindromo palindromo = new Palindromo();
		boolean ehPalindromo = palindromo.ehPalindromo(s1);
		
		//Assert
		Assert.assertTrue(ehPalindromo);
	}
	
	@Test
	public void deveSerPalindromo5() {
		//Arrange
		String s1 = "XYAYX";
		
		//Act
		Palindromo palindromo = new Palindromo();
		boolean ehPalindromo = palindromo.ehPalindromo(s1);
		
		//Assert
		Assert.assertTrue(ehPalindromo);
	}
	
	@Test
	public void deveNaoSerPalindromo1() {
		//Arrange
		String s1 = "Anotaram a data";
		
		//Act
		Palindromo palindromo = new Palindromo();
		boolean ehPalindromo = palindromo.ehPalindromo(s1);
		
		//Assert
		Assert.assertFalse(ehPalindromo);
	}
	
	@Test
	public void deveNaoSerPalindromo2() {
		//Arrange
		String s1 = "An";
		
		//Act
		Palindromo palindromo = new Palindromo();
		boolean ehPalindromo = palindromo.ehPalindromo(s1);
		
		//Assert
		Assert.assertFalse(ehPalindromo);
	}
	
	@Test
	public void deveNaoSerPalindromo3() {
		//Arrange
		String s1 = "XYZ";
		
		//Act
		Palindromo palindromo = new Palindromo();
		boolean ehPalindromo = palindromo.ehPalindromo(s1);
		
		//Assert
		Assert.assertFalse(ehPalindromo);
	}

}
