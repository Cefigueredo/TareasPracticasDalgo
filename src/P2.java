import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * 
 * @author Carlos Figueredo - 201813445 y Camilo Otalora - 201732760
 *
 */
public class P2 {
	public static void main(String[] args) throws Exception {
		Tarea5 instancia = new Tarea5();
		try ( 
			//Se recibe la lectura de consola
			InputStreamReader is= new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(is);
		) { 
			//Se lee cada linea de entrada por separado
			String line = br.readLine();
				
				//Se entra mientras la linea no sea vacia o no sea igual al caractaer de terminacion "0" (este depende de la especificacion de la salida)
			while(line!=null && line.length()>0 && !line.startsWith("0 0")) {
				//Se procesa la linea
				final String [] dataStr1 = line.split(" ");
				final int[] contieneT = Arrays.stream(dataStr1).mapToInt(f->Integer.parseInt(f)).toArray();
				int n = contieneT[0];
				int t = contieneT[1];
				line = br.readLine();
				final String [] dataStr2 = line.split(" ");
				final int[] primerArreglo = Arrays.stream(dataStr2).mapToInt(f->Integer.parseInt(f)).toArray();
				
				int respuestas = instancia.possible(primerArreglo,n,t);
				int respuestaNumerica = -1;
				if(respuestas)respuestaNumerica = 1;
				else respuestaNumerica = 0;
				System.out.println(respuestaNumerica);

				
				line = br.readLine();
			}
		}
	}
	 
	//cita https://medium.com/@rsinghal757/kadanes-algorithm-dynamic-programming-how-and-why-does-it-work-3fd8849ed73d?
	 public static int tres(int[][] matrix, int n ) {
		 //n es el tamaño
		 int localmax=0, globalmax = 0;

		 for (int i=0; i<n; i++){
			 for(int j = 0; j<n; j++){
				 //max(matrix o matrix+localmax)
				if(matrix[i][j] > matrix[i][j]+localmax){
					localmax = matrix[i][j];
				}else{
					localmax = matrix[i][j]+localmax;
				}
				if(localmax>globalmax){
					globalmax=localmax;
				}
			 }
		 }		 
	}
}
