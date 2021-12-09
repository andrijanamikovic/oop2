package Igra;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Igra extends Frame {
	private Mreza mreza = new Mreza(this);
	private int ulog;
	private Button igrajButton = new Button("Igraj");
	private double kvota;
	private double dobitak;
	private double balans;
	private TextField ulogField;
	private Label KvotaLabel2;
	private Label Dobitak;
	Label kolicinaBalansa;
//	private ArrayList<Integer> slucajni = new ArrayList<>();
	
	
	
	public Mreza getMreza() {
		return mreza;
	}



	private Panel upravljanje = new Panel();
	private Panel donji = new Panel();
	private Label donja = new Label();
	
	public Igra() {
		setBounds(700, 200, 500, 500);
		setResizable(true);		
		setLayout(new BorderLayout());
		setTitle("Igra");
		upravljanje.setBackground(Color.gray);
		donji.setBackground(Color.DARK_GRAY);
		populateWindow();	
		pack();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		ulogField.addTextListener((tl)->{
			try {
			ulog = Integer.parseInt(ulogField.getText());
			} catch(NumberFormatException e) {}
			
		});
		setVisible(true);
		igrajButton.setEnabled(false);
		igrajButton.addActionListener((al)->{
			int sluc = new Generator(0,mreza.height*mreza.width-1).getBroj();
			//slucajni.add(sluc); samo dodam na panel?
			setDonji(sluc);
			//..........
			//new Proba();
		});
	}
	
	
	
	private void populateWindow() {
		setUpravljanje();
		//setDonji();
		int width = (2*getWidth()/3) / mreza.width * mreza.width;
		mreza.setPreferredSize(new Dimension(getHeight(), width));
		add(mreza,BorderLayout.CENTER);
		add(upravljanje,BorderLayout.EAST);
		add(donji,BorderLayout.SOUTH);
		
	}



	private void setDonji(int sluc) {
		String text = donja.getText()+" "+sluc;
		if (mreza.getSkupCelih().contains(sluc)) {
			donji.setBackground(Color.green);
			balans+=dobitak;
		}
		else {
			donji.setBackground(Color.red);
			balans-=dobitak;
		}
		kolicinaBalansa.setText(String.format("%.2f",balans));
		donja.setText(text);
		donji.add(donja);
		revalidate();
	}



	private void setUpravljanje() {
		upravljanje.setLayout(new GridLayout(0, 1));
		Label balans = new Label("Balans");
		Panel balansPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
		kolicinaBalansa = new Label("0");
		balansPanel.add(balans);
		balansPanel.add(kolicinaBalansa);
		//Panel kolicinaBalansaPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
		//kolicinaBalansaPanel.add(kolicinaBalansa);
		Label UlogLabel = new Label("Ulog");
		ulogField = new TextField(5);
		//Panel textField = new Panel();
		Panel ulogL = new Panel(new FlowLayout(FlowLayout.LEFT));
		ulogL.add(UlogLabel,FlowLayout.LEFT);
		//textField.add(ulogField);
		ulogL.add(ulogField);
		Label KvotaLabel = new Label("Kvota");
		kvota = Math.round(kvota *100.0)/100.0;
		KvotaLabel2 = new Label(""+kvota);
		Panel panelZKvotu = new Panel(new FlowLayout(FlowLayout.LEFT));
		panelZKvotu.add(KvotaLabel);
		panelZKvotu.add(KvotaLabel2);
		Label DobitakLabel = new Label("Dobitak");
		Dobitak = new Label(""+dobitak);
		Panel DobitakPanel =  new Panel(new FlowLayout(FlowLayout.LEFT));
		DobitakPanel.add(DobitakLabel);
		DobitakPanel.add(Dobitak);
		Panel Igraj = new Panel(new FlowLayout(FlowLayout.RIGHT));
		Panel prazan  = new Panel();
		Igraj.add(igrajButton);
		upravljanje.add(balansPanel);
		//upravljanje.add(kolicinaBalansaPanel);
		upravljanje.add(ulogL);
		//upravljanje.add(textField);
		upravljanje.add(panelZKvotu);
		upravljanje.add(DobitakPanel);
		upravljanje.add(prazan);
		upravljanje.add(Igraj);
	}
	
	public void igrajEnable() {
		igrajButton.setEnabled(true);
		if (mreza.getSkupCelih().size()!=0)
			kvota = 1.0*(mreza.height*mreza.width)/mreza.getSkupCelih().size();
		else {
			kvota = 1.0*mreza.height*mreza.width;
		}
		
		dobitak = 1.0*kvota * ulog;
		Dobitak.setText(String.format("%.2f",dobitak));
		KvotaLabel2.setText(String.format("%.2f",kvota));
		
		revalidate();
	}

	public static void main(String[] args) {
		new Igra();
	}
	
	/*private class Proba implements Runnable{
		private Thread nit;
		private int cnt = 7;
		public Proba() {
			pokreni();
		}
		@Override
		public void run() {
			while (cnt>0) {
				try {
					nit.sleep(1000);
					cnt--;
					int sluc = new Generator(0,mreza.height*mreza.width-1).getBroj();
					//slucajni.add(sluc); samo dodam na panel?
					setDonji(sluc);
				} catch (InterruptedException e) {}
				revalidate();
			}
			zavrsi();
		}
		public synchronized void zavrsi() {
			if (nit!=null)
				nit.interrupt();
			nit = null;
			cnt = 7;
		}
		
		public synchronized void pokreni() {
			zavrsi();
			nit = new Thread(this);
			nit.start();
		}
		
	}*/
}

