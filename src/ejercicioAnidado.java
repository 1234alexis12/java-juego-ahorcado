public class ejercicioAnidado {
    public static void main(String[] args) {
        if (args.length > 0) {
            String color = args[0];

            if (color.equalsIgnoreCase("rojo")) {
                System.out.println("El color es rojo.");
            } else if (color.equalsIgnoreCase("verde")) {
                System.out.println("El color es verde.");
            } else if (color.equalsIgnoreCase("azul")) {
                System.out.println("El color es azul.");
            } else {
                System.out.println("Color no reconocido.");
            }
        } else {
            System.out.println("Por favor, proporciona un color como argumento.");
        }
        
    }
}
