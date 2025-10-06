
import java.util.Scanner;

public class practica {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String palabraSecreta = "manguito";
        int intentosMaximos = 6;
        int intentos = 0;
        boolean palabraCondicionAdivinada = false;
        char[] letrasAdivinadasTalla = new char[palabraSecreta.length()];
        for (int i = 0; i < letrasAdivinadasTalla.length; i++) {
            letrasAdivinadasTalla[i] = '_';
        }
        while(!palabraCondicionAdivinada && intentos < intentosMaximos) {
            System.out.println("Palabra a adiviar: " + String.valueOf(letrasAdivinadasTalla));
            System.out.println("Introduce una letra, por favor");
            char letraIngresada = Character.toLowerCase(entrada.next().charAt(0));
            boolean letraCorrecta = false;
            for (int i = 0; i < palabraSecreta.length(); i++){
                if (palabraSecreta.charAt(i) == letraIngresada) {
                    letrasAdivinadasTalla[i] = letraIngresada;
                    letraCorrecta = true;
                }
            }
            if (!letraCorrecta) {
                intentos++;
                System.out.println("Letra incorrecta. Te quedan " + (intentosMaximos - intentos) + " intentos.");
            }
            if (String.valueOf(letrasAdivinadasTalla).equals(palabraSecreta)) {
                palabraCondicionAdivinada = true;
                System.out.println("¡Felicidades! Has adivinado la palabra: " + palabraSecreta);
            }
        }
        if (!palabraCondicionAdivinada) {
            System.out.println("Has perdido. La palabra era: " + palabraSecreta);
        }
        entrada.close();
    }
}
