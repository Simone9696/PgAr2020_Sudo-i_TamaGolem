package it.unibs.ing.fp.arnaldo.tamagolem;

import java.util.HashMap;
import java.util.Map;

public class Battle {
	
	private static Player playerOne = new Player();
	private static Player playerTwo = new Player();
	
	private static int P = 0;
	
	private static Map<Elements, Integer> disposableRocks = new HashMap<Elements, Integer>();
	
	public static void initializeRockStock() {
		P = (int) (Math.ceil(2*Player.getMaxGolems()*Golem.getMaxRocks()/Equilibrium.getN()) * Equilibrium.getN());
	}
	
	private static void setDisposableRocks() {
		int rockNumberEachType = P / Equilibrium.getN();
		for (Elements elem : Elements.values()) {
			disposableRocks.put(elem, rockNumberEachType);
		}
	}
	
	public static void printDisposableRocks() {
		System.out.println("TIPO ROCCIA\tQUANTITA' DISPONIBILE\n");
		for (Elements elem : disposableRocks.keySet()) {
			System.out.println(elem.toString() + (elem.toString().length() > 7 ? "\t" : "\t\t") + disposableRocks.get(elem));
		}
	}
	
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
	
	public static void battle() {
		
		Utility.battleIntro();
		
		setDisposableRocks();
		
		Equilibrium.newEquilibrium();
		
		Equilibrium.print();
		
		Utility.setPlayer(playerOne);
		Utility.setPlayer(playerTwo);
		Golem golemOne = playerOne.evocateGolem();
		Golem golemTwo = playerTwo.evocateGolem();
		while (!playerOne.isDefeated() && !playerTwo.isDefeated() && areThereStillRocks()) {
			
			while (!golemOne.isDead() && !golemTwo.isDead()) {
				ElementRock rockOne = golemOne.throwRock(playerOne);
				ElementRock rockTwo = golemTwo.throwRock(playerTwo);
				int result = Equilibrium.calculateInteraction(rockOne, rockTwo);
				if (result < 0) {
					golemOne.setLife(golemOne.getLife() + result);
				} else {
					golemTwo.setLife(golemTwo.getLife() - result);
				}
				Utility.printDemage(result, rockOne, rockTwo);
				Utility.printStatus(playerOne, playerTwo);
			}
			
			if (golemOne.isDead()) { // sono sicuro che non muoiono mai contemporaneamente
				golemOne = playerOne.evocateGolem();
			} else {
				golemTwo = playerTwo.evocateGolem();
			}
		}
		
		if (!areThereStillRocks()) {
			Utility.finishedRocks();
			return;
		}
		
		if (playerOne.isDefeated()) {
			Utility.winner(playerTwo);
		} else {
			Utility.winner(playerOne);
		}
		
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
	
	

}
