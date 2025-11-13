public class matrizCompleja {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Por favor, proporciona al menos cuatro números como argumentos para formar una matriz 2x2.");
            return;
        }

        double[][] matriz = new double[2][2];
        int index = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                try {
                    matriz[i][j] = Double.parseDouble(args[index]);
                    index++;
                } catch (NumberFormatException e) {
                    System.out.println("El argumento '" + args[index] + "' no es un número válido.");
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
    }
}
