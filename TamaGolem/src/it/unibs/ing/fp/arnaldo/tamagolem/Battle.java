package it.unibs.ing.fp.arnaldo.tamagolem;

import java.util.HashMap;
import java.util.Map;

public class Battle {
	
	private static Player playerOne;
	private static Player playerTwo;
	
	private static int P = 0;
	
	private static Map<Elements, Integer> disposableRocks = new HashMap<Elements, Integer>();
	
	private static Golem golemOne;
	private static Golem golemTwo;
	
	/**
	 * Sets the rock stock capacity
	 */
	public static void initializeRockStock() {
		P = (int) (Math.ceil((double) 2*Player.getMaxGolems()*Golem.getMaxRocks()/Equilibrium.getN()) * Equilibrium.getN());
	}
	
	/**
	 * Initializes the common rock stock
	 */
	private static void setDisposableRocks() {
		int rockNumberEachType = P / Equilibrium.getN();
		for (Elements elem : Elements.values()) {
			if (elem.getId() < Equilibrium.getN()) {
				disposableRocks.put(elem, rockNumberEachType);
			}
		}
	}
	
	/**
	 * Prints the available rocks
	 */
	public static void printDisposableRocks() {
		System.out.println("TIPO ROCCIA\tQUANTITA' DISPONIBILE\n");
		for (Elements elem : disposableRocks.keySet()) {
			System.out.println(elem.toString() + (elem.toString().length() > 7 ? "\t" : "\t\t") + disposableRocks.get(elem));
		}
	}
	
	/**
	 * Checks wether rock stock is empthy
	 * @return true if not empthy, false if empthy
	 */
	public static boolean areThereStillRocks() {
		
		boolean empthy = true;
		for (Elements elem : disposableRocks.keySet()) {
			if (disposableRocks.get(elem) > 0) {
				empthy = false;
				break;
			}
		}
		
		return !empthy;
	}
	
	/**
	 * Manages a battle cicle
	 */
	public static void battle() {
		
		Utility.battleIntro(); // stampa intro alla battaglia
		
		setDisposableRocks(); // riempie la scorta comune di rocce
		
		while (!Equilibrium.newEquilibrium()); // genera un nuovo equilibrio
		
		Utility.introPlayer(); // fa impostare ai giocatori i loro nomi
	
        Utility.introGolem(playerOne, playerTwo); // fa evocare ai giocatori i loro primi Golem

		while (!playerOne.isDefeated() && !playerTwo.isDefeated()) { // finché sono entrambi in vita
			
			if (golemOne.rocks.equals(golemTwo.rocks)) { 	// se hanno lanciato le stesse rocce nello stesso ordine 
				Utility.tie();								// finisce in pareggio
				return;
			}
			
			while (!golemOne.isDead() && !golemTwo.isDead()) { // finché entrambi i Golem sono vivi

				Utility.push();
				ElementRock rockOne = golemOne.throwRock(playerOne); // lancia le due rocce
				ElementRock rockTwo = golemTwo.throwRock(playerTwo);
				int result = Equilibrium.calculateInteraction(rockOne, rockTwo); // calcola interazione fra le rocce
				if (result < 0) { // in base al risultato setta la vita del Golem perdente
					golemOne.setLife(golemOne.getLife() + result);
				} else {
					golemTwo.setLife(golemTwo.getLife() - result);
				}
				if (golemOne.getLife() != 0 && golemTwo.getLife() != 0) { // se non è morto stampa il danno
					Utility.printDemage(result, rockOne, rockTwo);
				} else if (golemOne.getLife() == 0) Utility.golemDead(playerOne); // altrimenti stampa chi è morto
				else Utility.golemDead(playerTwo);
				
				Utility.printStatus(playerOne, playerTwo); // stampa lo stato attuale dei Golem e dei giocatori
				
			}
			
			if (golemOne.isDead()) { // sono sicuro che non muoiono mai contemporaneamente
				Utility.turn(playerOne);
				golemOne = playerOne.evocateGolem(); 
			} else {
				Utility.turn(playerTwo);
				golemTwo = playerTwo.evocateGolem();
			}
		}
		
		if (!areThereStillRocks()) { // la partita finisce per mancanza di rocce
			Utility.finishedRocks();
			return;
		}
		
		if (playerOne.isDefeated()) { // stampa chi ha vinto
			Utility.winner(playerTwo);
		} else {
			Utility.winner(playerOne);
		}
		
		Equilibrium.print(); // stampa l'equilibrio
		
	}

	
	
	public static Player getPlayerOne(Player playerOne) {
		return playerOne;
	}

	public static void setPlayerOne(Player playerOne) {
		Battle.playerOne = playerOne;
	}

	public static void setPlayerTwo(Player playerTwo) {
		Battle.playerTwo = playerTwo;
	}

	public static Player getPlayerTwo(Player playerTwo) {
		return playerTwo;
	}

	public static Map<Elements, Integer> getDisposableRocks() {
		return disposableRocks;
	}

	public static void setDisposableRocks(Map<Elements, Integer> disposableRocks) {
		Battle.disposableRocks = disposableRocks;
	}

	public static int getP() {
		return P;
	}

	public static void setGolemOne(Golem golemOne) {
		Battle.golemOne = golemOne;
		// TODO Auto-generated method stub
		
	}
	
	public static void setGolemtwo(Golem golemTwo) {
		Battle.golemTwo = golemTwo;
		// TODO Auto-generated method stub
		
	}
	
	public static Golem getGolemOne() {
		return golemOne;
	}
	
	public static Golem getGolemTwo() {
		return golemTwo;
	}

}
