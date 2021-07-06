import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Tarea4 {
	public static void main(String[] args) throws Exception {
		Tarea4 instancia = new Tarea4();
		try ( 
			//Se recibe la lectura de consola
			InputStreamReader is= new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(is);
		) { 
			//Se lee cada linea de entrada por separado
			String line = br.readLine();
				
				//Se entra mientras la linea no sea vacia o no sea igual al caractaer de terminacion "0" (este depende de la especificacion de la salida)
			while(line!=null && line.length()>0 && !"0".equals(line)) {
				//Se procesa la linea
				final String [] dataStr = line.split(" ");
				final int[] numeros = Arrays.stream(dataStr).mapToInt(f->Integer.parseInt(f)).toArray();
				//Se resuelve el correspondiente caso
				int [] respuestas = instancia.procesarNumeros(numeros);
				//Se imprime la respuesta y se reinicia el while para verificar si existe otro caso por procesar
				System.out.println(respuestas[0]+" "+respuestas[1]);
				line = br.readLine();
			}
		}
	}
	/**
	 * Calcula la cantidad de pares y la suma de pares de una lista de numeros
	 * @param numeros a procesar 
	 * @return int [] arreglo de dos posiciones. La primera tiene la cantidad de numeros pares
	 * y la segunda la suma de los numeros 
	 */
	public int [] procesarNumeros(int[] numeros) {
		int [] respuestas = {0,0};
		for(int i=0;i<numeros.length;i++) {
			if(numeros[i]%2==0) {
				respuestas[0]++;
				respuestas[1]+=numeros[i];
			}
		}
		return respuestas;
	}
	
	public String hallarNMasRepetido(ArrayList<String> a, ArrayList<String> b) {


		    //n = largo del array 
		    //a = array 1
		    //b - array 2 

		    int n = 4;

		    Int a[] = a[1,2,3,4]; 
		    Int b[] = new ArrayList<>(); 
		    Int b1[] = new ArrayList<>(); 
		    Int a1[] = new ArrayList<>(); 


		    int cont = 1 ;
		    int max = 0 ;
		    int actual = 0;
		    int i = 0;
		    int j = 0;
		    int j1 = 0;

		    mergeSort(a, n);
		    mergeSort(b, n);


		    while(i < n && j < n ){
			if(a[1]==b[j] && b1[j1] != a[i]){
			    b1[j1] = b[j];
			    j++;
			    j1++;
			}
			else if(a[i]<b[j]){
			    i++;
			}
			else if(){

			}
		    }


		    System.out.println(cont);   


		    }


		    public static void mergeSort(int[] a, int n) {
			if (n < 2) {
			    return;
			}
			int mid = n / 2;
			int[] l = new int[mid];
			int[] r = new int[n - mid];

			for (int i = 0; i < mid; i++) {
			    l[i] = a[i];
			}
			for (int i = mid; i < n; i++) {
			    r[i - mid] = a[i];
			}
			mergeSort(l, mid);
			mergeSort(r, n - mid);

			merge(a, l, r, mid, n - mid);
		    }

		    public static void merge(
		  int[] a, int[] l, int[] r, int left, int right) {

		    int i = 0, j = 0, k = 0;
		    while (i < left && j < right) {
			if (l[i] <= r[j]) {
			    a[k++] = l[i++];
			}
			else {
			    a[k++] = r[j++];
			}
		    }
		    while (i < left) {
			a[k++] = l[i++];
		    }
		    while (j < right) {
			a[k++] = r[j++];
		    }
		

	}
}
