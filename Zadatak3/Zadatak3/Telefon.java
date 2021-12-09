package Telefoni;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.util.Iterator;

import Telefoni.Tastatura.Rezim;

public class Telefon extends Panel {
	private Broj broj;
	private Color boja;
	protected Tastatura tastatura; 
	private Imenik imenik = new Imenik();
	private Panel donji = new Panel();
	private Panel ImenikPanel = new Panel();
	private Button dodajKontakt = new Button();
	private Button iskljuci = new Button();
	
	public Telefon(Broj broj, Color boja) {
		super();
		this.broj = broj;
		this.boja = boja;
		//Kontakt k = new Kontakt("Milica", broj);
		//imenik.dodaj(k);
		populateWindow();
		iskljuci.addActionListener((al)->{
			if (iskljuci.getLabel().equals("Iskljuci telefon")) {
				iskljuci.setBackground(Color.red);
				iskljuci.setLabel("Ukljuci telefon");
			/*	for (Button b:tastatura.niz) {
					b.setEnabled(false);
				}
				dodajKontakt.setEnabled(false); gasenje dugmica*/
			}else {
				iskljuci.setBackground(Color.LIGHT_GRAY);
				iskljuci.setLabel("Iskljuci telefon");
			/*	for (Button b:tastatura.niz) {
					b.setEnabled(true);
				}
				dodajKontakt.setEnabled(true); gasenje dugmica*/
			}
		});
		
		dodajKontakt.addActionListener((al)->{
			if (tastatura.rezim == Rezim.PRVI) {
				
			} else {
				imenik.dodaj(new Kontakt(tastatura.ime.getText(),new Broj(tastatura.natpis.getText().toCharArray())));
				try {
					System.out.println(imenik.dohvBroj(tastatura.ime.getText()));
					tastatura.natpis.setText("");
					tastatura.ime.setText("");
				} catch (GNePostoji e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				revalidate();
			}
			tastatura.changeRezim();
		});
		
	}

	private void populateWindow() {
		this.setBackground(boja);
		this.setLayout(new GridLayout(2,1));
		tastatura = new Tastatura(new Label());
		tastatura.setBackground(boja);
		this.add(tastatura);
		
		donji.setLayout(new GridLayout(2,1));
		
		//imenik.setLayout(new GridLayout(0,1,0,-5));
		/*Iterator<Stavka> iter = imenik.stavke.iterator();
		while (iter.hasNext()) {
			ImenikPanel.add(iter.next());
		}*/
		//ImenikPanel.setLayout(new GridLayout(5,1));
		//ImenikPanel.add(imenik);
		//donji.add(ImenikPanel);
		//donji.setPreferredSize(new Dimension(getHeight()/2,this.getWidth()/2));
		//imenik.setPreferredSize(new Dimension(getHeight()/3,this.getWidth()/3));
		donji.add(imenik);
		dodajKontakt = new Button("Dodaj kontakt");
		iskljuci = new Button("Iskljuci telefon");
		Panel nova = new Panel(new GridLayout(1,2));
		nova.add(dodajKontakt);
		nova.add(iskljuci);
		//donji.add(nova);
		Label poslednji = new Label();
		poslednji.setAlignment(WIDTH);
		poslednji.setText(broj.toString());
		Panel drugi = new Panel(new GridLayout(2,1));
		drugi.add(nova);
		drugi.add(poslednji);
		donji.add(drugi);
		//donji.add(poslednji);
		this.add(donji);		
	}

	public Broj getBroj() {
		return broj;
	}
	
}
