package Igra;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.HashSet;

public class Mreza extends Panel {
	
	private Igra owner;
	protected int width = 4;
	protected int height = 5;
	private ArrayList<Polje> lista = new ArrayList<>();
	private Polje[][] matrica = new Polje[width][height];
	private HashSet<Integer> skupCelih = new HashSet<>();
	
	
	public Mreza(Igra owner) {
		this.owner = owner;
		this.setBackground(Color.black);
		this.setLayout(new GridLayout(height, width, 3, 3));
		int k = 0;
		for (int i = 0; i<width; i++) {
			for (int j=0; j<height; j++) {
				matrica[i][j] = new Polje(this,k);
				//System.out.println(matrica[i][j]);
				add(matrica[i][j]);
				if ((matrica[i][j].status).getStatus().equals("IZABRANO")) {
					lista.add(matrica[i][j]);
					skupCelih.add(matrica[i][j].broj);
				}
				k++;
			}
		}
		repaint();
	}
	
	public Mreza(Igra owner, int width, int height) {
		this.owner = owner;
		this.setBackground(Color.black);
		this.setLayout(new GridLayout(height, width, 3, 3));
		this.owner = owner;
		this.width = width;
		this.height = height;
		int k = 0;
		for (int i = 0; i<width; i++) {
			for (int j=0; j<height; j++) {
				matrica[i][j] = new Polje(this,k);
				//System.out.println(matrica[i][j]);
				add(matrica[i][j]);
				if ((matrica[i][j].status).getStatus().equals("IZABRANO")) {
					lista.add(matrica[i][j]);
					skupCelih.add(matrica[i][j].broj);
				}
				k++;
			}
		}
		repaint();
	}

	public ArrayList<Polje> getLista() {
		return lista;
	}

	public void promenjenStatus(Polje p) {
		if (p.status.getStatus().equals("IZABRANO")) {
			lista.add(p);
			skupCelih.add(p.broj);
		} else {
			lista.remove(p);
		}
		owner.igrajEnable();
	}

	public HashSet<Integer> getSkupCelih() {
		return skupCelih;
	}
	
	
	
	
	
	
	
}
