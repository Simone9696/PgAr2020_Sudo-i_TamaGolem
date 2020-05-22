package it.unibs.ing.fp.arnaldo.tamagolem;

import java.util.ArrayList;

public class Golem {
	
	private boolean dead = false;

	ArrayList<ElementRock> rocks = new ArrayList<ElementRock>();
	
	private static final int MAX_ROCKS = P;
	
	private int life = V;
	
	public boolean isDead() {
		
	}
	
	public ElementRock throwRock() {
		
	}
	
	/**number rock in golem * non sapevo se fare cosi o diverso boh in realtà dovrei fare due arraylist una dell
	 * quantità P cioè pietre nel Golem e sta qui poi ci sarebbero le  Scorte di S sempre calcolate su tutte le pietr  
	 * pero chiedo simone perchè forse in Player in svocazione sta cosa quindi inutile poi comuque ci sarebbe la scelta delle pietr
	 * quindi forse solo il massimo prietre e sono fuori strada */
	
	
	public void eatRocks(ElementRock rock) {
	   for (int i = 0; i <listaRockSize() ; i++) { 
		   rocks.add(i, rock);
		 
		
		   
		   
	}
		
	}
	/**quantity of rocks available in the tamagolem for each single evocation
	 * P=⎡(N + 1) / 3⎤ + 1.  N= number of element 
	 * @param rocksPower 
	 * @return
	 */
	public int listaRockSize() {
	  int rocksPower = Utility.balanceOfWorld(rocksPower);
	  int elementRockSize = Math.round((rocksPower+1)/3)+1;
	}
	
	/**calculate life by:
	 * 
	 * @return
	 */

	
}
