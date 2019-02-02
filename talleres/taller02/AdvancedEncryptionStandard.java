import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
/**
 *
 * @author Daniel García
 */

//Tomado de
//https://stackoverflow.com/questions/15554296/simple-java-aes-encrypt-decrypt-example
public class AdvancedEncryptionStandard
{


        public static void main(String[] args) {
            permutations("","abcd");
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

    private static void permutations(String loQueLlevo, String loQueMeFalta) {
        /*Si el string del lado derecho está vacío quiere decir que de una u otra manera
          en el lado izquierdo ya están todos los caracteres ordenados de alguna manera distinta
          por lo que lo agrego como una nueva permutacion
         */
        if (loQueMeFalta.length() == 0) {
            desencriptarArchivo(loQueLlevo);
        }
        else {
            /*De lo contrario a través de un ciclo con recursión seguiremos combinando las letras
             hasta que se acaben todas las combinaciones posibles (n veces tal que n es la logitud de la cadena)
             */
            for (int i = 0; i < loQueMeFalta.length(); i++) {
                permutations(loQueLlevo + loQueMeFalta.charAt(i), loQueMeFalta.substring(0, i) + loQueMeFalta.substring(i + 1));
            }
        }

    }


    /**
     * Decrypts the given byte array
     *
     * @param cipherText The data to decrypt
     */
    public static byte[] decrypt(byte[] cipherText, String password)
    {
        try{
          String key = "MZygpewJsCpR"+password;
          byte[] keyInBytes = key.getBytes(StandardCharsets.UTF_8);
          SecretKeySpec secretKey = new SecretKeySpec(keyInBytes, "AES");
          Cipher cipher = Cipher.getInstance("AES");
          cipher.init(Cipher.DECRYPT_MODE, secretKey);
          return cipher.doFinal(cipherText);
        }
        catch(Exception e)
        {
            return new byte[0];
        }
    }

    /**
     * Desencripta el archivo archivoEncriptado.txt con el password. Si el password falla retorna una cadena vacia
     *
     * @param password El password para desencriptar el archivo es una permutacion de la cadena abcd
     * @return Retorna una cadena con el contenido del archivo desencriptado
     */
    public static String desencriptarArchivo(String password)
    {
         try{
         Path path = Paths.get("archivoEncriptado.txt");
         byte[] archivoEncriptado = Files.readAllBytes(path);
         byte[] decryptedCipherText = AdvancedEncryptionStandard.decrypt(archivoEncriptado,password);
         System.out.println(decryptedCipherText.toString());
         return new String(decryptedCipherText);
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
            return  "";
        }
    }
}
