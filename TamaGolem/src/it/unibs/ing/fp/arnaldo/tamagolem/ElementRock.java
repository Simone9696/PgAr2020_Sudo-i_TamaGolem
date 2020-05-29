package it.unibs.ing.fp.arnaldo.tamagolem;

public class ElementRock {
	
	private Elements type;

	public ElementRock(Elements elem) {
		this.type = elem;
	}

	public Elements getType() {
		return type;
	}
	
	public int getTypeId() {
		return type.getId();
	}

	public void setType(Elements type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type.toString();
	}
	
	

}
