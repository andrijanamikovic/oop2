package Sladoled;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Sladoled.Aparat.GPostoji;

public class Sladoledzinica extends Frame {
	
	private Aparat aparat;
	private Panel panel;
	private TextField nazivUkusa = new TextField(6);
	private TextField bojaHex =  new TextField(6);;
	Button dodaj = new Button("Dodaj ukus");
	String ukus;
	Color boja;
	
	public Sladoledzinica() {
		setBounds(700, 200, 500, 300);
		setResizable(false);		
		setLayout(new BorderLayout());
		setTitle("Sladoledzinica");
		populateWindow();	
		pack();
		nazivUkusa.addTextListener((tl)-> {
			ukus = nazivUkusa.getText().toString();
		});

		bojaHex.addTextListener((tl)->{
			try {
				boja = new Color(Integer.parseInt(bojaHex.getText().toString(),16));
			}catch(NumberFormatException e) {
				boja = null;
			}
		});
		dodaj.addActionListener((al)->{
			try {
				if (boja!=null)
					aparat.dodaj(new Ukus(ukus, boja));
				//aparat.repaint();
			} catch (GPostoji e1) {}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setVisible(true);
	}
	
	private void populateWindow() {
		aparat = new Aparat();
		add(aparat,BorderLayout.CENTER);
		aparat.setPreferredSize(new Dimension(500, 300));
		Panel SouthPanel = new Panel();
		Label naziv = new Label ("Naziv: ");
		naziv.setFont(new Font("Arial",Font.BOLD,12));
		SouthPanel.setBackground(Color.CYAN);
		SouthPanel.add(naziv);
		SouthPanel.add(nazivUkusa);
		Label boja = new Label ("Boja: ");
		boja.setFont(new Font("Arial",Font.BOLD,12));
		SouthPanel.add(boja);
		SouthPanel.add(bojaHex);
		SouthPanel.add(dodaj);
		Panel moj = new Panel(new GridLayout(2,1) );
		Panel sladoledPanel = new Panel();
		sladoledPanel.setLayout( new FlowLayout(FlowLayout.LEFT));
		Label sladoledLabel = new Label("Sladoled:");
		sladoledLabel.setFont(new Font("Arial",Font.BOLD,12));
		sladoledPanel.add(sladoledLabel);
		sladoledPanel.setBackground(Color.gray);
		sladoledPanel.add(aparat.sladoled);
		moj.add(sladoledPanel);
		moj.add(SouthPanel);
		add(moj,BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		new Sladoledzinica();
	}
}
