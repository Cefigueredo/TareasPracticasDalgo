import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Este codigo no es de nuestra autoria, fue extraido del link
 * https://www.cnblogs.com/lz87/p/12005423.html
 * 
 * Se encontrara correctamente citado en la parte teorica.
 * Fue ligeramente modificado.
 *
 */
public class Main {
	public static void main(String[] args) {
		// long TInicio, TFin, tiempo;
		// TInicio = System.currentTimeMillis();

		try (InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader buffo = new BufferedReader(isr);) {
			// Se lee cada linea de entrada por separado
			String line = buffo.readLine();

			if (line.split(" ")[0].equals("<")) {
				ArrayList<Integer> lista = new ArrayList<>();
				String[] entrada = line.split(" ");

				// Aqui deberia cambiar la ruta en caso de querer una especifica o si es un pc
				// distinto
				File file = new File("C:\\Users\\carlo\\Downloads\\" + entrada[1]);
				BufferedReader br = new BufferedReader(new FileReader(file));

				// Aqui deberia cambiar la ruta en caso de querer una especifica o si es un pc
				// distinto
				FileWriter fw = new FileWriter("C:\\Users\\carlo\\Downloads\\" + entrada[3]);

				String lectura;
				while ((lectura = br.readLine()) != null && lectura.length() > 0 && !"0".equals(lectura)) {
					// Tamaño NxN de la matriz
					Integer ene = Integer.valueOf(lectura);

					// Lee y y guarda la matriz
					String[][] mat = new String[ene][ene];
					

					int fila = 0; // Para recorrer las filas de la matriz
					lectura = br.readLine();
					while (lectura != null) {
						String[] enteros = lectura.split("");
						for (int i = 0; i < enteros.length; i++)
							mat[fila][i] = enteros[i];

						fila++; // Incrementamos fila para la próxima línea de enteros

						if (fila == ene)
							break;

						lectura = br.readLine(); // Leemos siguiente línea
					}
					
					int[][] aux = new int[ene][ene];
					
					for (int i = 0; i < mat.length; i++) {
						for (int j = 0; j < mat[0].length; j++) {
							if(mat[i][j].equals("x"))
								aux[i][j] = 1;
							else
								aux[i][j] = 0;
						}
					}

					// Aqui comienza la logica
					Integer rta = minFlips(aux);
					// Aqui termina la logica

					lista.add(rta);
				}

				try {
					for (int i = 0; i < lista.size(); i++) {
						fw.append(String.valueOf(lista.get(i)));
						fw.append("\n");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						fw.flush();
						fw.close();
					} catch (Exception k) {
						k.printStackTrace();
					}
				}
			} else
				// Se entra mientras la linea no sea vacia o no sea igual al caractaer de
				// terminacion "0" (este depende de la especificacion de la salida)
				while (line != null && line.length() > 0 && !"0".equals(line)) {
					// Tamaño NxM de la matriz
					Integer ene = Integer.valueOf(line);
					
					// Lee y y guarda la matriz
					String[][] mat = new String[ene][ene];

					int fila = 0; // Para recorrer las filas de la matriz
					line = buffo.readLine();
					while (line != null) {
						String[] enteros = line.split("");
						for (int i = 0; i < enteros.length; i++)
							mat[fila][i] = enteros[i];

						fila++; // Incrementamos fila para la próxima línea de enteros

						if (fila == ene)
							break;

						line = buffo.readLine(); // Leemos siguiente línea
					}
					
					int[][] aux = new int[ene][ene];
					
					for (int i = 0; i < mat.length; i++) {
						for (int j = 0; j < mat[0].length; j++) {
							if(mat[i][j].equals("x"))
								aux[i][j] = 1;
							else
								aux[i][j] = 0;
						}
					}

					// Aqui comienza la logica
					Integer rta = minFlips(aux);
					// Aqui termina la logica

					System.out.println(rta);

					line = buffo.readLine();
				}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// TFin = System.currentTimeMillis();
		// tiempo = TFin - TInicio;
		// System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);

	}

	private static int[] dx = { -1, 0, 1, 0 };
	private static int[] dy = { 0, 1, 0, -1 };
	private static int[] dp; // dp[i]: the minimum flips needed transitioning from state i to state 0
	private static boolean[] seen;

	public static int minFlips(int[][] mat) {
		int m = mat.length;
		dp = new int[1 << (m * m)];
		seen = new boolean[1 << (m * m)];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		return dfs(mat);
	}

	private static int dfs(int[][] mat) {
		int estado = getState(mat);
		if (seen[estado]) {
			return -1;
		}
		if (dp[estado] < Integer.MAX_VALUE) {
			return dp[estado];
		}
		seen[estado] = true;
		int costoMinimo = Integer.MAX_VALUE;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				flipAt(mat, i, j);
				int min = dfs(mat);
				if (min >= 0) {
					costoMinimo = Math.min(costoMinimo, min);
				}
				flipAt(mat, i, j);
			}
		}
		dp[estado] = (costoMinimo == Integer.MAX_VALUE ? -1 : costoMinimo + 1);
		seen[estado] = false;
		return dp[estado];
	}

	private static void flipAt(int[][] mat, int x, int y) {
		mat[x][y] = (mat[x][y] == 0 ? 1 : 0);
		for (int dir = 0; dir < 4; dir++) {
			int x1 = x + dx[dir];
			int y1 = y + dy[dir];
			if (x1 >= 0 && x1 < mat.length && y1 >= 0 && y1 < mat[0].length) {
				mat[x1][y1] = (mat[x1][y1] == 0 ? 1 : 0);
			}
		}
	}

	private static int getState(int[][] mat) {
		int estado = 0, contador = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				estado = (estado | (mat[i][j] << contador));
				contador++;
			}
		}
		return estado;
	}
}
