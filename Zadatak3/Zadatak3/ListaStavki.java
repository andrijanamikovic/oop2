package Telefoni;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;

public class ListaStavki extends Panel {
	protected ArrayList<Stavka> stavke = new ArrayList<>();
	
	public ListaStavki() {
		//super();
		this.setLayout(new GridLayout(0, 1));
		this.setFont(new Font("Arial",Font.PLAIN,this.getHeight()/3));
	}
	
	public void dodaj(Stavka s) {
		stavke.add(s);
		add(s);
	}
}
