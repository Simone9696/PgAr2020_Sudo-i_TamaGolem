package it.unibs.ing.fp.arnaldo.tamagolem;

import it.unibs.fp.mylib.InputDati;

public class UIO {
	
	private final static String frame = "********************************************************";
	private final static String MESS_GOODBYE = "\n ARRIVEDERCI \n  ";
	private final static String MESS_WELCOME = "            SEI ENTRATO NELLA ARENA  \n       terreno di scontro dei TAMAGOLEM  \n   sarai abbastanza scaltro da comprendere \n   l'equilibrio delle forze che governano l'universo?";


	/**
	 * Greets players, initializes programm variables and let two users play a game
	 */
	public static void golemIntro() {
		
		printWelcome();
		
		System.out.println("Come primo sfindante clandestino ti è concesso di scegliere il livello della sfida");
	
		int n = -1;
		do {
			if (InputDati.yesOrNo("Sei un allievo ancora inetto ? Rifletti bene prima di rispondere no"))
				Utility.beginnerAdvise();
			else {
				do {
					n = Utility.setElementAdvance();
					if (n > 0)
						Equilibrium.setMatrix(n);
				} while (n == -2);
			} 
		} while (n == 0);
		
		System.out.println();
		
		Golem.initializeMaxRocks();
		Player.initializeMaxGolems();
		Battle.initializeRockStock();
		
		Battle.battle();
		
		printGoodbye();
	}
	
	
	/**
	* Prints welcome message
	*/
	public static void printWelcome() {
		System.out.println(frame);
		System.out.println(MESS_WELCOME);
		System.out.println(frame);
	}

	/**
	 * Prints a goodbye message
	 */
	public static void printGoodbye() {
		System.out.println(frame);
		System.out.println(MESS_GOODBYE);
		System.out.println(frame);

	}
}
