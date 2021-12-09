package Sladoled;

import java.awt.Color;

public class Ukus {
	private String naziv;
	private Color boja;
	
	public Ukus(String naziv, Color boja) {
		this.naziv = naziv;
		this.boja = boja;
	}

	public String getNaziv() {
		return naziv;
	}

	public Color getBoja() {
		return boja;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Ukus)) return false;
		Ukus u = (Ukus)obj;
		return this.naziv.equals(u.naziv);
	}
	
	@Override
	public String toString() {
		return "["+this.naziv+"]";
	}
	
	/*public static void main(String[] args) {
		Ukus u1 = new Ukus("jagoda", Color.RED);
		Ukus u2 = new Ukus("cokolada", Color.BLACK);
		Ukus u3 = new Ukus("jagoda", Color.pink);
		System.out.println(u2+"\n");
		System.out.println(u3.equals(u1)+" "+ u2.equals(u1));
	}*/
}
