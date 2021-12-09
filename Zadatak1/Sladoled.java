package Sladoled;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public class Sladoled {
	/*private ArrayList<Elem> sladoledi = new ArrayList<>();
	
	private class Elem{
		Ukus u;
		double v;
		public Elem(Ukus u, double v) {
			this.u = u;
			this.v = v;
		}
		
	}*/
	protected HashMap<Ukus, Double> sladoledi;
	protected double zapremina;
	private double trenutna;
	public Sladoled(double zapremina) {
		this.zapremina = zapremina;
		sladoledi = new HashMap<>();
		trenutna = 0;
	}
	
	public void dodaj(double kolicina, Ukus ukus ) {
		boolean flag = false;
		for (Ukus u:sladoledi.keySet()) {
			if (u.equals(ukus)) {
				if (trenutna + kolicina > zapremina) { kolicina = zapremina - trenutna; }
				trenutna +=kolicina;
				flag = true;
				kolicina+=sladoledi.get(u);
				sladoledi.remove(u);
				break;
			}
		}
		if (!flag) trenutna +=kolicina;
		if (trenutna > zapremina) {
			trenutna-=kolicina;
			kolicina = zapremina - trenutna;
			trenutna +=kolicina;
		}
		if (kolicina != 0)
			sladoledi.put(ukus,kolicina);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int br = 0;
		for (Ukus u:sladoledi.keySet()) {
			sb.append(sladoledi.get(u)).append("ml").append(u);
			br++;
			if (br != sladoledi.size()) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}
	
/*	public static void main(String[] args) {
		Sladoled s = new Sladoled (120);
		Ukus u1 = new Ukus("jagoda", Color.RED);
		Ukus u2 = new Ukus("cokolada", Color.BLACK);
		Ukus u3 = new Ukus("jagoda", Color.RED);
		s.dodaj(20, u1);
		s.dodaj(70, u3);
		s.dodaj(40, u2);
		System.out.println(s);
	}*/
}
