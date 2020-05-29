package it.unibs.ing.fp.arnaldo.tamagolem;

public class Player {
	
	private static int MAX_GOLEMS = 0;
	
	private String name;
	private boolean defeated = false;
	private int evocatedGolems = 1;
	private Golem myGolem;
	private char ensign;
	

	public Player(String name, char ensign) {
		
		setName(name);
		setEnsign(ensign);
	}

	/**
	 * Sets the maximum Golems a player can evocate
	 */
	public static void initializeMaxGolems() {
		MAX_GOLEMS = (int) Math.ceil((double)(Equilibrium.getN() - 1)*(Equilibrium.getN() - 2)/(2 * Golem.getMaxRocks()));
	}
	
	public boolean isDefeated() {
		return defeated;
		
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

	/**
	 * Evocates a new Golem
	 * @return the Golem evocated
	 */
	public Golem evocateGolem() {
		
		
		evocatedGolems++;
		
		if (evocatedGolems <= MAX_GOLEMS) {
			
			Utility.evocateGolemIntro();
			myGolem = Utility.firstEvoGolem();
			
		}
		if (evocatedGolems > MAX_GOLEMS) setDefeated(true); // da sistemare: non deve far generare il golem e poi dire subito che il giocatore � morto
		return myGolem;
		
	}


	
	

	public static int getMaxGolems() {
		return MAX_GOLEMS;
	}

	public Golem getMyGolem() {
		return myGolem;
	}

	public Golem setMyGolem(Golem myGolem) {
		return this.myGolem = myGolem;
	}
	
	public char getEnsign() {
		return ensign;
	}

	public void setEnsign(char _ensign) {
		ensign = _ensign;
	}
	public String toString() {
		StringBuilder cadet = new StringBuilder();
		cadet.append(String.format("username: ") + name + "\n");
		cadet.append(String.format("golem schierato: ") + myGolem.getNameGolem() + "\n");
		cadet.append("energie:\n");
		for (int i = 0; i < myGolem.rocks.size(); i++) {
			cadet.append(String.format(" ") + myGolem.rocks.get(i).toString() + " ");
		}
		cadet.append("\npunti vita golem :" + myGolem.getLife());
		return cadet.toString();
}
	
	}
