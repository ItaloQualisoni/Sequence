/*
2013 - Trabalho 3
Juliano Rotta
Italo Qualisoni
*/
public class MainEnglish {

	public static void callResolve(int n) {
		for (int x = 1; x <= n; x++) {
			int[] answer = new int[n];
			resolve(x, populateList(n), answer, 0);
		}
	}
	
	public static boolean resolve(int father, int[] availables, int[] answer, int nextAnswer) {
		if (nextAnswer == answer.length-1) {
			answer[nextAnswer] = father;
			if (circular) {
				if (!isSquare(answer[0] + father)) {
					answer[nextAnswer] = 0;
					return false;
				}
			}
			printSolution(answer);
			solutionsFound++;
			return true;
		}
		
		answer[nextAnswer] = father;
		nextAnswer++;
		availables[father-1] = 0;
		
		for (int x = 1; x <= availables.length; x++) {
			if (availables[x-1] > 0) {
				if (isSquare(answer[nextAnswer-1] + x)) {
					if (!resolve(x,availables , answer, nextAnswer)) {
						availables[x-1] = father;
						answer[nextAnswer] = 0;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean isSquare(int n) {
		Double resultado = Math.sqrt(n);
		return resultado.intValue() == resultado;
	}
	
	public static void printSolution(int[] list) {
		for (int x = 1; x <= list.length; x++) {
			System.out.print(list[x-1] + " ");
		}
		System.out.println();
	}
		
	public static int[] populateList(int n) {
		int[] retorno = new int[n];
		for (int x = 1; x <= n; x++) {
			retorno[x-1] = x;
		}
		return retorno;
	}

	public static int solutionsFound = 0;
	public static boolean circular = false;

	public static void main(String[] args) {
		System.out.println("\nTrabalho 3 - Complexidade e Optimizacao de Algoritmos");
		System.out.println("Juliano Rotta");
		System.out.println("Italo Lobato\n");
		if (args.length < 1) {
			System.out.println("Please provide at least one number as argument to gerate the solutions.\n");
			System.out.println("Instructions to perform new searchs:");
			System.out.println("First argument: size number of the list.");
			System.out.println("Second argument: circular list(1), normal list(0).");
			System.out.println("Default: normal list (the sum of the first and last element not necessary is a perfect square number.).");

		} else {
		
		
		if (args.length == 2) {
			int value = new Integer(args[1]);
			if (value == 1) {
				circular = true;
				System.out.println("List circular option.\n");
				
			}
		}
		
		callResolve(new Integer(args[0]));
		
		if (solutionsFound == 0) {
			System.out.println("None answer found.\n");
		}
		else
		System.out.println("Total of solutions founds: "+ solutionsFound +"\n");
		System.out.println("Instructions to perform new searchs:");
		System.out.println("First argument: size number of the list.");
		System.out.println("Second argument: circular list(1), normal list(0).");
		System.out.println("Default: normal list (the sum of the first and last element not necessary is a perfect square number.).");
		
		}
	}

}
