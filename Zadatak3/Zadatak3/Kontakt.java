package Telefoni;

public class Kontakt extends Stavka {
	
	protected String ime;
	protected Broj broj;
	
	
	public Kontakt(String ime, Broj broj) {
		super(ime,broj.toString());
		this.ime = new String(ime);
		this.broj = broj;
	}

}
