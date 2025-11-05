public class ArreglosDobles {
    public static void main(String[] args) {
        // Declaración e inicialización de un arreglo bidimensional
        int[][] matriz = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        // Imprimir los elementos del arreglo bidimensional
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println(); // Nueva línea después de cada fila
        }
    }
}
