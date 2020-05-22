package it.unibs.ing.fp.arnaldo.tamagolem;

import java.util.ArrayList;

public class Battle {
	
	private static Player playerOne;
	private static Player playerTwo;
	
	private ArrayList<ElementRock> disposableRocks = new ArrayList<ElementRock>();
	
	public static void battle() {
		Golem
		 golemOne = playerOne.evocateGolem();
		Golem golemTwo = playerTwo.evocateGolem();
		while (!golemOne.isDead() && !golemTwo.isDead()) {
			ElementRock rockOne = golemOne.throwRock();
			ElementRock rockTwo = golemTwo.throwRock();
			Equilibrium.calculateInteraction(rockOne, rockTwo);
			
		}
	}
	
	

}
