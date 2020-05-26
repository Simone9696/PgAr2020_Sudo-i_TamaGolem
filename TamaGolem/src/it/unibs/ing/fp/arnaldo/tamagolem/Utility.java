package it.unibs.ing.fp.arnaldo.tamagolem;

import it.unibs.fp.mylib.EstrazioniCasuali;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;


public class Utility {

	public static final boolean elementForBeginner = false;
	private static int N;
	
	
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

	public static void chooseAnotherRock() { // avvisa utente che la roccia scelta non è disponibile
		System.out.println();
		System.out.println("Roccia non piu' disponibile\n");
		
	}

	public static void addRocksIntro() { // avvisa utente che sta per scegliere le rocce da dare al golem
		System.out.println();
		System.out.println("Scegli le rocce da dare al tuo TamaGolem\n");
		System.out.println("Devi scegliere " + Golem.getMaxRocks() + " rocce");
		
	}

	public static void evocateGolemIntro() { // avvisa utente che sta per evocare un golem
		System.out.println();
		System.out.println("Evoca un nuovo TamaGolem:\n");
		
	}

	public static void battleIntro() { // avvisa utenti che stanno per iniziare una partita
		System.out.println();
		System.out.println("FORZE MITI E DISTRUTTIVE SONO IN GIOCO LA BATTAGLIA INCOMBE:\n");
//		System.out.println("sarete due sfidanti e ... non sarete soli");
//		System.out.println("finalemnte testerete le abilità dei vostri TAMAGOLEM  ");
//		System.out.println("presentazioni:");
//		
	}

	public static void throwRockIntro(ElementRock elementRock, Player player) { // avvisa utente che sta per lanciare una roccia
		System.out.println();
		System.out.println(player.getName() + " lancia una roccia di tipo " + elementRock.toString().toUpperCase());
		
	}

	public static void finishedRocks() { // avvisa che le rocce a disposizione sono finite
		System.out.println();
		System.out.println("Le rocce presenti nella scorta sono terminate\n");
		
	}

	public static void winner(Player player) { // stampa il vincitore
		System.out.println();
		System.out.println(player.getName() + " ha vinto la partita!\n");
		
	}

	public static boolean newPlay() {
		System.out.println();
		return InputDati.yesOrNo("Vuoi fare un'altra partita? ");
	}

	public static void setPlayer(Player player) { // fa aggiungere un nome al giocatore
		System.out.println();
		player.setName(InputDati.leggiStringa("iserisci Username "));
	}

	    public static void printDemage(int result, ElementRock rockOne, ElementRock rockTwo) {
		System.out.println();
		if (result < 0) {
			System.out.println(rockTwo.toString() + " infligge " + Math.abs(result) + " danni a " + rockOne.toString());
		} else {
			System.out.println(rockOne.toString() + " infligge " + Math.abs(result) + " danni a " + rockTwo.toString());
		}
	}

	public static void printStatus(Player playerOne, Player playerTwo) {
		System.out.println();
		System.out.println(playerOne.getName() + "\t-->\tVita TamaGolem: " + playerOne.getMyGolem().getLife());
		System.out.println(playerTwo.getName() + "\t-->\tVita TamaGolem: " + playerTwo.getMyGolem().getLife());
	}

	public static void golemDead(Player player) {
		System.out.println();
		System.out.println("Il TamaGolem di " + player.getName() + " e' morto\n");
		
	}

	public static void tie() {
		System.out.println();
		System.out.println("Avete scelto le stesse rocce... la partita finisce in parita' !");
		
	}

	public static void turn(Player player) {
		System.out.println();
		System.out.println("E' il turno di " + player.getName());
		
	}

	public static void push() {
		System.out.println();
		InputDati.leggiChar("Premi un tasto per lanciare la prossima roccia\n");
		
	}
    public static int setNforBeginners() {
    	return 3;
    	
    }
    
    public static int  elementForBeginner() {
    return 3;

    	}
    public static void setElementAdvance() 
  {     
    	    N = 0;
            final String[] LEVEL = { "ENTRY", "MEDIUM", "ADVANCE","UNGODLY" };
    		MyMenu sottomenu = new MyMenu("quante forze pensi di poter gestire?",LEVEL);
    		int scelta = sottomenu.scegli();
    		switch (scelta) {
    		case 0:
    			InputDati.yesOrNo("per tornare indietro devi ammettere di aver paura,hai paura?");
    			return ;
    		case 1:
    			System.out.println("equilibrio di elementi controllabili da matricole");
    			N = EstrazioniCasuali.estraiIntero(4, 5);
    			break;
			case 2:
    			System.out.println("interazione tra forze miti e ditruttive non banale ");
    			N = EstrazioniCasuali.estraiIntero(6, 7);
    			break;
    		case 3:
    			System.out.println("potere srigionato gestibile solo da utenti avanzati Avanzati ");
    			N = EstrazioniCasuali.estraiIntero(8,9);
    			break;
    		case 4:
    			System.out.println("ATTENZIONE ONLY MASTER");
    			System.out.println("numero troppo elevato di forze in gioco pericolo");
    			N =10;
    			break;
    		}
    	}
    
    	public static Player registerPlayer() {
    		String username = InputDati.leggiStringaNonVuota("inserisci username :");
    		char ensign =InputDati.leggiCharNonVuoto("scegli un carattere per il tuo vessillo");
    		return new Player(username, ensign);
    	}
    	

//		public static void introductionPlayer(Player playerOne, Player playerTwo) {
//			System.out.println("sarete due sfidanti e ... non sarete soli");
//    		System.out.println("finalemnte testerete le abilità dei vostri TAMAGOLEM  ");
//    		System.out.println("presentazioni:");
//    		
//     	    
//    		playerOne = registerPlayer();
//    		System.out.println("bene allievo"+playerOne.getName()+"adesso tocca al tuo sifdante:");
//    		playerTwo = registerPlayer();
//    		TO DO DA FARE SPER SPOSTARE TUTTO DI QUI
			
//		}
    
	// interazione con utente
}
