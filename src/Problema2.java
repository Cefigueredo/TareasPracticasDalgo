import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problema2 {
	public static void main(String[] args) throws Exception {
		Problema2 instancia = new Problema2();
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
				final String [] dataStr1 = line.split(" ");
				final int[] tamanio = Arrays.stream(dataStr1).mapToInt(f->Integer.parseInt(f)).toArray();
				int n = tamanio[0];
				int[][] matr = new int[n][n];

				for(int i = 0; i < n; i++) {
					line = br.readLine();
					final String [] dataStr2 = line.split(" ");
					final int[] arrayEnI = Arrays.stream(dataStr2).mapToInt(f->Integer.parseInt(f)).toArray();
					for(int j = 0;j < n;j++) {
						matr[i][j] = arrayEnI[j];
					}

				}
				int respuestas = instancia.hallarMayorSumSubarreglo(matr,n);				
				System.out.println(respuestas);

				line = br.readLine();
			}
		}
	}
}
