import java.util.Scanner;

public class CalculadoraPromedio {
    public static void main(String[] args) {
        // ========== INICIALIZACIÓN ==========
        // Objeto Scanner para recibir entrada del usuario
        Scanner entrada = new Scanner(System.in);
        
        // Variable para almacenar el número de materias que cursó el estudiante
        int numeroMaterias;
        
        // Acumulador para sumar todas las calificaciones ingresadas
        double sumaCalificaciones = 0;
        
        // Variable para el promedio final calculado
        double promedio;
        
        // Contador de materias aprobadas (calificación >= 6.0)
        int materiasAprobadas = 0;
        
        // Contador de materias reprobadas (calificación < 6.0)
        int materiasReprobadas = 0;
        
        // Variable para almacenar la calificación más alta obtenida
        double calificacionMaxima = 0;
        
        // Variable para almacenar la calificación más baja obtenida
        // Se inicializa en 10 porque es la nota máxima posible
        double calificacionMinima = 10;
        
        // ========== BIENVENIDA Y SOLICITUD DE DATOS ==========
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  CALCULADORA DE PROMEDIO ACADÉMICO    ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        
        // Solicita el número de materias al usuario
        System.out.print("¿Cuántas materias cursaste este semestre? ");
        numeroMaterias = entrada.nextInt();
        
        // ========== VALIDACIÓN DE ENTRADA ==========
        // Estructura de control: Condicional (if)
        // Verifica que el número de materias sea válido (mayor a 0)
        if (numeroMaterias <= 0) {
            System.out.println("❌ Error: Debes ingresar al menos 1 materia.");
            entrada.close();
            return; // Termina la ejecución del programa
        }
        
        // Arreglo para almacenar todas las calificaciones
        // El tamaño del arreglo es igual al número de materias ingresado
        double[] calificaciones = new double[numeroMaterias];
        
        // Arreglo para almacenar los nombres de las materias
        String[] nombresMaterias = new String[numeroMaterias];
        
        System.out.println("\n--- Ingresa las calificaciones (escala 0-10) ---");
        
        // ========== INGRESO DE CALIFICACIONES ==========
        // Estructura de control: Iterativa (Bucle for)
        // Recorre cada materia para solicitar su nombre y calificación
        for (int i = 0; i < numeroMaterias; i++) {
            // Solicita el nombre de la materia actual
            System.out.print("\nNombre de la materia " + (i + 1) + ": ");
            // nextLine() para limpiar el buffer después de nextInt()
            if (i == 0) entrada.nextLine();
            nombresMaterias[i] = entrada.nextLine();
            
            // Variable temporal para validar la calificación ingresada
            double calificacionTemp;
            
            // Estructura de control: Iterativa (Bucle do-while)
            // Se repite hasta que se ingrese una calificación válida
            do {
                System.out.print("Calificación de " + nombresMaterias[i] + ": ");
                calificacionTemp = entrada.nextDouble();
                
                // Validación: la calificación debe estar entre 0 y 10
                if (calificacionTemp < 0 || calificacionTemp > 10) {
                    System.out.println("⚠️  Calificación inválida. Debe estar entre 0 y 10.");
                }
            } while (calificacionTemp < 0 || calificacionTemp > 10);
            
            // Almacena la calificación validada en el arreglo
            calificaciones[i] = calificacionTemp;
            
            // Suma la calificación al acumulador para calcular el promedio después
            sumaCalificaciones += calificaciones[i];
            
            // ========== CONTADORES Y MÁXIMO/MÍNIMO ==========
            // Estructura de control: Condicional (if-else)
            // Determina si la materia fue aprobada o reprobada
            if (calificaciones[i] >= 6.0) {
                materiasAprobadas++;
            } else {
                materiasReprobadas++;
            }
            
            // Actualiza la calificación máxima si la actual es mayor
            if (calificaciones[i] > calificacionMaxima) {
                calificacionMaxima = calificaciones[i];
            }
            
            // Actualiza la calificación mínima si la actual es menor
            if (calificaciones[i] < calificacionMinima) {
                calificacionMinima = calificaciones[i];
            }
        }
        
        // ========== CÁLCULO DEL PROMEDIO ==========
        // Divide la suma total entre el número de materias
        // Se hace un cast a double para evitar división entera
        promedio = sumaCalificaciones / numeroMaterias;
        
        // ========== MOSTRAR RESULTADOS ==========
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║           RESULTADOS FINALES           ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        // Muestra todas las calificaciones ingresadas
        System.out.println("\n📋 Calificaciones por materia:");
        for (int i = 0; i < numeroMaterias; i++) {
            // Formato con printf para alinear los datos
            System.out.printf("   %d. %-25s %.2f\n", (i + 1), nombresMaterias[i], calificaciones[i]);
        }
        
        // Muestra el promedio con dos decimales usando printf
        System.out.printf("\n📊 Promedio General: %.2f\n", promedio);
        System.out.println("📈 Calificación más alta: " + calificacionMaxima);
        System.out.println("📉 Calificación más baja: " + calificacionMinima);
        System.out.println("✅ Materias aprobadas: " + materiasAprobadas);
        System.out.println("❌ Materias reprobadas: " + materiasReprobadas);
        
        // ========== EVALUACIÓN DEL DESEMPEÑO ==========
        // Estructura de control: Condicional (if-else if-else)
        // Determina el nivel de desempeño según el promedio obtenido
        System.out.println("\n🎯 Evaluación de desempeño:");
        if (promedio >= 9.0) {
            System.out.println("   ¡EXCELENTE! Sigue así, vas por buen camino.");
        } else if (promedio >= 8.0) {
            System.out.println("   MUY BIEN. Buen rendimiento académico.");
        } else if (promedio >= 7.0) {
            System.out.println("   BIEN. Rendimiento aceptable.");
        } else if (promedio >= 6.0) {
            System.out.println("   SUFICIENTE. Considera estudiar más.");
        } else {
            System.out.println("   INSUFICIENTE. Necesitas mejorar tu rendimiento.");
        }
        
        // ========== ESTADÍSTICAS ADICIONALES ==========
        // Calcula el porcentaje de materias aprobadas
        double porcentajeAprobadas = (materiasAprobadas * 100.0) / numeroMaterias;
        System.out.printf("\n📊 Porcentaje de aprobación: %.1f%%\n", porcentajeAprobadas);
        
        // Cierra el Scanner para liberar recursos del sistema
        entrada.close();
        
        System.out.println("\n¡Gracias por usar el sistema! 👋");
    }
}