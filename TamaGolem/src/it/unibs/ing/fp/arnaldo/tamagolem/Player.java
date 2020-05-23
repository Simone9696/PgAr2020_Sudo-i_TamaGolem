package it.unibs.ing.fp.arnaldo.tamagolem;

public class Player {
	
	private static int MAX_GOLEMS = 0;
	
	private String name;
	private boolean defeated = false;
	private int evocatedGolems = 0;
	private Golem myGolem;
	
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

	public Golem evocateGolem() {
		
		
		evocatedGolems++;
		
		if (evocatedGolems <= MAX_GOLEMS) {
			Utility.evocateGolemIntro();
			myGolem = new Golem();
			myGolem.addRocks();
			
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

	public void setMyGolem(Golem myGolem) {
		this.myGolem = myGolem;
	}
	
	
	

}
