package it.unibs.ing.fp.arnaldo.tamagolem;

public class GoleMain {

	public static void main(String[] args) {
		
		do {
			
			Golem.initializeMaxRocks();
			
			Player.initializeMaxGolems();
			
			Battle.initializeRockStock();
				
			Battle.battle();
			
		} while (Utility.newPlay());

	}

}
