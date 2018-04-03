package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;
	
	@BeforeClass
	public static void testandoBeforeClass() {
		System.out.println("testandoBeforeClass");
	}
	
	@AfterClass
	public static void testandoAfterClass() {
		System.out.println("testandoAfterClass");
	}
	
	@Before
	public void setUp() {
		System.out.println("setUp");
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.maria = new Usuario("Maria");
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// parte 1 : cenário (Arrange)
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
											.lance(joao, 250.0)
											.lance(jose, 300.0)
											.lance(maria, 400.0)
											.constroi();
		
		// parte 2 : Ação (Act)
		leiloeiro.avalia(leilao);
		
		// parte 3 : Validação (Assert)
		
//		double maiorEsperado = 400.0;
//		double menorEsperado = 250;
//		
//		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
//		
//		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		
		assertThat(leiloeiro.getMenorLance(), is(250.0));
		assertThat(leiloeiro.getMaiorLance(), is(400.0));
	}
	
	@Test
	public void deveCalcularValorMedioLances() {
		//Arrange
		// parte 1 : cenário (Arrange)
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
											.lance(joao, 250.0)
											.lance(jose, 300.0)
											.lance(maria, 400.0)
											.constroi();
		
		//Act
		// parte 2 : Ação (Act)
		leiloeiro.calculaValorMedioLances(leilao);
		
		//Assert
		double valorMedioEsperado = 316.7;
		
		//assertEquals(valorMedioEsperado, leiloeiro.getValorMedio(), 0.1);
		assertThat(leiloeiro.getValorMedio(), is(closeTo(valorMedioEsperado, 0.1)));
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		//Arrange

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
											.lance(joao, 200.0)
											.constroi();
		
		//Act
		leiloeiro.avalia(leilao);
		
		//Assert
		//assertEquals(200.0, leiloeiro.getMaiorLance(), 0.00001);
		//assertEquals(200.0, leiloeiro.getMenorLance(), 0.00001);
		assertThat(leiloeiro.getMaiorLance(), is(200.0));
		assertThat(leiloeiro.getMenorLance(), is(200.0));
		assertThat(leiloeiro.getMenorLance(), is(equalTo(leiloeiro.getMaiorLance())));
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		//Arrange
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
											.lance(joao, 100.0)
											.lance(maria, 200.0)
											.lance(joao, 300.0)
											.lance(maria, 400.0)
											.constroi();
		
		//Act
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		//Assert
//		assertEquals(3, maiores.size());
		assertThat(maiores, hasSize(3));
		
//		assertEquals(400, maiores.get(0).getValor(), 0.00001);
//		assertEquals(300, maiores.get(1).getValor(), 0.00001);
//		assertEquals(200, maiores.get(2).getValor(), 0.00001);
		
		assertThat(maiores, hasItems(
				new Lance(maria, 400),
				new Lance(joao, 300),
				new Lance(maria, 200)
		));
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances_LeilaoComDoisLances() {
		//Arrange
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
											.lance(joao, 100.0)
											.lance(maria, 200.0)
											.constroi();
		
		//Act
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
	
		//Assert
//		assertEquals(2, maiores.size());
//		assertEquals(200, maiores.get(0).getValor(), 0.00001);
//		assertEquals(100, maiores.get(1).getValor(), 0.00001);
		
		assertThat(maiores, hasSize(2));
		
		assertThat(maiores, contains(
				new Lance(maria, 200),
				new Lance(joao, 100)
		));
	}
	
	@Test(expected=RuntimeException.class)
	public void deveEncontrarOsTresMaioresLances_LeilaoSemLances() {
		//Arrange
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").constroi();
		
		//Act
		leiloeiro.avalia(leilao);

		//deixaram de fazer sentido depois que o método avalia passou a lançar RuntimeException quando o leilao nao possui lances
//		List<Lance> maiores = leiloeiro.getTresMaiores();
//		
//		//Assert
//		assertTrue(maiores.isEmpty());
//		assertEquals(0, maiores.size());
		
	}
	@Test
	public void deveEntenderLancesEmOrdemAleatoria() {
		//Arrange
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
											.lance(joao, 120.0)
											.lance(jose, 450.0)
											.lance(joao, 700.0)
											.lance(jose, 630.0)
											.lance(joao, 230.0)
											.constroi();
		
		//Act
		leiloeiro.avalia(leilao);
		
		//Assert
//		assertEquals(120.0, leiloeiro.getMenorLance(), 0.00001);
//		assertEquals(700.0, leiloeiro.getMaiorLance(), 0.00001);
		
		assertThat(leiloeiro.getMenorLance(), is(120.0));
		assertThat(leiloeiro.getMaiorLance(), is(700.0));
	}
	
	@Test
	public void deveEntenderLancesEmOrdemDecrescente() {
		//Arrange
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
											.lance(maria, 400.0)
											.lance(maria, 300.0)
											.lance(joao, 200.0)
											.lance(maria, 100.0)
											.constroi();
		
		//Act
		leiloeiro.avalia(leilao);
		
		//Assert
//		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
//		assertEquals(100.0, leiloeiro.getMenorLance(), 0.00001);
		
		assertThat(leiloeiro.getMaiorLance(), is(equalTo(400.0)));
		assertThat(leiloeiro.getMenorLance(), is(equalTo(100.0)));
	}

	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
		//Arrange
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
											.constroi();
		
		//Act
		leiloeiro.avalia(leilao);
		
		//Assert???
	}
	
	@After
	public void finaliza() {
		System.out.println("fim");
	}
}
