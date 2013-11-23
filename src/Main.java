/*
2013 - Trabalho 3
Juliano Rotta
Italo Qualisoni
*/
public class Main {

	public static void chamaResolve(int n) {
		for (int x = 1; x <= n; x++) {
			int[] resposta = new int[n];
			resolve(x, populaLista(n), resposta, 0);
		}
	}
	
	//pai -> valor do nodo pai
	//proximaResposta -> posicao em que a ultima resposta correta esta armazenada
	public static boolean resolve(int pai, int[] disponiveis, int[] resposta, int proximaResposta) {
		if (proximaResposta == resposta.length-1) {
			resposta[proximaResposta] = pai;
			if (circular) {
				if (!quadradoPerfeito(resposta[0] + pai)) {
					resposta[proximaResposta] = 0;
					return false;
				}
			}
			imprimeResultado(resposta);
			totalRespostas++;
			return true;
		}
		
		resposta[proximaResposta] = pai;
		proximaResposta++;
		disponiveis[pai-1] = 0;
		
		for (int x = 1; x <= disponiveis.length; x++) {
			if (disponiveis[x-1] > 0) {
				if (quadradoPerfeito(resposta[proximaResposta-1] + x)) {
					if (!resolve(x, disponiveis, resposta, proximaResposta)) {
						disponiveis[x-1] = pai;
						resposta[proximaResposta] = 0;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean quadradoPerfeito(int n) {
		Double resultado = Math.sqrt(n);
		return resultado.intValue() == resultado;
	}
	
	public static void imprimeResultado(int[] lista) {
		for (int x = 1; x <= lista.length; x++) {
			System.out.print(lista[x-1] + " ");
		}
		System.out.println();
	}
	/*Metodo usado para testar nosso vetor resultado
	 *Foi usado durante a criacao do algoritimo
	*/
	public static boolean verificaResposta(int[] lista) {  
		for (int x = 1; x < lista.length; x++) {
			if (!quadradoPerfeito(lista[x-1] + lista[x])) return false;
		}
		return true;
	}
	
	public static int[] populaLista(int n) {
		int[] retorno = new int[n];
		for (int x = 1; x <= n; x++) {
			retorno[x-1] = x;
		}
		return retorno;
	}

	public static int totalRespostas = 0;
	public static boolean circular = false;

	public static void main(String[] args) {
		System.out.println("\nTrabalho 3 - Complexidade e Optimizacao de Algoritmos");
		System.out.println("Juliano Rotta");
		System.out.println("Italo Lobato\n");
		if (args.length < 1) {
			System.out.println("Passe pelo menos um parametro numerico para gerar as respostas.\n");
			System.out.println("Primeiro parametro: numero de elementos na lista.");
			System.out.println("Segundo parametro: lista circular(1), lista normal(0).");
			System.out.println("Default: Lista normal (a soma do ultimo elemento com o primeiro nao precisa ser um quadrado perfeito).");
		} else {
		
		
		if (args.length == 2) {
			int valor = new Integer(args[1]);
			if (valor == 1) {
				circular = true;
				System.out.println("Opcao de lista circular.\n");
				
			}
		}
		
		chamaResolve(new Integer(args[0]));
		
		if (totalRespostas == 0) {
			System.out.println("Nenhum resultado encontrado.\n");
		}
		else
			System.out.println("Total de resultados encontrados: "+ totalRespostas +"\n");
		System.out.println("Para novas consultas:");
		System.out.println("Primeiro parametro: numero de elementos na lista.");
		System.out.println("Segundo parametro: lista circular(1), lista normal(0).");
		System.out.println("Default: Lista normal (a soma do ultimo elemento com o primeiro nao precisa ser um quadrado perfeito).");
		
		}
	}

}
