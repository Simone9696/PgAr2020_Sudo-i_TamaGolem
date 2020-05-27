package it.unibs.ing.fp.arnaldo.tamagolem;

import java.util.ArrayList;
import java.util.Map;

public class Golem {
	
	private boolean dead = false;

	ArrayList<ElementRock> rocks; 
	
	
	private String nameGolem;
	
	private static int MAX_ROCKS = 0;
	
	private static final int MAX_LIFE = 5; // cambiare a piacere
	
	private int life = MAX_LIFE;
	
	private int rockThrown = 0;
	
	
	
	
	
	public Golem() {}
	
	
	
	public Golem(String nameGolem) {
	
		this.nameGolem = nameGolem;
	}

	
	public Golem(String nameGolem, ArrayList<ElementRock> rocks, int life) {
		
		
		this.nameGolem = nameGolem;
		this.rocks  = new ArrayList<ElementRock>();
		this.life = getMaxLife();
	}



	

	public static void initializeMaxRocks() {
		MAX_ROCKS = (int) (Math.ceil((Equilibrium.getN() + 1) / 3.0) + 1);
	}
	
	public boolean isDead() {
		return dead;
		
	}
	
	public ElementRock throwRock(Player player) {
		
		if (rockThrown == rocks.size()) {
			rockThrown = 0;
		}
		
		rockThrown++;
		
		Utility.throwRockIntro(rocks.get(rockThrown - 1), player);
		
		return rocks.get(rockThrown - 1);
		
	}
	
	public void addRocks() {
		
		Utility.addRocksIntro();
		
		Map<Elements, Integer> map = Battle.getDisposableRocks();
		
		if (Battle.areThereStillRocks()) {
			for (int i = 0; i < MAX_ROCKS; i++) {
				ElementRock rock = Utility.chooseRock();
				if (map.get(rock.getType()) > 0) {
					rocks.add(rock);
					map.replace(rock.getType(), map.get(rock.getType()) - 1);
				} else {
					i--;
					Utility.chooseAnotherRock();
				}
			} 
		} else {
			return;
		}
		
		Battle.setDisposableRocks(map);
	}

	public int getLife() {
		return this.life;
	}

	public void setLife(int life) {
		
		this.life = life;
		if (life <= 0) {
			dead = true;
			this.life = 0;
		}
	}

	public static int getMaxRocks() {
		return MAX_ROCKS;
	}

	public static int getMaxLife() {
		return MAX_LIFE;
	}
}
