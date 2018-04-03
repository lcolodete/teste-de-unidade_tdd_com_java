package br.com.caelum.hamcrest.matcher;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class TemUmLance extends TypeSafeMatcher<Leilao> {

	private Lance lance;
	
	public TemUmLance(Lance lance) {
		this.lance = lance;
	}

	public void describeTo(Description description) {
		description.appendText("Leilao contendo apenas um lance");
	}

	@Override
	protected boolean matchesSafely(Leilao leilao) {
		return (leilao.getLances().size() == 1 && leilao.getLances().get(0).equals(lance));
	}

	@Factory
	public static Matcher<Leilao> temUmLance(Lance lance) {
		return new TemUmLance(lance);
	}
}
