import java.util.ArrayList;

/**
 *
 * @authorMauricio Toro, Camilo Paez y Daniel García, apoyandome de el repositorio de https://github.com/vsuarezm
 */
public class Taller2 {

    public static void main(String[] args) {
        System.out.println("Las combinaciones posibles son las siguientes: \n");
        combinations("ABCD");
        System.out.println("Las permutaciones posibles son las siguientes: \n");
        permutations("Ad1#");
    }

    /**
        * Metodo público que llama al metodo combinations posteriormente
        *
        * @param str cadena sobre el cual se haran las combinaciones (o subconjuntos)

        */

    public static ArrayList<String> combinations(String str) {
         ArrayList<String> list = new ArrayList<String>();
         combinations("", str, list);
         return list;
         }

     /**
        * Metodo para obtener las posibles combinaciones (o subconjuntos) que se pueden hacer
        * con los elementos dados
        *
        * @param  loQueLlevo cadena de caracteres con lo que ya se tiene del subconjunto
        * @param loQueMeFalta cadena de caracteres con lo que me falta por calcular del subconjunto
        * @param list lista de cadenas para almacenar las combinaciones (o subconjuntos)
        *
        */

     private static void combinations(String loQueLlevo, String loQueMeFalta, ArrayList<String> list) {
           if (loQueMeFalta.length() == 0) {
               list.add(loQueLlevo);
               System.out.println(loQueLlevo);
           } else {
               combinations(loQueLlevo, loQueMeFalta.substring(1), list);
               combinations(loQueLlevo + loQueMeFalta.charAt(0), loQueMeFalta.substring(1), list);
           }
     }


     /**
        * Metodo auxiliar que llama al metodo permutations posterios
        *
        * @param  s la cadena a la cual se le haran las permutaciones
        * @return un ArrayList que contiene las permutaciones
        */



    public static ArrayList<String> permutations(String s) {
        ArrayList<String> list = new ArrayList<String>();
        permutations("", s, list);
        return list;

    }


    /**
        * Metodo para obtener las posibles permutaciones que se pueden hacer
        * con los caracteres de una cadena dada, recuerde que las letras no se
        * repiten en este ejercicio
        *
        * @param  loQueLlevo parte de la cadena que hace parte de la permutacion
        * @param loQueMeFalta parte de cadena que falta por calcular en una permutacion
        * @param list el conjunto que tiene todas las permutaciones
        *
        */

    private static void permutations(String loQueLlevo, String loQueMeFalta, ArrayList<String> list) {
        /*Si el string del lado derecho está vacío quiere decir que de una u otra manera
          en el lado izquierdo ya están todos los caracteres ordenados de alguna manera distinta
          por lo que lo agrego como una nueva permutacion
         */
        if (loQueMeFalta.length() == 0) {
            list.add(loQueLlevo);
            System.out.println(loQueLlevo);

        }
        else {
            /*De lo contrario a través de un ciclo con recursión seguiremos combinando las letras
             hasta que se acaben todas las combinaciones posibles (n veces tal que n es la logitud de la cadena)
             */
            for (int i = 0; i < loQueMeFalta.length(); i++) {
                permutations(loQueLlevo + loQueMeFalta.charAt(i), loQueMeFalta.substring(0, i) + loQueMeFalta.substring(i + 1), list);
            }
        }

    }

}
