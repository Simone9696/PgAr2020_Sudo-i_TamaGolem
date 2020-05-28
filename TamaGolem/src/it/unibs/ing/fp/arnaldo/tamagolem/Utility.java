package it.unibs.ing.fp.arnaldo.tamagolem;


import it.unibs.fp.mylib.EstrazioniCasuali;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;
import util.mylib.diSG.BelleStringhe;

public class Utility {

	public static final boolean elementForBeginner = false;
	
	/**
	 * Guides player in choosing a rock from the set of available rocks
	 * @return the rock chosen
	 */
	public static ElementRock chooseRock() {
		System.out.println("Scegli una roccia fra quelle disponibili.\n");
		Battle.printDisposableRocks();
		
		String voci[] = Elements.getElementsArray();
		MyMenu menu = new MyMenu("Digita il numero corrispondente alla roccia da scegliere", voci);
		int chosen = -1;
		do {
			chosen = menu.scegli();
		} while (chosen == 0);
		return new ElementRock(Elements.getElement(chosen - 1));
	}

	/**
	 * Prompts user to choose another rock
	 */
	public static void chooseAnotherRock() { // avvisa utente che la roccia scelta non è disponibile
		System.out.println();
		System.out.println("Roccia non piu' disponibile\n");
		
	}

	/**
	 * Prompts user to the rocks-choosing procedure
	 */
	public static void addRocksIntro() { // avvisa utente che sta per scegliere le rocce da dare al golem
		System.out.println();
		System.out.println("Scegli le rocce da dare al tuo TamaGolem\n");
		System.out.println("Devi scegliere " + Golem.getMaxRocks() + " rocce");
		
	}

	/**
	 * Prompts user on evocating a new Golem
	 */
	public static void evocateGolemIntro() { // avvisa utente che sta per evocare un golem
		System.out.println();
		System.out.println("Evoca un nuovo TamaGolem:\n");
		
	}

	/**
	 * Prints a intoduction to the battle
	 */
	public static void battleIntro() { // avvisa utenti che stanno per iniziare una partita
		System.out.println();
		System.out.println("FORZE MITI E DISTRUTTIVE SONO IN GIOCO, LA BATTAGLIA INCOMBE:\n");	
		
	}

	/**
	 * Prints a message about the rock thrown
	 * @param elementRock the rock throw
	 * @param player the player whose Golem has thrown the rock
	 */
	public static void throwRockIntro(ElementRock elementRock, Player player) { // avvisa utente che sta per lanciare una roccia
		System.out.println();
		System.out.println(player.getName() + " lancia una roccia di tipo " + elementRock.toString().toUpperCase());
		
	}

	/**
	 * Warns users that there are no more rocks available
	 */
	public static void finishedRocks() { // avvisa che le rocce a disposizione sono finite
		System.out.println();
		System.out.println("Le rocce presenti nella scorta sono terminate\n");
		
	}

	/**
	 * Prints the winning player
	 * @param player the winning player
	 */
	public static void winner(Player player) { // stampa il vincitore
		System.out.println();
		System.out.println(player.getName() + " ha vinto la partita!\n");
		
	}

	/**
	 * Asks users if they want to play a new game
	 * @return true if yes, false otherwise
	 */
	public static boolean newPlay() {
		System.out.println();
		return InputDati.yesOrNo("Vuoi fare un'altra partita? ");
	}

	/**
	 * Sets player name
	 * @param player the player whom to set the name
	 */
	public static void setPlayer(Player player) { // fa aggiungere un nome al giocatore
		System.out.println();
		player.setName(InputDati.leggiStringa("iserisci Username "));
	}

	/**
	 * Prints the damage inflicted from one rock to the other
	 * @param result the damage inflicted (can be negative)
	 * @param rockOne the first rock
	 * @param rockTwo the second rock
	 */
	public static void printDemage(int result, ElementRock rockOne, ElementRock rockTwo) {
		System.out.println();
		if (result < 0) {
			System.out.println(rockTwo.toString() + " infligge " + Math.abs(result) + " danni a " + rockOne.toString());
		} else {
			System.out.println(rockOne.toString() + " infligge " + Math.abs(result) + " danni a " + rockTwo.toString());
		}
	}

	/**
	 * Prints the current status of the players
	 * @param playerOne first player
	 * @param playerTwo second player
	 */
	public static void printStatus(Player playerOne, Player playerTwo) {
		System.out.println();
		String str = new String(playerOne.getName() + "\t-->\tVita TamaGolem: " + playerOne.getMyGolem().getLife());
		System.out.println(BelleStringhe.incorniciaCentrato(str, 40, playerOne.getEnsign()));
		str = playerTwo.getName() + "\t-->\tVita TamaGolem: " + playerTwo.getMyGolem().getLife();
		System.out.println();
		System.out.println(BelleStringhe.incorniciaCentrato(str, 40, playerTwo.getEnsign()));
	}

	/**
	 * Warns players that a Golem is dead
	 * @param player the player whose Golem is dead
	 */
	public static void golemDead(Player player) {
		System.out.println();
		System.out.println("Il TamaGolem di " + player.getName() + " e' morto\n");
		
	}

	/**
	 * Warns players that the game ends in a draw
	 */
	public static void tie() {
		System.out.println();
		System.out.println("Avete scelto le stesse rocce... la partita finisce in parita' !");
		
	}

	/**
	 * Prompts a player for its turn
	 * @param player the player
	 */
	public static void turn(Player player) {
		System.out.println();
		System.out.println("E' il turno di " + player.getName());
		
	}

	/**
	 * Promts user to press enter to continue
	 */
	public static void push() {
		System.out.println();
		util.mylib.diSG.InputDati.isInvioPremuto("", "Premi invio per lanciare la prossima roccia\n");
		
	}
	
	/**
	 * Prompts players to choose the level of difficulty for the game
	 * @return the number of elements for the game or 0 if the players want to choose again
	 */
	public static int setElementAdvance() {

		final String[] LEVEL = { "ENTRY", "MEDIUM", "ADVANCE", "UNGODLY" };
		MyMenu sottomenu = new MyMenu("quante forze pensi di poter gestire?", LEVEL);
		int scelta = sottomenu.scegli();
		switch (scelta) {
		case 0:
			if (InputDati.yesOrNo("per tornare indietro devi ammettere di aver paura,hai paura?"))
				return 0;
			else return -2;
		case 1:
			System.out.println("equilibrio di elementi controllabili da matricole");
			return Equilibrium.setN(EstrazioniCasuali.estraiIntero(4, 5));
		case 2:
			System.out.println("interazione tra forze miti e ditruttive non banale ");
			return Equilibrium.setN(EstrazioniCasuali.estraiIntero(6, 7));
		case 3:
			System.out.println("potere srigionato gestibile solo da utenti avanzati Avanzati ");
			return Equilibrium.setN(EstrazioniCasuali.estraiIntero(8, 9));

		case 4:
			System.out.println("ATTENZIONE ONLY MASTER");
			System.out.println("numero troppo elevato di forze in gioco pericolo");
			return Equilibrium.setN(10);

		}
		return 0;
	}

	/**
	 * Prompts user to enter a name
	 * @return the player created with such a name
	 */
	public static Player registerPlayer() {
		
		String username = InputDati.leggiStringaNonVuota("inserisci username :");
		char ensign =InputDati.leggiCharNonVuoto("scegli un carattere per il tuo vessillo");
		return new Player(username, ensign);
	}
	
    /**
     * 	Prompts users to set their names
     */
	public static void introPlayer() {
		
		System.out.println("Sarete due sfidanti e ... non sarete soli");
		System.out.println("Finalemnte testerete le abilità dei vostri TAMAGOLEM !!! ");    		
		System.out.println("Adesso le presentazioni:");
		
		
		Battle.setPlayerOne(Utility.registerPlayer());
		System.out.println("Bene allievo adesso tocca al tuo sifdante:");
		Battle.setPlayerTwo(Utility.registerPlayer());
	}
    
	/**
	 * Prompts player to initialize its Golem
	 * @return the Golem evocated
	 */
	public static Golem firstEvoGolem() {
		
		Golem golem = new Golem();
    	//String nameGolem = InputDati.leggiStringaNonVuota("Nome del TAMAGOLEM :"); 
    	//golem.setNameGolem(nameGolem);
        golem.addRocks();
	
        return golem;
	}
	
	/**
	 * Prompts players to evocate their Golems for the first time
	 * @param playerOne first player
	 * @param playerTwo second player
	 */
	public static void introGolem(Player playerOne, Player playerTwo) {
		
		System.out.println("Evocate le votre creature");
		System.out.println("Dategli in pasto le pietre degli elementi ");
		turn(playerOne);
	    Battle.setGolemOne(Utility.firstEvoGolem());
	    playerOne.setMyGolem(Battle.getGolemOne());
		System.out.println("Bene allievo adesso tocca al tuo sifdante:");
		Battle.setGolemtwo(Utility.firstEvoGolem());
		playerTwo.setMyGolem(Battle.getGolemTwo());
		
	}

	/**
	 * Warns players that they're gonna play with 3 elements
	 */
	public static void beginnerAdvise() {
		System.out.println();
		System.out.println("Giocherete con 3 elementi");
		
	}

	public static void revelation() {
		System.out.println();
		System.out.println("La partita è terminata. E' arrivato il momento di svelare\n"+
							"l'equilibrio delle forze del Mondo.\n");
		
	}

}
