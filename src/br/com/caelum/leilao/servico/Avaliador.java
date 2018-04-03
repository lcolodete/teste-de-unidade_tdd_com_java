package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double valorMedio = 0;
	private List<Lance> maiores;

	public void avalia(Leilao leilao) {
		
		if (leilao.getLances().size() == 0) {
			throw new RuntimeException("Não é possível avaliar um leilão sem lances!");
		}
		
		for (Lance lance : leilao.getLances()) { 
			if (lance.getValor() > maiorDeTodos ) maiorDeTodos = lance.getValor();
			if (lance.getValor() < menorDeTodos) menorDeTodos  = lance.getValor();
		}
		
		pegaOsMaioresLancesNo(leilao);
	}
	
	private void pegaOsMaioresLancesNo(Leilao leilao) {
		maiores = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(maiores, new Comparator<Lance>() {
			public int compare(Lance o1, Lance o2) {
				if (o1.getValor() > o2.getValor()) return -1;
				if (o1.getValor() < o2.getValor()) return 1;
				return 0;
			}
		});
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}

	public List<Lance> getTresMaiores() {
		return maiores;
	}

	public Double getMaiorLance() {
		return maiorDeTodos;
	}
	
	public Double getMenorLance() {
		return menorDeTodos;
	}
	
	public void calculaValorMedioLances(Leilao leilao) {
		double soma = 0;
		for (Lance lance : leilao.getLances()) {
			soma += lance.getValor();
		}
		valorMedio = soma/leilao.getLances().size();
	}
	
	public double getValorMedio() {
		return valorMedio;
	}
}
