package Sladoled;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Mesto extends Canvas implements Runnable {
	
	private Sladoled sladoled;
	private Aparat aparat; 
	private long sleep = 500;
	private static Thread nit = new Thread();
	private int height;
	private boolean toci;
	private double zapremina = 0;
	private ArrayList<Ukus> crtanje = new  ArrayList<Ukus>();
	private boolean clear = false;
	private Ukus trenutni;
	private static final int ukupna = 200;
	
	public Mesto(Aparat a) {
		aparat = a;
	}

	public Sladoled getSladoled() {
		return sladoled;
	}
	
	@Override
	public void paint(Graphics g) {
		if (clear) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		else {
		height = getHeight();
		int korak = height /10;
		int poc = height - korak;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (crtanje!=null)
			for (Ukus u:crtanje) {
				g.setColor(u.getBoja());
				g.fillRect(0, poc, getWidth(), korak);
				poc -=korak;
			}
		}
		if (sladoled!=null) {
			aparat.setLabelText(sladoled.toString());
		}
		else {
			aparat.setLabelText("");
		}
		revalidate();
	}


	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (!toci) {
						wait();
					}
				}
				Thread.sleep(sleep);
				if (zapremina <= sladoled.zapremina + 20) {									//!!!
					sladoled.dodaj(20, trenutni);
					crtanje.add(trenutni);
					zapremina+=20;
				}
				if (zapremina >= sladoled.zapremina) {
					aparat.prodaj();
				}
				
				repaint();
			}
		}
		catch (InterruptedException e) {
		}
		nit = null;
	}
	
	public synchronized void zaustavi() {
		toci = false;
	}
	
	public synchronized void nastavi() {
		toci = true;
		synchronized (this) {
			notify();
		}
	}
	
	public synchronized void zavrsi(){
		toci = false;
		if (nit!=null)
			nit.interrupt();
		nit = null;
		crtanje = null;
		clear = true;
		sladoled = null;
		//sladoled = new Sladoled(200);
		zapremina = 0;
		repaint();
	}

	public synchronized boolean uToku() {
		return toci;
	}
	
	public void postaviUkusu(Ukus u) {
		trenutni = u;
		
	}
	
	public synchronized void pokreni() {
		zavrsi();
		clear = false;
		crtanje = new ArrayList<Ukus>();
		toci = true;
		sladoled = new Sladoled(ukupna);
		nit = new Thread(this);
		nit.start();
	}
	
	/*private Ukus u;
	private double v;
	public boolean smanji() {
		if (crtanje.size()==0) {zavrsi(); return true;}
		u = crtanje.get(crtanje.size()-1);
		crtanje.remove(crtanje.size()-1);
		zapremina -= ukupna/10;
		for (Ukus u2:sladoled.sladoledi.keySet()) {
			if (u.equals(u2)) {
				v = sladoled.sladoledi.get(u2);
				System.out.println("zapremina: "+v+"\n");
				v -= ukupna/10;
				sladoled.sladoledi.remove(u2);
				break;
				}
		}
		if (v>0) {
			sladoled.sladoledi.put(u, v);
		}
		System.out.println("\n\n"+sladoled.sladoledi.toString());
		repaint();
		return false;
	}*/

}
