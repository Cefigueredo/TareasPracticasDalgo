import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
//Hecho por @Carlos Figueredo y @Camilo Otalora
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
				
				line = br.readLine();
				final String [] dataStr = line.split(" ");
				final int[] primerArreglo = Arrays.stream(dataStr).mapToInt(f->Integer.parseInt(f)).toArray();
				line = br.readLine();
				final String [] dataStr2 = line.split(" ");
				final int[] segundoArreglo = Arrays.stream(dataStr2).mapToInt(f->Integer.parseInt(f)).toArray();
				int respuestas = instancia.hallarNMasRepetido(primerArreglo, segundoArreglo);
				System.out.println(respuestas);

				
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
	/**
	 * 
	 * @param a
	 * @param b
	 * @return r
	 */
	public int hallarNMasRepetido(int[] a, int[] b) {

			int r = -1;
		    //n = largo del array 
		    //a = array 1
		    //b - array 2 

		    
		    int[] a1 = new int[a.length]; 
		    int[] b1 = new int[b.length]; 
		    
		    int anteriorCont = 1;
		    int cont = 1 ;
		    int max = 0 ;
		    int actual = 0;
		    int i = 0;
		    int j = 0;
		    int j1 = 0;
		    int i1 = 0;

		    mergeSort(a, a.length);
		    mergeSort(b, b.length);


		    while(i < a.length && j < b.length ){
		    	if(a[i]==b[j] && b1[j1] != a[i]){
		    		b1[j1] = b[j];
		    		j++;
		    		j1++;
		    	}
		    	else if(a[i]<b[j]){
		    		i++;
		    	}
		    	else if(a[i]>b[j]){
		    		j++;
		    	}
		    	else if(a[i]==b[j] && b1[j1] == a[i]) {
		    		i++;
		    		j++;
		    		j1++;
		    	}
		    }
		    i=0;
		    j=0;
		    
		    while(i < a.length && j < b.length ){
		    	if(a[i]==b[j] && a1[i1] != b[j]){
		    		a1[i1] = a[i];
		    		i++;
		    		i1++;
		    	}
		    	else if(a[i]<b[j]){
		    		i++;
		    	}
		    	else if(a[i]>b[j]){
		    		j++;
		    	}
		    	else if(a[i]==b[j] && a1[i1] == b[j]) {
		    		i++;
		    		j++;
		    		i1++;
		    	}
		    }
		    int[] c = new int[i1+j1];
		    
		    System.arraycopy(a1, 0, c, 0, i1);
	        System.arraycopy(b1, 0, c, i1, j1);
		    mergeSort(c, c.length);
		    i=0;
		    
		    while(i<c.length-1) {
		    	if(c[i]==c[i+1] ) {
		    		cont++;
		    		actual=c[i];
		    		i++;
		    	}
		    	else if(c[i]!=c[i+1]) {
		    	  	if(cont > anteriorCont) {
		    			max = actual;
		    		}
		    		anteriorCont = cont;
		    		cont = 1;
		    		i++;
		    	}
		    	
		    }
		    if(cont > anteriorCont) {
    			max = actual;
    		}
		    if(max>0) {
		    	r=max;
		    }

		    return r;
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
