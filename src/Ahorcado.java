import java.util.Scanner;

public class Ahorcado {
    public static void main(String[] args) throws Exception {
        //clase scanner que nos permite que el usuario ingrese datos
        Scanner entrada = new Scanner(System.in);

        //declaraciones y asignaciones
        String palabraSecreta = "inteligencia";
        int intentosMaximos = 6;
        int intentos = 0;
        boolean palabraAdivinada = false;

        //Arreglos
        char[] letrasAdivinadas = new char[palabraSecreta.length()];
        //estructura de control: Iterativa (Bucle for)
        for (int i= 0; i < letrasAdivinadas.length; i++) {
            letrasAdivinadas[i] = '_';
            //System.out.print(letrasAdivinadas[i]);
        }
        while(!palabraAdivinada && intentos < intentosMaximos) {
            System.out.println("Palabra a adivinar: " + String.valueOf(letrasAdivinadas));
            System.out.println("Introduce una letra, por favor");
            //usamos la clase scanner para pedir una letra
            //char letraIngresada = entrada.next().charAt(0);
            char letraIngresada = Character.toLowerCase(entrada.next().charAt(0));
            boolean letraCorrecta = false;
            //estructura de control: Iterativa (Bucle for)
            for (int i = 0; i < palabraSecreta.length(); i++) {
                //estrucura de control: Condicional (if)
                if (palabraSecreta.charAt(i) == letraIngresada) {
                    // charAt(i) nos permite obtener el caracter en la posicion i
                    letrasAdivinadas[i] = letraIngresada;
                    letraCorrecta = true;
                }
            }
            if(!letraCorrecta) {
                intentos++;
                System.out.println("¡Incorrecto! Te quedan " + (intentosMaximos - intentos) + " intentos.");
            }
            if (String.valueOf(letrasAdivinadas).equals(palabraSecreta)) {
                palabraAdivinada = true;
                System.out.println("¡Felicidades! Has adivinado la palabra: " + palabraSecreta);
            }
        }
        if (!palabraAdivinada) {
            System.out.println("Has perdido. La palabra era: " + palabraSecreta);
        }
        entrada.close();
    }
}
