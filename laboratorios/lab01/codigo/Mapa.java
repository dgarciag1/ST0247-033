/**
* Esta clase debe de contener la solucion al problema planteado en el punto 1
* del laboratorio#1 del curso de estructura de datos y algoritmos II
* @author Daniel Garcia
* @version 1.0
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; //por teclado
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
public class Mapa
{
  /**
  * El metodo debe de leer el archivo y construir la estrcutura de datos con el mapa
  *
  */
    public Mapa(){
        try{
            makeMap();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
 //Metodo donde crearemos el mapa a través de la estructura de datos conocida como HashMap
    public void makeMap() throws IOException
  {

      HashMap<Long, LinkedList<Pair<Long,Double>>> vertice = new HashMap<>();
      String nombreArchivo = "mapaMedellin.txt";
      boolean nodo = true;

      try{
        //Leemos el archivo por lineas (para esto hacemos el ciclo)
        BufferedReader  br = new BufferedReader(new FileReader(nombreArchivo));
        String linea = br.readLine();
        linea = br.readLine();
        while(linea != null){
          //Dividimos cada linea por espacios, asi sabremos diferenciar un dato de otro
           String[] item= linea.split(" ");
          //Si la linea esta vacia simplemente pasamos a leer la siguiente linea 
          if(linea.isEmpty()){
               linea = br.readLine();
           }

             //Cuando el programa encuentre la palabra arcos entonces insertaremos los datos correspondientes
             //En este caso insertaremos el ID de cada nodo adyacente y la distancia entre ellos
             if(linea.contains("Arcos")){
                  nodo = false;
                  linea = br.readLine();
             }
              
          //Creamos los nodos y cuando hayan arcos ingresaremos los valores
             if(nodo == true){
                 long ID1 = Long.parseLong(item[0]);
                 LinkedList<Pair<Long, Double>> adyacente = new LinkedList();
                 vertice.put(ID1, adyacente);
                 linea = br.readLine();
             }else{
               item= linea.split(" ");
               long ID1 = Long.parseLong(item[0]);
               //Utilizaremos el metodo 'aristas()' que nos devolvera una lista de todos los nodos adyacentes al nodo previamente indicado
               LinkedList<Pair<Long,Double>> m = aristas(ID1);
               vertice.put(ID1, m);
             }

             linea = br.readLine();
        }




        System.out.println(vertice);
      }catch(IOException ioe){
          System.out.println("El archivo no existe");
      }
  }

   //Este metodo nos devolvera el LinkedList con los nodos adyacentes del nodo que queramos saber
    public  LinkedList<Pair<Long, Double>> aristas(long ID1) throws IOException
  {
      LinkedList<Pair<Long, Double>> elementos = new LinkedList();;
      long r = ID1;
      String nombreArchivo = "mapaMedellin.txt";
      boolean nodo = true;
      try{
        BufferedReader  br = new BufferedReader(new FileReader(nombreArchivo));
        String linea = br.readLine();
        linea = br.readLine();
        while(linea != null){

           String[] item= linea.split(" ");

           if(linea.isEmpty()){
               linea = br.readLine();
           }


             if(linea.contains("Arcos")){
                  nodo = false;
                  linea = br.readLine();
             }

             if(nodo == false){
                 item= linea.split(" ");
                 long n = Long.parseLong(item[0]);
                 //Si el nodo de inicio corresponde al nodo que ingreso el usuario entonces nos interesa saber con cuales nodos es adyacentes
                 //Y la distancia entre ellos, esta informacion la almacenaremos en una lista
                 if(n == r){
                      long ID2 = Long.parseLong(item[1]);
                      Double distancia = Double.parseDouble(item[2]);
                      elementos.add(new Pair(ID2, distancia));
                 }

             }

             linea = br.readLine();
        }


      }catch(IOException ioe){
          System.out.println("El archivo no existe");
      }

    return elementos;
  }




  /**
  * Metodo principal del programa
  * @param args un array de argumentos
  */
  public static void main(String[] args) throws IOException
  {
     Mapa m = new Mapa();
  }
}
