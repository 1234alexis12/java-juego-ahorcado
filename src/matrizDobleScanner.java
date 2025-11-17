public class matrizDobleScanner {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Por favor, proporciona al menos cuatro números para llenar una matriz 2x2:");
            return;
        }

        int[][] matriz = new int[2][2];
        int indice = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                try {
                    matriz[i][j] = Integer.parseInt(args[indice]);
                    indice++;
                } catch (NumberFormatException e) {
                    System.out.println("El argumento '" + args[indice] + "' no es un número válido.");
                    return;
                }
            }
        }

        System.out.println("La matriz 2x2 es:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Programa finalizado.");
    }
}
