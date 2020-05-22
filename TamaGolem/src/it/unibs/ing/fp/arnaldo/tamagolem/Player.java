package it.unibs.ing.fp.arnaldo.tamagolem;

import java.util.ArrayList;

public class Player {
	


	private static final int MAX_GOLEMS = G;
	
	private String name;
	private boolean defeated = false;
	private int evocatedGolems = 0;
	private Golem myGolem;
	
	
	
	
	
	
	
	
	
	public void throwRock() {
		
	}
	
	public boolean isDefeated() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDefeated(boolean defeated) {
		this.defeated = defeated;
	}

	public Golem evocateGolem() {
		
		while (evocatedGolems < MAX_GOLEMS) {
			myGolem = new Golem();
			myGolem.addRocks();
		}
		evocatedGolems++;
		
	}

}
