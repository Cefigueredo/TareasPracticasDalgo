package tareas;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * 
 * @author Carlos Figueredo - 201813445 y Camilo Otalora - 201732760
 *Fuente: Geeksforgeeks (2021). Maximum sum rectangle in a 2D matrix | DP-27.
 *Recuperado de: https://www.geeksforgeeks.org/maximum-sum-rectangle-in-a-2d-matrix-dp-27/
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		Main instancia = new Main();
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


	public static int hallarMayorSumSubarreglo(int[][] matrix, int n ) {
		int largo = matrix.length;
		int preSum[][] = new int[largo + 1][n];

		for (int i = 0; i < largo; i++)
		{
			for (int j = 0; j < n; j++)
			{
				preSum[i + 1][j] =
						preSum[i][j] + matrix[i][j];
			}
		}

		int global_max = 0;
		int local_max = 0;

		for (int i = 0;i < largo;i++){
			for (int j = i; j < largo; j++){
				
				int sum = 0;
				for (int k = 0; k < n; k++){
					
					sum += preSum[j + 1][k] - preSum[i][k];
					if (sum < 0) {
						if (local_max < sum) {
							local_max = sum;
						}
						sum = 0;
					}
					else if (global_max < sum){
						global_max = sum;
					}
				}
			}
		}

		return global_max;
	}
}
