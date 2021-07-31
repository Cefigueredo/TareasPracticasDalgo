import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;



public class Main{
	
	private static int[][] start = new int[3][3];	
	private static ArrayList<int[][]>  q = new ArrayList<int[][]>();
	private static int[][][] r = new int[300][][];
	private static int tamTablero = 0;
	
	
	public static void main(String[] args) throws IOException {
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
				final int[] nArray = Arrays.stream(dataStr1).mapToInt(f->Integer.parseInt(f)).toArray();
				int n = nArray[0];
				tamTablero = n;
				int[][] matrix = new int[n][n];
				for(int i = 0;i<n;i++) {
					line = br.readLine();
					final String [] dataStr2 = line.split("");

					for(int j = 0; j < n; j++) {
						
						if(dataStr2[j].contains("x")) {
							matrix[i][j]=1;
						}
						else {
							matrix[i][j]=0;
						}
					}
				}
				start = matrix;
				q.add(start);
				q.add(new int[0][0]);
				metodazo();

				line = br.readLine();
			}
			is.close();
			br.close();
					
		}


	}
	
	public static void metodazo() {
		Hashtable<Integer,Boolean> d = new Hashtable<Integer,Boolean>();
		int depth = 0;
		boolean cent = false;
		while(0 < q.size() && !cent){
			
			int[][] state = q.get(0);
			q.remove(0);

			if(state.length==0) {
				depth+=1;
				q.add(state);
			}
			else {
				int val=getStateVal(start);
				if(val==0) {
					System.out.println(depth);
					cent=true;
				}
				if (!d.contains(val) && !cent) {
					d.put(val, true);
					for(int i = 0; i < state.length;i++) {
						for(int j = 0; j < state.length;j++) {
							int[][] newState = new int[tamTablero][tamTablero];
							newState = toggle(start, i, j);
							q.add(newState);
						}
					}
				}
			}
		}
	}

	public static int getStateVal(int[][] state) {
		int val = 0;
		int base = 1;
		for(int i = 0; i < state.length;i++) {
			for(int j = 0; j < state[0].length;j++) {
				val += base*state[i][j];
				base = base*2;
			}
		}
		return val;
	}
	
	public static int toggleBit(int bit) {
		return bit==0?1:0;
	}
	
	public static int[][] toggle(int[][] state, int i, int j) {

		int[][] newState = new int[i][j];
//		for r in state: 
//		new_state.append([c for c in r]) 
		newState = state;
		newState[i][j] = toggleBit(newState[i][j]);
		
		if(i+1 < state.length) {
			newState[i+1][j] = toggleBit(newState[i+1][j]);
		}
		if(i-1 >= 0) {
			newState[i-1][j] = toggleBit(newState[i-1][j]);
		}
		if(j+1 < state[0].length) {
			newState[i][j+1] = toggleBit(newState[i][j+1]);
		}
		if(j-1 >= 0) {
			newState[i][j-1] = toggleBit(newState[i][j-1]);
		}
		return newState;
	}
}
