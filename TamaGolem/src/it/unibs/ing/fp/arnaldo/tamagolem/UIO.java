package it.unibs.ing.fp.arnaldo.tamagolem;

import it.unibs.fp.mylib.InputDati;

public class UIO {
	
	private final static String frame = "********************************************************";
	private final static String MESS_GOODBYE = "\n TORNA IN ARENA \n  ";
	private final static String MESS_WELCOME = "            SEI ENTRATO NELLA ARENA  \n       terreno di scontro dei TAMAGOLEM  \n   sarai abbastanza scaltro da comprendere \n   l'equilibrio delle forze che governano l'universo?";


	/**
	 * Greets players, initializes programm variables and let two users play a game
	 */
	public static void golemIntro() {
		 
		printWelcome();
		
		boolean primo = true;
		
		do {
			System.out.println("Come primo sfindante clandestino ti � concesso di scegliere il livello della sfida");
			int n = -1;
			do {

				if (primo && InputDati.yesOrNo("Sei un allievo ancora inetto ? Rifletti bene prima di rispondere no")) // sfrutto il fatto che se primo � false la seconda condizione non viene verificata
					Utility.beginnerAdvise();
				else {
					if (!primo) Utility.noMoreNovel();
					do {
						n = Utility.setElementAdvance();
						if (n > 0)
							Equilibrium.setMatrix(n);
					} while (n == -2);
				} primo = false;
			} while (n == 0);
			System.out.println();
			Golem.initializeMaxRocks();
			Player.initializeMaxGolems();
			Battle.initializeRockStock();
			Battle.battle();
			printGoodbye();
		} while (Utility.newPlay());
		
		printGoodbyefinal();
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
	public static void printGoodbyefinal() {
		System.out.println(frame);
		System.out.println("BUON ALLENAMENTO ARRIVEDERCI");
		System.out.println(frame);

	}
}
