package it.unibs.ing.fp.arnaldo.tamagolem;
import java.util.*;

public class Equilibrium {
	
	private static final int N = 5;// oppure far scegliere all'utente il numero di elementi (tra 3 e 10)
	private static final int MAX_WEIGHT = N - 1;
	
	public static int getN() {
		return N;
	}

	private static int equilibrium[][] = new int[N+1][N+1]; // usare elements id come indici

	
	public static void newEquilibrium() {
		
		for (int i = 0; i < N + 1; i++) { // inizializzo tutta la matrice a 0
			for (int j = 0; j < N + 1; j++) {
				equilibrium[i][j] = 0;
			}
		}
		
		for (int i = 0; i < N; i++) { // itero saltando ultima riga e colonna che servono per tracciare
			for (int j = 0; j < N; j++) { // le somme di righe e colonne
				if (j > i) { // lavoro sul triangolo superiore
					equilibrium[i][j] = generateRandomWeight(generatePossibleWeights(i, j)); // scelgo un numero casuale tra quelli possibili
					equilibrium[j][i] = -equilibrium[i][j];
					setRowSum(i);
					setColumnSum(j);
					setRowSum(j);
					setColumnSum(i);
				}
			}
		}
	}
	
	private static void setColumnSum(int j) {
		int sum = 0;
		for (int i = 0; i < N; i++) sum += equilibrium[i][j];
		equilibrium[N][j] = sum;
		
	}

	private static void setRowSum(int i) {
		int sum = 0;
		for (int j = 0; j < N; j++) sum += equilibrium[i][j];
		equilibrium[i][N] = sum;
		
	}

	private static ArrayList<Integer> generatePossibleWeights(int i, int j) {
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (int k = - MAX_WEIGHT; k < N; k++) {
			if (k != 0) {
				if (isMatrixCompilable(k, i, j)) {
					list.add(k);
				}
			}
		}
		
		return list;
		
	}


	private static boolean isMatrixCompilable(int k, int i, int j) {
	
		int matrix[][] = equilibrium.clone();
		boolean compilable = false;
		matrix[i][j] = k;
		matrix[j][i] = -k;
		
		if (checkColumn(matrix, i, j) && checkRow(matrix, i, j)) compilable = true;
		
		return compilable;
	}

	private static boolean checkRow(int[][] matrix, int i, int j) {
		
		int rowSumTarget = 0;
		for (int x = 0; x < N; x++) rowSumTarget += matrix[i][x];
		rowSumTarget = - rowSumTarget;
		int remainingNumbers = N - 1 - j;
		return checkExistisCombination(remainingNumbers, rowSumTarget);
		
	}

	private static boolean checkColumn(int[][] matrix, int i, int j) {
		int columnSumTarget = 0;
		for (int x = 0; x < N; x++) columnSumTarget += matrix[x][j];
		columnSumTarget = - columnSumTarget;
		int remainingNumbers = N - 2 - i;
		return checkExistisCombination(remainingNumbers, columnSumTarget);
		
	}
	
	private static void combinationSum(ArrayList<Integer> list, int data[], int start, int end, int index, int r, HashSet<Integer> possibleSums) { 
		 
		if (index == r) { 
			
			int sum = 0;
			for (int i = 0; i < r; i++) sum += data[i]; 
			possibleSums.add(sum);
			return; 
		} 

		for (int i = start; i <= end && end - i + 1 >= r - index; i++) { 
			data[index] = list.get(i); 
			combinationSum(list, data, i+1, end, index+1, r, possibleSums); 
		} 
	} 
	
	private static boolean checkExistisCombination(int remainingNumbers, int rowSumTarget) {
		
		ArrayList<Integer> weights = new ArrayList<Integer>();
		
		for (int k = - MAX_WEIGHT; k < N; k++) {
			if (k != 0) {
				weights.add(k);
			}
		}
		
		HashSet<Integer> possibleSums = new HashSet<Integer>();
		
		int tempArray[] = new int[remainingNumbers];
		
		combinationSum(weights, tempArray, 0, weights.size() - 1, 0, remainingNumbers, possibleSums);
		
		return possibleSums.contains(rowSumTarget);
	}

	private static int generateRandomWeight(ArrayList<Integer> set) {
		Random rd = new Random();
		try {
			int ind = rd.nextInt(set.size());
			return set.get(ind);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return -1;
		
	}

	public static int calculateInteraction(ElementRock rockOne, ElementRock rockTwo) {
		
		return equilibrium[rockOne.getTypeId()][rockTwo.getTypeId()];
	}

	public static void print() {
		for (int i = 0; i < N + 1; i++) { 
			for (int j = 0; j < N + 1; j++) {
				System.out.print(equilibrium[i][j] + "\t");
			} System.out.println();
		}
		
	}

	

}
