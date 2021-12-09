package Sladoled;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Aparat  extends Panel{
	private  Mesto mesto;
	private  Panel ukusi;
	private static ArrayList<Button> ukus = new ArrayList<>();
	private  Button prodaja;
	protected  Label sladoled = new Label();
	private  Panel istok;
	private Akcija osluskivac;
	private int kolona = 1;
	private int vrsta = 1;

	//.......
	//private Button jedi = new Button("Jedi");
	
	class GPostoji extends Exception{
		public GPostoji() {
			super("Ukus vec postoji");
		}
	}
	private class Akcija extends MouseAdapter{
		Aparat a;
		
		public Akcija(Aparat a2) {
			a = a2;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			//System.out.println("Kliknuo si");
			String naziv = ((Button)e.getSource()).getLabel();
			Color boja = ((Button)e.getSource()).getBackground();
			if (mesto.getSladoled()!=null) {
				mesto.nastavi();
			}else{
				mesto.pokreni();
			}
			mesto.postaviUkusu(new Ukus(naziv,boja));
			
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			//System.out.println("Pustio si");
			mesto.zaustavi();
		}
	}
	
	public Aparat() {
		mesto = new Mesto(this);
		setLayout(new GridLayout(1,2));
		setBackground(Color.LIGHT_GRAY);
		ukusi = new Panel();
		//ukusi.setLayout(new GridLayout(0,2));
		add(ukusi);
		prodaja = new Button("Prodaj");
		prodaja.setEnabled(false);
		prodaja.addActionListener((al)->{
			System.out.println(mesto.getSladoled());
			mesto.zavrsi();	//pod komentar ako treba da jede
			prodaja.setEnabled(false);
			/*.......
				jedi.setEnabled(true);
				.....*/
		});
		
		//.......
		/*jedi.setEnabled(false);
		jedi.addActionListener((al)->{
			System.out.println(mesto.getSladoled());
			boolean flag2 = mesto.smanji();
			prodaja.setEnabled(false);
			if (flag2) jedi.setEnabled(false);;
			
		});
		Panel dugmici = new Panel (new GridLayout(1,2));
		dugmici.add(prodaja);
		dugmici.add(jedi);*/
		istok = new Panel(new GridLayout(2,1));
		istok.add(prodaja);
		//istok.add(dugmici);
		istok.add(mesto);
		add(istok);
		osluskivac = new Akcija(this);
	}

	public void setLabelText(String tekst) {
		sladoled.setText(tekst);
	}
	
	public void dodaj(Ukus u) throws GPostoji {
		//System.out.println("Ovo je ok");
		Button novi = new Button(u.getNaziv());
		novi.setBackground(u.getBoja());
		if (ukus.size()!=0)
			{if (ukus.contains(novi)) throw new GPostoji();}
		ukus.add(novi);	
		if (ukusi!=null)
			ukusi.removeAll();
		if (ukus.size()!=0)
			for (Button b:ukus) {
				b.addMouseListener(osluskivac);
				ukusi.add(b);
			}
		if (ukus.size()>kolona*vrsta) {
			if (kolona == vrsta) {
				vrsta++;
			}else {
				kolona++;
			}
		}
		ukusi.setLayout(new GridLayout(vrsta,kolona));
		revalidate();
		
	}
	public void prodaj() {
		prodaja.setEnabled(true);
	}
}
