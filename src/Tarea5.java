import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
/**
 * 
 * @author Carlos Figueredo y Camilo Otalora
 *Fuente:
 *TutorialCup (2021). Encuentra si hay un subarreglo con suma 0.
 *Recuperado de https://www.tutorialcup.com/es/entrevista/Hashing/encuentra-si-hay-un-subarreglo-con-0-sum.htm
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
			while(line!=null && line.length()>0 && !"0".equals(line)) {
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
}
