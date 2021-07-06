//OJO: Este archivo no pertenece a ningun paquete (package)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * Programa para calcular la cantidad y suma de numeros pares en una lista de numeros
 * @author Jorge Duitama
 * Editado por Nelson Sanchez
 */
public class ProblemaZ {

	public static void main(String[] args) throws Exception {
		ProblemaZ instancia = new ProblemaZ();
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

}
