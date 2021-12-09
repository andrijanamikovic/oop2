package Igra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class Polje extends Canvas {
	private Mreza owner;
	protected int broj;
	private Label natpis;
	protected Status status = new Status();
	private int height = 75;
	private int width = 75;
	
	protected class Status{
		private String status = "SLOBODNO";
		public Status() {}
		public Status(String status) {
			if (status.equals("SLOBODNO") || status.equals("IZABRANO")) {
				this.status = status;
			}
		}
		public String getStatus() {
			return status;
		}	
	}

	
	public Polje(Mreza owner, int broj) {
		super();
		this.broj = broj;
		this.owner = owner;
		Label natpis = new Label(""+broj);
		Polje p = this;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (status.getStatus().equals("SLOBODNO")) { 
					status = new Status("IZABRANO");
					owner.promenjenStatus(p);
				}
				else {
					status = new Status("SLOBODNO");
					owner.promenjenStatus(p);
				}
				repaint();
			}
		});
	}
	
	private void crtajBroj(Color boja) {
		Graphics g = this.getGraphics();
		g.setColor(boja);
		int fontSize = (int)( Math.min(height, width)/3);
		g.setFont(new Font("Comic Sans MS",Font.PLAIN,fontSize));
		String text = "";
		text+=broj;
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		int velicina = (int)((getWidth() < getHeight() ? getWidth() : getHeight()) /3);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, velicina));
		FontMetrics font = g.getFontMetrics();
		int x = (getWidth() - font.stringWidth(text)) / 2;
		int y = ((getHeight() - font.getHeight()) / 2) + font.getAscent();
		g.drawString(text, x, y);
	}

	@Override
	public void paint(Graphics g) {
		setBackground(Color.orange);
		Color PrevColor = g.getColor();
		if (this.status.getStatus().equals("SLOBODNO")) {
			crtajBroj(Color.black);
			}
		else {
			g.setColor(Color.BLUE);
			g.fillOval(0, 0, getWidth(), getHeight());
			crtajBroj(Color.white);
		}
		
		g.setColor(PrevColor);
	}
	public Label getNatpis() {
		return natpis;
	}
	
	
	
	
	
}
