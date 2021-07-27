import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * 
 * @author Carlos Figueredo - 201813445 && Camilo Otalora - 201
 */
public class Problema2 {
	
	public static int DIMENSIONES_CAJA = 3;
	
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
				final int[] cantidadCajas = Arrays.stream(dataStr1).mapToInt(f->Integer.parseInt(f)).toArray();
				int n = cantidadCajas[0];
				int[][] matr = new int[n][DIMENSIONES_CAJA];

				//Carga la matriz con los datos
				for(int i = 0; i < n; i++) {
					line = br.readLine();
					final String [] dataStr2 = line.split(" ");
					final int[] arrayEnI = Arrays.stream(dataStr2).mapToInt(f->Integer.parseInt(f)).toArray();
					for(int j = 0;j < DIMENSIONES_CAJA;j++) {
						matr[i][j] = arrayEnI[j];
					}

				}
				int respuestas = instancia.alturaMaxPilaCajas(matr, n);
				
				System.out.println(respuestas);

				line = br.readLine();
			}
		}
	}
	public static int alturaMaxPilaCajas(int[][] matrix, int n) {
		
		int altura = 0;
		int posAltura = 0;
		boolean[][] marcado = new boolean[matrix.length][matrix[0].length];
		int cantCajas = matrix.length;
		//Análisis
		//altura = matrix[m][n];
		for(int i = 0; i < cantCajas; i++) {
			for(int j = 0; j < DIMENSIONES_CAJA-1; j++) {
				if(matrix[i][j]<matrix[i][j+1]) {
					posAltura = j;
				}
				marcado[i][j]=true;
			}
		}
		

		return altura;
	}
}
