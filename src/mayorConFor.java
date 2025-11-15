public class mayorConFor {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Por favor, proporciona al menos tres números como argumentos.");
            return;
        }

        int mayor = Integer.MIN_VALUE;

        for (int i = 0; i < args.length; i++) {
            try {
                int numero = Integer.parseInt(args[i]);
                if (numero > mayor) {
                    mayor = numero;
                }
            } catch (NumberFormatException e) {
                System.out.println("El argumento '" + args[i] + "' no es un número válido.");
                return;
            }
        }

        System.out.println("El número mayor es: " + mayor);
        System.out.println("Programa finalizado.");
    }
}
