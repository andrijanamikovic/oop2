package Telefoni;

public class Broj {
	private String kodDrzave;
	private String pozivniBroj;
	private String broj;
	
	public Broj(String kodDrzave, String pozivniBroj, String broj) {
		super();
		this.kodDrzave = kodDrzave;
		this.pozivniBroj = pozivniBroj;
		this.broj = broj;
	}
	
	public boolean istaDrzava(Broj b) {
		return this.kodDrzave.equals(b.kodDrzave);
	}
	
	public boolean istaMreza(Broj b) {
		return kodDrzave.equals(b.kodDrzave)  && this.pozivniBroj.equals(b.pozivniBroj);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Broj)) return false;
		Broj o = (Broj)obj;
		return (this.istaMreza(o)&&this.broj.equals(o.broj));
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("+").append(this.kodDrzave).append(" ").append(this.pozivniBroj).append(" ").append(this.broj);
		return sb.toString();
	}
	
	public Broj(char[] b) {
			String drz = new String();
			String poz = new String();
			String br = new String();
			
			for (int i=1; i<b.length; i++) {
				if (i<4) {
					drz+=b[i];
				}
				else if (i<6) {
					poz+=b[i];
				}
				else {
					br+=b[i];
				}
			}
			this.kodDrzave = drz;
			this.pozivniBroj = poz;
			this.broj = br;
	}
	
	/*public static void main(String[] args) {
		char[] c = {'+','3','8','1','6','1','1','1','6','6','7','0','1'};
		Broj b1 = new Broj(c);
		System.out.println(b1+"  broj\n");
		
	}*/
	
}
