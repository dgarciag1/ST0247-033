import java.util.Scanner;

public class MainBicolorable
{
     public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese el número de nodos que desea: ");
        int size = in.nextInt();
        //Para que sea considerado un grafo el numero de nodos debe ser mayor a '1'
        if(size>1){
        DigraphAM B = new DigraphAM(size);
        }else
            {
                System.out.println("El número de nodos debe ser por lo menos mayor a 1 ");
            }
    }


}

