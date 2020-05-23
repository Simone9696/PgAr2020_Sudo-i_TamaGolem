package it.unibs.ing.fp.arnaldo.tamagolem;

public enum Elements {

	ROCCIA(0),
	FUOCO(1),
	ARIA(2),
	ACQUA(3),
	ERBA(4),
	MONTAGNA(5),
	TENEBRA(6),
	SPETTRO(7),
	SCINTILLA(8),
	ACCIAIO(9);
	
	private int id;
	
	private Elements(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public static Elements getElement(int id) {
		for (Elements elem : Elements.values()) {
			if (elem.id == id) return elem;
		} return null;
	}
	
	public static String[] getElementsArray() {
		String[] str = new String[Equilibrium.getN()];
		int i = 0;
		for (Elements elem : Elements.values()) {
			if (i < Equilibrium.getN()) {
				str[i] = elem.toString();
				i++;
			}
		}
		return str;
	}
	
}
