package it.unibs.ing.fp.arnaldo.tamagolem;

import java.util.HashMap;
import java.util.Map;

public class Battle {
	
	private static Player playerOne;
	private static Player playerTwo;
	
	private static int P = 0;
	
	private static Map<Elements, Integer> disposableRocks = new HashMap<Elements, Integer>();
	
	public static void initializeRockStock() {
		P = (int) (Math.ceil((double) 2*Player.getMaxGolems()*Golem.getMaxRocks()/Equilibrium.getN()) * Equilibrium.getN());
	}
	
	private static void setDisposableRocks() {
		int rockNumberEachType = P / Equilibrium.getN();
		for (Elements elem : Elements.values()) {
			if (elem.getId() < Equilibrium.getN()) {
				disposableRocks.put(elem, rockNumberEachType);
			}
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
		
		while (!Equilibrium.newEquilibrium());
			
		Equilibrium.print();
		
		playerOne = new Player();
		playerTwo = new Player();
		
		Utility.setPlayer(playerOne);
		Utility.setPlayer(playerTwo);
		Utility.turn(playerOne);
		Golem golemOne = playerOne.evocateGolem();
		Utility.turn(playerTwo);
		Golem golemTwo = playerTwo.evocateGolem();
		while (!playerOne.isDefeated() && !playerTwo.isDefeated()) {
			
			if (golemOne.rocks.equals(golemTwo.rocks)) {
				Utility.tie();
				return;
			}
			
			while (!golemOne.isDead() && !golemTwo.isDead()) {

				Utility.push();
				ElementRock rockOne = golemOne.throwRock(playerOne);
				ElementRock rockTwo = golemTwo.throwRock(playerTwo);
				int result = Equilibrium.calculateInteraction(rockOne, rockTwo);
				if (result < 0) {
					golemOne.setLife(golemOne.getLife() + result);
				} else {
					golemTwo.setLife(golemTwo.getLife() - result);
				}
				if (golemOne.getLife() != 0 && golemTwo.getLife() != 0) {
					Utility.printDemage(result, rockOne, rockTwo);
				} else if (golemOne.getLife() == 0) Utility.golemDead(playerOne);
				else Utility.golemDead(playerTwo);
				
				Utility.printStatus(playerOne, playerTwo);
				
			}
			
			if (golemOne.isDead()) { // sono sicuro che non muoiono mai contemporaneamente
				Utility.turn(playerOne);
				golemOne = playerOne.evocateGolem();
			} else {
				Utility.turn(playerTwo);
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
