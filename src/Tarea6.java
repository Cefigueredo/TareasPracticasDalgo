import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Tarea6 {
	private static  int V = 0;
    
 
    // Driver method
    public static void main(String[] args) throws IOException
    {
    	Tarea6 instancia = new Tarea6();
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
				int n = tamanio[0];//Cantidad computadoras
				V = n;
				int k = tamanio[1];//Cantidad cables
				int[][] matr = new int[k][3];//Inicia arreglo
				
				//Llena al matriz con los valores del dados por entrada
				for(int i = 0; i < k; i++) {
					line = br.readLine();
					final String [] dataStr4 = line.split(" ");
					final int[] arrayEnI = Arrays.stream(dataStr4).mapToInt(f->Integer.parseInt(f)).toArray();
					for(int j = 0;j < 3;j++) {
						matr[i][j] = arrayEnI[j];
					}
				}

				//Ejecuta el algoritmo
				int[] respuestas = instancia.dijkstra(matr);
				String r = "";
				//Imprime la respuesta
				for(int i = 1; i < respuestas.length; i++) {
					r = r.concat(respuestas[i]+" ");
				}
				System.out.println(r);
				line = br.readLine();
			}
			is.close();
			br.close();
		}
    }
    public static int maxDistance(int dist[], Boolean sptSet[])
    {
        // Initialize max value
        int max = Integer.MAX_VALUE, max_index = V-1;
 
        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= max) {
                max = dist[v];
                max_index = v;
            }
 
        return max_index;
    }
 

    public static int[] dijkstra(int graph[][])
    {
    	
        int maxCapacidad[] = new int[V+1]; 
//        Boolean sptSet[] = new Boolean[V];
//        
        for (int i = 0; i < V; i++) {
        	maxCapacidad[i] = Integer.MAX_VALUE;
            
        }
// 
//        for (int count = 0; count < V - 1; count++) {
//            int u = maxDistance(maxCapacidad, sptSet);
//            sptSet[u] = true;
// 
//            for (int v = 0; v < 3; v++)
//                if (!sptSet[v] && graph[u][v] != 0 && maxCapacidad[u] != Integer.MAX_VALUE && maxCapacidad[u] + graph[u][v] < maxCapacidad[v])
//                	maxCapacidad[v] = maxCapacidad[u] + graph[u][v];
//        }
        
        
        for(int i = 0; i < graph.length; i++) {
        	if(maxCapacidad[graph[i][0]] > graph[i][2]) {
        		maxCapacidad[graph[i][0]] = graph[i][2];
        	}
        	if(maxCapacidad[graph[i][1]] > graph[i][2]) {
        		maxCapacidad[graph[i][1]] = graph[i][2];
        	}
        }
        
        return maxCapacidad;
    }
}

