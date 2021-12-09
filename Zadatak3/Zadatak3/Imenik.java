package Telefoni;

public class Imenik extends ListaStavki {
	
	public Imenik() {
		super();
	}
	
	@Override
	public void dodaj(Stavka s) {
		if (s instanceof Kontakt)
			super.dodaj(s);
	}
	
	public String dohvKorisnika(Broj b) throws GNePostoji {
		for (Stavka s:this.stavke) {
			Kontakt stavka = (Kontakt)s;
			if (stavka.broj.equals(b)) {
				return stavka.ime;
			}
		}
		throw new GNePostoji();
	}
	
	public Broj dohvBroj(String ime) throws GNePostoji {
		for (Stavka s:this.stavke) {
			Kontakt stavka = (Kontakt)s;
			if (stavka.ime.equals(ime)) {
				return stavka.broj;
			}
		}
		throw new GNePostoji();
	}
	
/*	public static void main(String[] args) {
		char[] c = {'+','3','8','1','6','1','1','1','6','6','7','0','1'};
		Broj b1 = new Broj(c);
		Kontakt k = new Kontakt("Andrijana",b1);
		Imenik i = new Imenik();
		i.dodaj(k);
		char[] c2 = {'+','3','8','1','6','6','9','1','8','9','8','4','4'};
		Broj b2 = new Broj(c2);
		Kontakt k2 = new Kontakt("Sara",b2);
		i.dodaj(k2);
		try {
			System.out.println(i.dohvKorisnika(b1));
			System.out.println(i.dohvBroj("MIka").toString());
		} catch (GNePostoji e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}*/
}
