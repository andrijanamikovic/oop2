package Telefoni;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.awt.Label;

public class Tastatura extends Panel implements Runnable {
	
	public enum Rezim {PRVI,DRUGI};
	Rezim rezim = Rezim.PRVI;
	 Label natpis = new Label();
	 Label ime = new Label();
	private Panel labela = new Panel();
	private Panel buttons = new Panel();
	private ArrayList<Button> niz = new ArrayList<>();
	//protected ArrayList<Button> niz = new ArrayList<>(); ako traze gasenje dugmica na ugasi telefon
	private String pomocni = new String("");
	private String pomocni1 = new String("");
	private Thread nit = new Thread();
	private int cnt = 0;
	private Button trenutno = new Button();
	private Button prethodno = new Button();
	private boolean flag = false;
	private boolean flag2 = false;
	private String old = new String("");
	
	public Tastatura(Label natpis) {
		
		this.natpis = natpis;
		populateWindow();
	}
	
	public void changeRezim () {
		if (this.rezim == Rezim.PRVI) {
			this.rezim = Rezim.DRUGI;
		}else {
			this.rezim = Rezim.PRVI;
		}
		old = ime.getText();
		setButtons();
	}
	
	public void setRezim(Rezim rezim) {
		this.rezim = rezim;
		setButtons();
	}
	
	private void setButtons(){
		//this.remove(buttons);
		//buttons.setLayout(new GridLayout(4,3));
		niz.removeAll(niz);
		buttons.removeAll();
		buttons.revalidate();
		for (int i=0; i < 12; i++) {
			Button novoDugme = new Button();
			if (rezim == Rezim.PRVI) {
				if (i < 9) {
					 novoDugme = new Button(Integer.toString(i+1));
				} else if (i == 9) {
					 novoDugme = new Button("*");
					
				} else if (i == 10) {
					 novoDugme = new Button("0");
				} else {
					 novoDugme = new Button("+");
				}
			} else {
				switch (i){
				case 0: novoDugme = new Button(); break;
				case 1: novoDugme = new Button("ABC"); break;
				case 2: novoDugme = new Button("DEF"); break;
				case 3: novoDugme = new Button("GHI"); break;
				case 4: novoDugme = new Button("JKL"); break;
				case 5: novoDugme = new Button("MNO"); break;
				case 6: novoDugme = new Button("PQRS"); break;
				case 7: novoDugme = new Button("TUV"); break;
				case 8: novoDugme = new Button("WXYZ");break;
				case 9: novoDugme = new Button(); break;
				case 10: novoDugme = new Button("_"); break;
				case 11: novoDugme = new Button(); break;
				}
			}
			niz.add(novoDugme);
			buttons.add(novoDugme);
		}
		Osluskivac();
	}

	private void Osluskivac() {
		for (Button b:niz) {
			b.addActionListener((al)->{
				if (this.rezim == Rezim.PRVI) {
					pomocni1 = natpis.getText()+b.getLabel();
					natpis.setText(pomocni1);
				} else {
					prethodno = trenutno;
					trenutno = b;
					if (nit!=null) {
						synchronized (this) {
							nit.interrupt();
						}
						//prethodno = trenutno;
					}
					flag = true;
					nit = null;
					nit = new Thread(this);
					trenutno = b;
					nit.start();
				}
			});
		}
		add(buttons, BorderLayout.CENTER);
		revalidate();
	}
	public void populateWindow() {
		//this.setBackground(Color.GREEN);
		this.setLayout(new BorderLayout());
		labela.setLayout(new GridLayout(2,1));
		labela.add(natpis);
		labela.add(ime);
		add(labela, BorderLayout.NORTH);
		buttons.setLayout(new GridLayout(4,3));
		for (int i=0; i < 12; i++) {
			Button novoDugme = new Button();
			if (rezim == Rezim.PRVI) {
				if (i < 9) {
					 novoDugme = new Button(Integer.toString(i+1));
				} else if (i == 9) {
					 novoDugme = new Button("*");
					
				} else if (i == 10) {
					 novoDugme = new Button("0");
				} else {
					 novoDugme = new Button("+");
				}
			} else {
				switch (i){
				case 0: novoDugme = new Button();
				case 1: novoDugme = new Button("ABC");
				case 2: novoDugme = new Button("DEF");
				case 3: novoDugme = new Button("GHI");
				case 4: novoDugme = new Button("JKL");
				case 5: novoDugme = new Button("MNO");
				case 6: novoDugme = new Button("PQRS");
				case 7: novoDugme = new Button("TUV");
				case 8: novoDugme = new Button("WXYZ");
				case 9: novoDugme = new Button();
				case 10: novoDugme = new Button("_");
				case 11: novoDugme = new Button();
				}
			}
			niz.add(novoDugme);
			buttons.add(novoDugme);
		}
		Osluskivac();
	}


	@Override
	public void run() {
		try {
			//System.out.println("U niti sam");
			if (trenutno.getLabel().length()!=0)
				printNit(old + trenutno.getLabel().toCharArray()[cnt%trenutno.getLabel().length()]);
			Thread.sleep(1000);
			if (!flag2) {
				old += trenutno.getLabel().toCharArray()[cnt%trenutno.getLabel().length()];
			} else {
				old = ime.getText();
					
			}
			printNit(old);
			nit = null;
			cnt = 0;
				
		} catch (InterruptedException e) {
			//System.out.println(""+cnt);
				if (!prethodno.equals(trenutno)) {
					if (prethodno.getLabel().length()!=0) {
						if (cnt!=0)
							old+= prethodno.getLabel().toCharArray()[(cnt-1)%prethodno.getLabel().length()];
						else {
							old+= prethodno.getLabel().toCharArray()[0];
							flag2 = false;
						}
						cnt = 0;
					}
				} else if (trenutno.getLabel().length()!=0) {
					printNit(old + trenutno.getLabel().toCharArray()[cnt%trenutno.getLabel().length()]);
					cnt++;
				}
			synchronized (this) {
				nit = null;
				flag2 = true;
			}
		}
		//System.out.println(cnt);
		revalidate();
	}
	
	
	private void printNit(String s) {
		flag = false;
		flag2 = true;
		ime.setText(s);
	}
}
