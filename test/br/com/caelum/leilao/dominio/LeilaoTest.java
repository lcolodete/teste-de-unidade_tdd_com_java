package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static br.com.caelum.hamcrest.matcher.TemUmLance.temUmLance;

import org.junit.Test;

public class LeilaoTest {
	
	@Test
	public void deveTerUmLance() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		assertThat(leilao.getLances(), is(empty()));
		
		Lance lance = new Lance(new Usuario("Steve Jobs"), 2000);
		leilao.propoe(lance);
//		leilao.propoe(new Lance(new Usuario("Steve Wozniak"), 3000));
		assertThat(leilao.getLances(), hasSize(1));
		assertThat(leilao, temUmLance(lance));
	}
	
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		leilao.propoe(new Lance(new Usuario("Steve Wozniak"), 3000));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario steveJobs = new Usuario("Steve Jobs");
		
		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(steveJobs, 3000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(billGates, 3000));
        leilao.propoe(new Lance(steveJobs, 4000));
        leilao.propoe(new Lance(billGates, 5000));
        leilao.propoe(new Lance(steveJobs, 6000));
        leilao.propoe(new Lance(billGates, 7000));
        leilao.propoe(new Lance(steveJobs, 8000));
        leilao.propoe(new Lance(billGates, 9000));
        leilao.propoe(new Lance(steveJobs, 10000));
        leilao.propoe(new Lance(billGates, 11000));

        leilao.propoe(new Lance(steveJobs, 12000));
        
        assertEquals(10, leilao.getLances().size());
        int ultimo = leilao.getLances().size() - 1;
        Lance ultimoLance = leilao.getLances().get(ultimo);
        assertEquals(11000, ultimoLance.getValor(), 0.00001);
	}
	
	@Test
	public void deveDobrarUmLance() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario steveJobs = new Usuario("Steve Jobs");
		Usuario billGates = new Usuario("Bill Gates");
		
		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(billGates, 3000));
		leilao.dobraLance(steveJobs);
		
		assertEquals(3, leilao.getLances().size());
		Lance primeiroLance = leilao.getLances().get(0); 
		int ultimo = leilao.getLances().size() - 1;
		Lance ultimoLance = leilao.getLances().get(ultimo);
		assertEquals(2000, primeiroLance.getValor(), 0.00001);
		assertEquals(4000, ultimoLance.getValor(), 0.00001);
		assertEquals(primeiroLance.getUsuario(), ultimoLance.getUsuario());
	}
	
	@Test
	public void naoDeveDobrarUmLanceSeForLanceSeguidoDoMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario steveJobs = new Usuario("Steve Jobs");
		
		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.dobraLance(steveJobs);
		
		assertEquals(1, leilao.getLances().size());
		Lance primeiroLance = leilao.getLances().get(0); 
		assertEquals(2000, primeiroLance.getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveDobrarUmLanceSeUmMesmoUsuarioJaTem5Lances() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(billGates, 3000));
        leilao.propoe(new Lance(steveJobs, 4000));
        leilao.propoe(new Lance(billGates, 5000));
        leilao.propoe(new Lance(steveJobs, 6000));
        leilao.propoe(new Lance(billGates, 7000));
        leilao.propoe(new Lance(steveJobs, 8000));
        leilao.propoe(new Lance(billGates, 9000));
        leilao.propoe(new Lance(steveJobs, 10000));
        leilao.propoe(new Lance(billGates, 11000));

        leilao.dobraLance(steveJobs);
        leilao.dobraLance(billGates);
        
        assertEquals(10, leilao.getLances().size());
        int ultimo = leilao.getLances().size() - 1;
        Lance ultimoLance = leilao.getLances().get(ultimo);
        assertEquals(11000, ultimoLance.getValor(), 0.00001);
	}

	@Test
	public void naoDeveDobrarUmLanceCasoUsuarioNaoTenhaDadoLanceAnterior() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario steveJobs = new Usuario("Steve Jobs");
		Usuario billGates = new Usuario("Bill Gates");
		
		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.dobraLance(billGates);
		
		assertEquals(1, leilao.getLances().size());
		Lance primeiroLance = leilao.getLances().get(0); 
		assertEquals(2000, primeiroLance.getValor(), 0.00001);
	}

}
