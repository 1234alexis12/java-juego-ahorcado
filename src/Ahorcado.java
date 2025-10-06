import java.util.Scanner;

public class Ahorcado {
    public static void main(String[] args) throws Exception {
        // Clase Scanner que nos permite que el usuario ingrese datos por teclado
        Scanner entrada = new Scanner(System.in);

        // ========== DECLARACIONES Y ASIGNACIONES ==========
        // La palabra que el jugador debe adivinar
        String palabraSecreta = "inteligencia";
        // Número máximo de intentos fallidos permitidos antes de perder
        int intentosMaximos = 6;
        // Contador de intentos fallidos actuales
        int intentos = 0;
        // Bandera que indica si la palabra ha sido completamente adivinada
        boolean palabraAdivinada = false;

        // ========== ARREGLOS ==========
        // Arreglo de caracteres para almacenar las letras adivinadas
        // Tiene el mismo tamaño que la palabra secreta
        char[] letrasAdivinadas = new char[palabraSecreta.length()];
        
        // Estructura de control: Iterativa (Bucle for)
        // Este bucle inicializa el arreglo con guiones bajos '_'
        // representando las letras que aún no han sido adivinadas
        for (int i = 0; i < letrasAdivinadas.length; i++) {
            letrasAdivinadas[i] = '_';
            // System.out.print(letrasAdivinadas[i]); // Línea comentada para debug
        }
        
        // ========== BUCLE PRINCIPAL DEL JUEGO ==========
        // El juego continúa mientras:
        // 1. La palabra NO haya sido adivinada completamente
        // 2. Y aún queden intentos disponibles
        while(!palabraAdivinada && intentos < intentosMaximos) {
            // Muestra el progreso actual: letras adivinadas y espacios por adivinar
            System.out.println("Palabra a adivinar: " + String.valueOf(letrasAdivinadas));
            System.out.println("Introduce una letra, por favor");
            
            // Usamos la clase Scanner para pedir una letra al usuario
            // next() obtiene la siguiente palabra ingresada
            // charAt(0) obtiene el primer carácter de esa palabra
            // Character.toLowerCase() convierte la letra a minúscula para evitar problemas de mayúsculas
            char letraIngresada = Character.toLowerCase(entrada.next().charAt(0));
            
            // Bandera para verificar si la letra ingresada está en la palabra secreta
            boolean letraCorrecta = false;
            
            // Estructura de control: Iterativa (Bucle for)
            // Recorre cada posición de la palabra secreta
            for (int i = 0; i < palabraSecreta.length(); i++) {
                // Estructura de control: Condicional (if)
                // Compara si el carácter en la posición i de la palabra secreta
                // coincide con la letra ingresada por el usuario
                if (palabraSecreta.charAt(i) == letraIngresada) {
                    // charAt(i) nos permite obtener el caracter en la posicion i
                    // Si hay coincidencia, revela esa letra en el arreglo de letras adivinadas
                    letrasAdivinadas[i] = letraIngresada;
                    // Marca que la letra fue encontrada
                    letraCorrecta = true;
                }
            }
            
            // Si la letra NO estaba en la palabra secreta
            if(!letraCorrecta) {
                // Incrementa el contador de intentos fallidos
                intentos++;
                // Informa al usuario cuántos intentos le quedan
                System.out.println("¡Incorrecto! Te quedan " + (intentosMaximos - intentos) + " intentos.");
            }
            
            // Verifica si el jugador ha completado toda la palabra
            // String.valueOf() convierte el arreglo de caracteres a String para poder compararlo
            // equals() compara si ambas cadenas son idénticas
            if (String.valueOf(letrasAdivinadas).equals(palabraSecreta)) {
                // Marca que la palabra fue adivinada exitosamente
                palabraAdivinada = true;
                // Mensaje de victoria
                System.out.println("¡Felicidades! Has adivinado la palabra: " + palabraSecreta);
            }
        }
        
        // ========== CONDICIÓN DE DERROTA ==========
        // Si se terminaron los intentos sin adivinar la palabra
        if (!palabraAdivinada) {
            // Mensaje de derrota revelando la palabra secreta
            System.out.println("Has perdido. La palabra era: " + palabraSecreta);
        }
        
        // Cierra el objeto Scanner para liberar recursos
        entrada.close();
    }
}