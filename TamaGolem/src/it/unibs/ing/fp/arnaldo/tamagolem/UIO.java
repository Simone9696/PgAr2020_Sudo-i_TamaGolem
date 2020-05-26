package it.unibs.ing.fp.arnaldo.tamagolem;

import it.unibs.fp.mylib.InputDati;

public class UIO {
	
	private final static String frame = "********************************************************";
	private final static String MESS_GOODBYE = "\n ARRIVEDERCI \n  ";
	private final static String MESS_WELCOME = "            SEI ENTRATO NELLA ARENA  \n       terreno di scontro dei TAMAGOLEM  \n   sarai abbastanza scaltro da comprendere \n   l'equilibrio delle forze che governano l'universo?";


	
	
	



	public static void golemIntro() {
		
		printWelcome();
		System.out.println("come primo sfindante clandestino ti è concesso di scegliere il livello della sfida");
		if (InputDati.yesOrNo("sei un allievo ancora inetto ? rifletti bene prima di rispondere no"))
        Utility.setNforBeginners();
		else 
		Utility.setElementAdvance();
		System.out.println("");
		Golem.initializeMaxRocks();
		Player.initializeMaxGolems();
		Battle.initializeRockStock();
		Battle.battle();
		System.exit(0);
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
