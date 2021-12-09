package Telefoni;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class Stavka extends Panel{
	private Label natpis1 = new Label();
	private Label natpis2 = new Label();
	
	public Stavka(String natpis1, String natpis2) {
		this.natpis1.setFont(new Font("Arial",Font.BOLD,this.getHeight()/3));
		this.natpis1.setText(natpis1);
		this.natpis2.setText(natpis2);
		populateWindow();
	}

	private void populateWindow() {
		setLayout(new GridLayout(2,1));
		add(natpis1);
		add(natpis2);
	}

	public void setNatpis1(String natpis1) {
		this.natpis1.setText(natpis1);
		revalidate();
	}
}
