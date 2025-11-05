public class Arreglos {
    public static void main(String[] args) {
        // Declaración e inicialización de un arreglo unidimensional
        int[] numeros = {10, 20, 30, 40, 50};

        // Imprimir los elementos del arreglo unidimensional
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Elemento en el índice " + i + ": " + numeros[i]);
        }

        int [] copiaNumeros = new int[numeros.length];
        // Copiar elementos de un arreglo a otro
        for (int i = 0; i < numeros.length; i++) {
            copiaNumeros[i] = numeros[i];
        }
        System.out.println("Elementos del arreglo copiado:");
        for (int i = 0; i < copiaNumeros.length; i++) {
            System.out.println("Elemento en el índice " + i + ": " + copiaNumeros[i]);
        }
    }
}
