package it.unibs.ing.fp.arnaldo.tamagolem;
import java.util.*;

public class Equilibrium {
	
	private static  int N = 3;// oppure far scegliere all'utente il numero di elementi (tra 3 e 10)
	private static int maxWeight = N;
	
	
	private static int equilibrium[][] = new int[N+1][N+1]; // usare elements id come indici

	
	public static int getMaxWeight() {
		return maxWeight;
	}

	public static void setMaxWeight(int max) {
		maxWeight = max;
	}

	public static int getN() {
		return N;
	}
	
	/**
	 * Calculates a new equilibrium
	 * @return true if succesful, false if not
	 */
	public static boolean newEquilibrium() {
		
		setMaxWeight(getN());
		
		for (int i = 0; i < N + 1; i++) { // inizializzo tutta la matrice a 0
			for (int j = 0; j < N + 1; j++) {
				equilibrium[i][j] = 0;
			}
		}
		
		for (int i = 0; i < N; i++) { // itero saltando ultima riga e colonna che servono per tracciare
			for (int j = 0; j < N; j++) { // le somme di righe e colonne
				if (j > i) { // lavoro sul triangolo superiore
					ArrayList<Integer> list = generatePossibleWeights(i, j);
					try {
						equilibrium[i][j] = generateRandomWeight(list); // scelgo un numero casuale tra quelli possibili
						equilibrium[j][i] = -equilibrium[i][j];
						setRowSum(i);
						setColumnSum(j);
						setRowSum(j);
						setColumnSum(i);
					} catch (Exception e) { // tasso fallimento circa 1 %
						return false;
					}
				}
			}
		} return true;
	}
	
	/**
	 * Sets the columns sum
	 * @param j the column to sum
	 */
	private static void setColumnSum(int j) {
		int sum = 0;
		for (int i = 0; i < N; i++) sum += equilibrium[i][j];
		equilibrium[N][j] = sum;
		
	}

	/**
	 * Sets the row sum
	 * @param i the row to sum
	 */
	private static void setRowSum(int i) {
		int sum = 0;
		for (int j = 0; j < N; j++) sum += equilibrium[i][j];
		equilibrium[i][N] = sum;
		
	}

	/**
	 * Generates a list of the compatible weights for a cell
	 * @param i the cell row
	 * @param j the cell column
	 * @return a {@linkplain ArrayList} containing the weights
	 */
	private static ArrayList<Integer> generatePossibleWeights(int i, int j) {
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (int k = - maxWeight; k <= maxWeight; k++) {
			if (k != 0) {
				if (isMatrixCompilable(k, i, j)) {
					list.add(k);
				}
			}
		}
		
		return list;
		
	}

	/**
	 * Checks wether a certain weight is acceptable in a given cell
	 * @param k the weight
	 * @param i the cell row
	 * @param j the cell column
	 * @return true if acceptable, false if not
	 */
	private static boolean isMatrixCompilable(int k, int i, int j) {
	
		int matrix[][] = equilibrium.clone();
		boolean compilable = false;
		matrix[i][j] = k;
		matrix[j][i] = -k;
		
		if (checkColumn(matrix, i, j) && checkRow(matrix, i, j)) compilable = true;
		
		return compilable;
	}

	/**
	 * Checks wether a <b>row</b> is fillable with proper weights once a value is inserted in a certain cell
	 * @param matrix the test matrix containing a test value in a cell
	 * @param i the test value cell row
	 * @param j the test value cell column
	 * @return true if fillable, false if not
	 */
	private static boolean checkRow(int[][] matrix, int i, int j) {
		
		int rowSumTarget = 0;
		for (int x = 0; x < N; x++) rowSumTarget += matrix[i][x];
		rowSumTarget = - rowSumTarget;
		int remainingNumbers = N - 1 - j;
		return checkExistisCombination(remainingNumbers, rowSumTarget);
		
	}

	/**
	 * Checks wether a <b>column</b> is fillable with proper weights once a value is inserted in a certain cell
	 * @param matrix the test matrix containing a test value in a cell
	 * @param i the test value cell row
	 * @param j the test value cell column
	 * @return true if fillable, false if not
	 */
	private static boolean checkColumn(int[][] matrix, int i, int j) {
		int columnSumTarget = 0;
		for (int x = 0; x < N; x++) columnSumTarget += matrix[x][j];
		columnSumTarget = - columnSumTarget;
		int remainingNumbers = N - 2 - i;
		return checkExistisCombination(remainingNumbers, columnSumTarget);
		
	}
	
	/**
	 * @see https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
	 * Adds all the possible sums of 'r' elements of a integer list to a set
	 * @param list the {@linkplain ArrayList} of integer elements
	 * @param data a temporary array to store a combination
	 * @param start start index in list
	 * @param end end index in list
	 * @param index temporary array index
	 * @param r the subset cardinality
	 * @param possibleSums the {@linkplain HashSet} to which add the sums
	 */
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
	
	/**
	 * Looks for a valid combination of weigths whose sum equals the target sum. Checks wether such a combination
	 * exist given the available weights
	 * @param remainingNumbers the number of weigth to combinate
	 * @param sumTarget the target sum
	 * @return true if a valid combination exists, false otherwise
	 */
	private static boolean checkExistisCombination(int remainingNumbers, int sumTarget) {
		
		ArrayList<Integer> weights = new ArrayList<Integer>();
		
		for (int k = - maxWeight; k <= maxWeight; k++) {
			if (k != 0) {
				weights.add(k);
			}
		}
		
		HashSet<Integer> possibleSums = new HashSet<Integer>();
		
		int tempArray[] = new int[remainingNumbers];
		
		combinationSum(weights, tempArray, 0, weights.size() - 1, 0, remainingNumbers, possibleSums);
		
		return possibleSums.contains(sumTarget);
	}

	/**
	 * Extracts a random weight from a weight list
	 * @param set the weight list
	 * @return the random weight extracted
	 */
	private static int generateRandomWeight(ArrayList<Integer> set) {
		Random rd = new Random();
		
		int ind = rd.nextInt(set.size());
		return set.get(ind);
		
	}

	/**
	 * Calculates the interaction between two rocks based upon the equilibrium
	 * @param rockOne the first rock
	 * @param rockTwo the second rock
	 * @return the damage inflicted from rock one to rock two. If rock two inflicts damage to rock one
	 * then a negative damage is returned
	 */
	public static int calculateInteraction(ElementRock rockOne, ElementRock rockTwo) {
		
		return equilibrium[rockOne.getTypeId()][rockTwo.getTypeId()];
	}

	/**
	 * Prints out the equilibrium
	 */
	public static void print() {
		
		System.out.print("\t\t");
		
		for (int i = 0; i < N; i++) { 
			System.out.print(Elements.getElement(i).toString() + (Elements.getElement(i).toString().length() > 7 ? "\t" : "\t|\t"));
		} System.out.println();
		
		for (int k = 0; k < N + 1; k++) { 
			System.out.print("---------------");
		} System.out.println();
		
		for (int i = 0; i < N; i++) { // N + 1 stampa anche le somme delle colonne
			if (i != N) {
				System.out.print(Elements.getElement(i).toString() + (Elements.getElement(i).toString().length() > 7 ? "\t" : "\t\t"));
			} else {
				System.out.print("\t\t");
			}
			for (int j = 0; j < N; j++) { // N + 1 stampa anche le somme di riga
				System.out.print(equilibrium[i][j] + "\t|\t");
			} System.out.println();
			for (int k = 0; k < N + 1; k++) { 
				System.out.print("---------------");
			} System.out.println();
		}
		
	}

	public static int setN(int n) {
		N = n;
		return n;
	}

	public static void setMatrix(int n) {
		int temp[][] = new int[n+1][n+1];
		equilibrium = temp.clone();
	}

	

}
