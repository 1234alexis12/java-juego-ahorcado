public class switchUSO {
    public static void main(String[] args) {
        int numero = 3;
        String dia;

        switch (numero) {
            case 1:
                dia = "Lunes";
                break;
            case 2:
                dia = "Martes";
                break;
            case 3:
                dia = "Miércoles";
                break;
            case 4:
                dia = "Jueves";
                break;
            case 5:
                dia = "Viernes";
                break;
            case 6:
                dia = "Sábado";
                break;
            case 7:
                dia = "Domingo";
                break;
            default:
                dia = "Número inválido";
                break;
        }

        System.out.println("El día correspondiente al número " + numero + " es: " + dia);
        
    }
}
