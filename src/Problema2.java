import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * 
 * @author Carlos Figueredo - 201813445 && Camilo Otalora - 201732760
 * Fuente: GeeksForGeeks (2021).Box Stacking Problem | DP-22
 * Recuperado de: https://www.geeksforgeeks.org/box-stacking-problem-dp-22/
 */
public class Problema2 {

	public static int DIMENSIONES_CAJA = 3;


	/* Representation of a box */
	static class Box implements Comparable<Box>{

		// h --> height, w --> width,
		// d --> depth
		int h, w, d, area;

		// for simplicity of solution,
		// always keep w <= d

		/*Constructor to initialise object*/
		public Box(int h, int w, int d) {
			this.h = h;
			this.w = w;
			this.d = d;
		}

		/*To sort the box array on the basis
        of area in decreasing order of area */
		@Override
		public int compareTo(Box o) {
			return o.area-this.area;
		}
	}

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
				Box[] arr = new Box[n];
				//Carga la matriz con los datos
				for(int i = 0; i < n; i++) {
					line = br.readLine();
					final String [] dataStr2 = line.split(" ");
					final int[] arrayEnI = Arrays.stream(dataStr2).mapToInt(f->Integer.parseInt(f)).toArray();
					arr[i] = new Box(arrayEnI[0],arrayEnI[1],arrayEnI[2]);
				}

				int respuestas = instancia.alturaMaxPilaCajas(arr, n);

				System.out.println(respuestas);

				line = br.readLine();
			}
		}
	}
	public static int alturaMaxPilaCajas(Box[] arr, int n) {

		Box[] rot = new Box[n*3];

		/* New Array of boxes is created -
        considering all 3 possible rotations,
        with width always greater than equal
        to width */
		for(int i = 0;i < n;i++){
			Box box = arr[i];

			/* Original Box*/
			rot[3*i] = new Box(box.h, Math.max(box.w,box.d),
					Math.min(box.w,box.d));

			/* First rotation of box*/
			rot[3*i + 1] = new Box(box.w, Math.max(box.h,box.d),
					Math.min(box.h,box.d));

			/* Second rotation of box*/
			rot[3*i + 2] = new Box(box.d, Math.max(box.w,box.h),
					Math.min(box.w,box.h));
		}

		/* Calculating base area of
        each of the boxes.*/
		for(int i = 0; i < rot.length; i++)
			rot[i].area = rot[i].w * rot[i].d;

		/* Sorting the Boxes on the bases
        of Area in non Increasing order.*/
		Arrays.sort(rot);

		int count = 3 * n;

		/* Initialize msh values for all
        indexes
        msh[i] --> Maximum possible Stack Height
                   with box i on top */
		int[]msh = new int[count];
		for (int i = 0; i < count; i++ )
			msh[i] = rot[i].h;

		/* Computing optimized msh[]
        values in bottom up manner */
		for(int i = 0; i < count; i++){
			msh[i] = 0;
			Box box = rot[i];
			int val = 0;

			for(int j = 0; j < i; j++){
				Box prevBox = rot[j];
				if(box.w < prevBox.w && box.d < prevBox.d){
					val = Math.max(val, msh[j]);
				}
			}
			msh[i] = val + box.h;
		}

		int max = -1;

		/* Pick maximum of all msh values */
		for(int i = 0; i < count; i++){
			max = Math.max(max, msh[i]);
		}

		return max;
	}
}
