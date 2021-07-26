import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author Carlos Figueredo - 201813445 && Camilo Otalora - 201732760
 *Fuente: GeeksForGeeks (2021). Shortest distance between two cells in a matrix or grid.
 *Recuperadod de: https://www.geeksforgeeks.org/shortest-distance-two-cells-matrix-grid/
 */
class QItem {
	int row;
	int col;
	int dist;
	public QItem(int row, int col, int dist)
	{
		this.row = row;
		this.col = col;
		this.dist = dist;
	}
}

public class Problema1 {
	public static void main(String[] args) throws Exception {
		Problema1 instancia = new Problema1();
		try ( 
				//Se recibe la lectura de consola
				InputStreamReader is= new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
				) { 
			//Se lee cada linea de entrada por separado
			String line = br.readLine();

			//Se entra mientras la linea no sea vacia o no sea igual al caractaer de terminacion "0" (este depende de la especificacion de la salida)
			while(line!=null && line.length()>0 && !"0 0".equals(line)) {
				//Se procesa la linea
				final String [] dataStr1 = line.split(" ");
				final int[] tamanio = Arrays.stream(dataStr1).mapToInt(f->Integer.parseInt(f)).toArray();
				int m = tamanio[0];//Filas del arreglo
				int n = tamanio[1];//Columnas del arreglo
				int[][] matr = new int[m][n];//Inicia arreglo [m,n]

				//Establece posición Inicial
				line = br.readLine();
				final String [] dataStr2 = line.split(" ");
				final int[] posicionInicial = Arrays.stream(dataStr2).mapToInt(f->Integer.parseInt(f)).toArray();

				//Establece posición Final
				line = br.readLine();
				final String [] dataStr3 = line.split(" ");
				final int[] posicionFinal = Arrays.stream(dataStr3).mapToInt(f->Integer.parseInt(f)).toArray();


				//Llena al matriz con los valores del dados por entrada
				line = llenarMatriz(br, line, matr, m, n);

				//Ejecuta el algoritmo
				int respuestas = instancia.rallyRacing(matr,n,posicionInicial[0],posicionInicial[1],posicionFinal[0],posicionFinal[1]);

				//Imprime la respuesta
				System.out.println(respuestas);

				line = br.readLine();
			}
			is.close();
			br.close();
		}
	}
	/**
	 * Llena la matriz
	 * @param br
	 * @param linea
	 * @param matriz
	 * @param filas
	 * @param columnas
	 * @return
	 * @throws Exception
	 */
	public static String llenarMatriz(BufferedReader br, String linea, int[][] matriz, int filas, int columnas) throws Exception{
		for(int i = 0; i < filas; i++) {
			linea = br.readLine();
			final String [] dataStr4 = linea.split(" ");
			final int[] arrayEnI = Arrays.stream(dataStr4).mapToInt(f->Integer.parseInt(f)).toArray();
			for(int j = 0;j < columnas;j++) {
				matriz[i][j] = arrayEnI[j];
			}
		}
		return linea;

	}
	/**
	 * Ejecuta el algoritmo
	 * @param grid
	 * @param n
	 * @param x
	 * @param y
	 * @param r
	 * @param s
	 * @return
	 */
	public static int rallyRacing(int[][] grid, int n,int x, int y, int r, int s) {
		QItem source = new QItem(0, 0, 0);
		// To keep track of visited QItems. Marking
		// blocked cells as visited.
		source.row = x;
		source.col = y;
		

		// applying BFS on matrix cells starting from source
		Queue<QItem> queue = new LinkedList<>();
		queue.add(new QItem(source.row, source.col, 0));

		boolean[][] visited = new boolean[grid.length][grid[0].length];
		visited[source.row][source.col] = true;

		while (queue.isEmpty() == false) {
			QItem p = queue.remove();

			// Destination found;
			if (p.row == r && p.col == s)
				return p.dist;

			// moving up
			if (isValid(p.row - 1, p.col, grid, visited)) {
				int diferenciaNivel = 0;//La diferencia entre un nivel y otro
				if(grid[p.row - 1][p.col]>grid[p.row][p.col])diferenciaNivel=grid[p.row - 1][p.col]-grid[p.row][p.col];
				queue.add(new QItem(p.row - 1, p.col, p.dist + 1 + diferenciaNivel));
				visited[p.row - 1][p.col] = true;
			}

			// moving down
			if (isValid(p.row + 1, p.col, grid, visited)) {
				int diferenciaNivel = 0;//La diferencia entre un nivel y otro
				if(grid[p.row + 1][p.col]>grid[p.row][p.col])diferenciaNivel=grid[p.row - 1][p.col]-grid[p.row][p.col];
				queue.add(new QItem(p.row + 1, p.col, p.dist + 1 + diferenciaNivel));
				visited[p.row + 1][p.col] = true;
			}

			// moving left
			if (isValid(p.row, p.col - 1, grid, visited)) {
				int diferenciaNivel = 0;//La diferencia entre un nivel y otro
				if(grid[p.row][p.col - 1]>grid[p.row][p.col])diferenciaNivel=grid[p.row][p.col - 1]-grid[p.row][p.col];
				queue.add(new QItem(p.row, p.col - 1, p.dist + 1 + diferenciaNivel));
				visited[p.row][p.col - 1] = true;
			}

			// moving right
			if (isValid(p.row, p.col + 1, grid,
					visited)) {
				int diferenciaNivel = 0;//La diferencia entre un nivel y otro
				if(grid[p.row][p.col + 1]>grid[p.row][p.col])diferenciaNivel=grid[p.row][p.col + 1]-grid[p.row][p.col];
				queue.add(new QItem(p.row, p.col + 1, p.dist + 1 + diferenciaNivel));
				visited[p.row][p.col + 1] = true;
			}
		}
		return -1;

	}

	private static boolean isValid(int x, int y, int[][] grid, boolean[][] visited){
		if (x >= 0 && y >= 0 && x < grid.length
				&& y < grid[0].length
				&& visited[x][y] == false) {
			return true;
		}
		return false;
	}
}
