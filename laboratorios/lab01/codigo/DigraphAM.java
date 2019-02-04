import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Scanner;

/**
 * Esta clase es una implementación de un digrafo usando matrices de adyacencia
 *
 * @author Daniel Garcia utilizando informacíon de https://www.geeksforgeeks.org/bipartite-graph/
 * El algoritmo desarrollado por mi persona cuenta aún con ciertas fallas
 * @version 1
 */
public class DigraphAM
{
   private int[][] matriz;
   ArrayList<Integer> grupoA = new ArrayList<Integer>();
   ArrayList<Integer> grupoB = new ArrayList<Integer>();
   private int ultimo=0;


    /**Constructor donde construiremos el grafo y llamaremos a los demas metodos
       @param size, tamaño de la matriz(numero de nodos del grafo)
      */
    public DigraphAM(int size)
   {

        Scanner in = new Scanner(System.in);
        matriz= new int[size][size];
        System.out.println("Ingrese el número de arcos que desea: ");
        int arcos = in.nextInt();
             for(int i = 0;i<arcos; i++)
             {
               System.out.println("\nNodo Inicio: ");
               int source = in.nextInt();
               System.out.println("Nodo Destino: ");
               int destination= in.nextInt();
               int arco = 1;
               //Creamos una arista
               addArc(source, destination, arco);
               mostrarMatriz();
             }
             boolean conjuntos = Divisible();
             boolean bicolor = Bipartito();
             if(conjuntos == true)
             {
               System.out.println("\n\nSegún algoritmo propio: BICOLORABLE");
             }else
                 {
                     System.out.println("\n\nSegún algoritmo propio: NOT BICOLORABLE");
                 }
             if(bicolor == true)
             {
               System.out.println("\n\nSegún algoritmo modificado de https://www.geeksforgeeks.org/bipartite-graph/ : BICOLORABLE");
             }else
                 {
                     System.out.println("\n\nSegún algoritmo modificado de https://www.geeksforgeeks.org/bipartite-graph/ : NOT BICOLORABLE");
                 }

   }


    /**Metodo para unir dos nodos por medio de una arista
      @param source, nodo de inicio
      @param destination, nodo de destino
      @param weight, peso de la arista
    */
    public void addArc(int source, int destination, int weight)
    {
       matriz[source][destination]= weight;
       matriz[destination][source]= weight;
    }


      //Método para definir si un grafo es bipartito
  boolean Bipartito()
      {
        int src = 0;

        /** Creamos un arreglo de colores para asignar a todos los vértices
          El número de nodo lo utilizamos como indicador en el arreglo.
          El valor '-1' del arreglo se utiliza para indicar que el nodo´'i' no tiene un color asignado
          El valor'1' para indicar que fue asignado el primer color
          El valor'0' para indicar que fue asignado el segundo color
        */

        int colorArr[] = new int[matriz.length];
        for (int i=0; i<matriz.length; ++i)
            colorArr[i] = -1;

        // Asignamos el primer color al valor fuente
        colorArr[src] = 1;

        // Creamos una cola donde entra el primer numero de nodo
        // Y encolamos el nodo fuente para hacer un recorrido en anchura (BFS)
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(src);

        //Mientras hayan nodos en la cola
        while (q.size() != 0)
        {
            //Tomamos el primer elemento y lo eliminamos de la lista
            int j = q.poll();

            // Retorna falso si hay un bucle
            if(matriz[j][j] == 1)
            {
                return false;
            }
            // Buscamos todos los nodos adyacentes que no fueron coloreados
            for (int y=0; y<matriz.length; ++y)
            {
                // Existe un nodo de 'j' a 'y' y el destino no está coloreado
                if(matriz[j][y]==1 && colorArr[y]==-1)
                {
                    // Asignamos otro color para el nodo 'y' adyacente a 'j'
                    colorArr[y] = 1-colorArr[j];
                    q.add(y);
                }
                 // 'j' y 'y' son adyacentes y están coloreados del mismo color
                 else
                 {
                    if(matriz[j][y]==1 && colorArr[y]==colorArr[j])
                     {
                        return false;
                     }
                }
            }
        }
        // Todos los vertices adyacentes pudieron ser coloreados de diferente color
        return true;
      }

    /**
       @param nodo, tomaremos los hijos de un nodo
     */
    public void herederos(int nodo){

            if(grupoA.indexOf(nodo)==-1&&grupoB.indexOf(nodo)==-1)
            {
               grupoA.add(nodo);
            }
            ArrayList<Integer> hijos =  getSuccessors(nodo);
            for(int j =0;j<hijos.size();j++)
            {
                //Si un nodo esta en una lista entonces sus hijos deberán ir a la otra
                if(grupoA.indexOf(nodo)!=-1)
                {
                    grupoB.add(hijos.get(j));
                }else
                    {
                      if(grupoB.indexOf(nodo)!=-1)
                      {
                          grupoA.add(hijos.get(j));
                      }
                    }
            }


    }


    /**Lo que haremos es dividir los vértices en dos conjuntos de tal manera que ninguno quede en el mismo grupo con un hijo suyo
      Si al fin en uno de los dos grupos dos nodos padre e hijo quedan juntos se comprobará que el grafo no es bipartito
      si el grafo no es bipartito no se puede pintar con dos colores
    */
   public boolean Divisible()
    {
        for(int i = 0;i<matriz.length;i++){
            herederos(i);
        }

        //Buscamos si en el grupo 'A' dos nodos se relacionan entre si, si esto sucede entonces el grafo no será bipartito
        //Y por lo tanto no se puede pintar con dos colores
        for(int i= 0;i<grupoA.size();i++)
         {
                     for(int j=0;j<grupoA.size();j++)
                    {
                            if(matriz[grupoA.get(i)][grupoA.get(j)]==1 && i != j)
                             {
                                 return false;
                             }

                    }
         }

        //Buscamos si en el grupo 'B' dos nodos se relacionan entre si, si esto sucede entonces el grafo no será bipartito
        //Y por lo tanto no se puede pintar con dos colores

          for(int i= 0;i<grupoB.size();i++)
          {
               for(int j=0;j<grupoB.size();j++)
               {
                            if(matriz[grupoB.get(i)][grupoB.get(j)]==1 && i != j)
                             {
                                return false;
                             }
               }

           }

          //Si los nodos se pudieron dividir en dos conjuntos y en cada conjunto ninguno está relacionado directamente
          //con otro elemento entonces el grafo es bipartito y por lo tanto se puede pintar con dos colores

              return true;

    }

    //Método para extraer los hijos de un nodo seleccionado
   public ArrayList<Integer> getSuccessors(int vertex) {
        // por cada fila, las columnas que no sean cero
        ArrayList<Integer> respuesta = new ArrayList<Integer>();
        for (int j = 0; j < matriz.length; j++) {
            if (matriz[vertex][j] == 1) {
                respuesta.add(j); // error matriz[vertex][j]
            }
        }
        return respuesta;
    }

    //Mostraremos la matriz para mayor guia del usuario
    public void mostrarMatriz()
   {
       System.out.print("   ");
        for(int i = 0; i<matriz.length;i++)
       {
          System.out.print("["+i+"]");
       }

        for(int i = 0; i<matriz.length;i++)
       {
           System.out.print("\n["+i+"] ");
           for(int j = 0; j<matriz.length;j++)
           {
               System.out.print(matriz[i][j]+"  ");
           }
       }


   }


}

