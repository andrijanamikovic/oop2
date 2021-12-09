package Igra;

public class Generator {
	
	private int broj;

	public Generator(int min, int max) {
		broj = (int) (min + Math.random()*(max-min));
	}

	public int getBroj() {
		return broj;
	}
	
	
}
