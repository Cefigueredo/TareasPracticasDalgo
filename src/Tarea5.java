import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * 
 * @author Carlos Figueredo - 201813445 y Camilo Otalora - 201732760
 *
 */
public class Tarea5 {
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
				
				boolean respuestas = instancia.possible(primerArreglo,n,t);
				int respuestaNumerica = -1;
				if(respuestas)respuestaNumerica = 1;
				else respuestaNumerica = 0;
				System.out.println(respuestaNumerica);

				
				line = br.readLine();
			}
		}
	}
	 
	 public static boolean possible(int[] a, int n, int T) {
		 boolean r = false;		 
		 if(n==0 && T==0) r = true;
		 else if(n==0 && T!=0) r = false;
		 else {
			 r = possible(a,n-1,T-a[n-1])||possible(a,n-1,T);
			 
		 }
		 return r;
	 }
		public static int hallarMayorSumSubarreglo(int[][] matrix, int n ) {
			//n es el tamaño
			int x = 0;
			int localmax=0, globalmax = 0;
			int nums=0;
			for (int i=0; i<n; i++){
				for(int j = i; j<n; j++){
					//max(matrix o matrix+localmax)
					if(matrix[i][j] > matrix[i][j]+localmax){
						localmax = matrix[i][j];
						nums=0;
					}else{
						localmax = matrix[i][j]+localmax;
						nums++;
					}
					if(localmax>globalmax){
						globalmax=localmax;
					}
				}
			}
			x=globalmax;
			return x;
		}
		
}
