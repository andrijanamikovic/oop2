package Telefoni;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Telefoni.Tastatura.Rezim;

public class Telefoni extends Frame {
	private Telefon telefon;
	private Telefon telefon2;
	//private Tastatura tastatura;
	private Panel levi = new Panel();
	private Panel desni = new Panel();
	
	public Telefoni() {
		setBounds(700, 200, 500, 300);
		setResizable(true);
		setTitle("Telefoni");
		//tastatura.populateWindow();
		char[] c = {'+','3','8','1','6','1','1','1','6','6','7','0','1'};
		Broj b1 = new Broj(c);
		telefon = new Telefon(b1, Color.GREEN);
		char[] c2 = {'+','3','8','1','5','6','7','9','6','6','7','0','1'};
		Broj b2 = new Broj(c2);
		telefon2 = new Telefon(b1, Color.YELLOW);
		populateWindow();
		pack();
		revalidate();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setVisible(true);
	}
	
	
	private void populateWindow() {
		this.setLayout(new GridLayout(1,2));
		levi.setLayout(new GridLayout(1,2));
		//tastatura = new Tastatura(new Label());
		//tastatura.setRezim(Rezim.DRUGI);
		//levi.add(tastatura);
		levi.add(telefon);
		//levi.add(levi)
		this.add(levi);
		desni.setLayout(new GridLayout(1,2));
		telefon2.tastatura.setRezim(Rezim.DRUGI);
		desni.add(telefon2);
		this.add(desni);
	}


	public static void main(String[] args) {
		new Telefoni();
	}

}
