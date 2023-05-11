import java.io.*;
import java.util.*;

/**
 * @author Miguel Ángel Martínez Guijarro y Javier Tomás Vicente - Grupo 2I
 */
public class EjercicioProblema {


    final static String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Creamos las variables que vamos a utilizar en nuestro programa y las inicializamos
        BufferedReader br = null;
        BufferedWriter bw = null;
        String lectura = "";
        String clave = "";
        String cifrado = "";
        char concatenacion = '0';
        char concatenacion2 = '0';
        char concatenacionFinal = '0';
        int desplazamientoClave = 0;
        int suma1 = 0;
        int suma2 = 0;
        int sumaTotal = 0;

        try {
            br = new BufferedReader(new FileReader("mensaje.txt"));
            bw = new BufferedWriter(new FileWriter("mensaje_cifrado.txt"));

            String linea = null;

            /* Lectura y validación  de clave */
            // Primero leemos el texto que queremos cifrar.
            do {
                System.out.print("\nDime el mensaje: ");
                lectura = sc.nextLine();
                if (lectura.isBlank() || lectura.isEmpty()) {
                    System.err.println("El mensaje no puede estar vacío");
                } else {
                    break;
                }
            } while (true);


            // Ahora pedimos la clave que nos va a servir para desplazar cada carácter del texto.
            do {
                System.out.print("\nAhora dime la clave: ");
                clave = sc.nextLine();
                if (clave.isBlank() || clave.isEmpty()) {
                    System.err.println("La clave no puede estar vacía");
                } else {
                    break;
                }
            } while (true);


            while ((linea = br.readLine()) != null) {
                StringBuilder sb = new StringBuilder(linea.length());

                /* Aquí vendría la lógica del programa */

                for (int i = 0; i < lectura.length(); i++) {
                    concatenacion = lectura.toUpperCase().charAt(i);
                    if (!esAlfabetico(concatenacion)) {
                        cifrado += concatenacion;
                    } else{
                        if (desplazamientoClave == clave.length() - 1) {
                            desplazamientoClave = 0;
                        }
                        concatenacion2 = cifrado.toUpperCase().charAt(desplazamientoClave);

                        suma1 = asignarNumero(concatenacion);
                        suma2 = asignarNumero(concatenacion2);

                        sumaTotal = suma1 + suma2;

                        concatenacionFinal = asignarLetra(sumaTotal);
                        cifrado += concatenacionFinal;

                    }

                }

                bw.write(sb.toString()); /* Escribe la cadena de caracteres en el fichero*/
                bw.newLine(); /* escribe nueva línea en el fichero */

            }

            System.out.println("El mensaje ha sido cifrado correctamente");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bw != null) try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static int asignarNumero(char letra) {
        int contador = 0;

        for (int i = 0; i < letras.length(); i++) {
            if (letras.charAt(i) == letra) {
                return contador;
            } else {
                contador++;
            }
        }

        return contador;
    }

    public static char asignarLetra(int suma) {
        if (suma > 25) {
            suma -= 25;
        }

        char devolver = letras.charAt(suma);
        return devolver;
    }

    public static boolean esAlfabetico(char caracter) {
        if (caracter < (char) 65 && caracter > (char) 90) {
            return false;
        } else {
            return true;
        }
    }

}