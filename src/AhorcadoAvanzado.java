import java.util.Random;
import java.util.Scanner;

public class AhorcadoAvanzado {
    // ========== CONSTANTES DEL SISTEMA 1 ==========
    // Número máximo de intentos fallidos permitidos
    private static final int INTENTOS_MAXIMOS = 6;
    // Número máximo de palabras en la base de datos
    private static final int CAPACIDAD_PALABRAS = 50;
    // Número máximo de jugadores que pueden jugar
    private static final int MAX_JUGADORES = 10;
    
    // ========== VARIABLES GLOBALES ==========
    // Base de datos de palabras organizadas por categorías
    private static String[][] palabrasPorCategoria = {
        // Categoría: Animales
        {"elefante", "jirafa", "pinguino", "cocodrilo", "mariposa", "serpiente", "aguila", "delfin"},
        // Categoría: Países
        {"argentina", "japon", "noruega", "tailandia", "australia", "brasil", "italia", "españa"},
        // Categoría: Frutas
        {"manzana", "platano", "fresa", "sandia", "melocoton", "frambuesa", "kiwi", "coco"},
        // Categoría: Películas
        {"avatar", "titanic", "matrix", "inception", "interestelar", "gladiador", "acoso", "viaje"},
        // Categoría: Profesiones
        {"ingeniero", "medico", "abogado", "profesor", "pintor", "musico", "cocinero", "piloto"}
    };
    
    // Nombres de las categorías
    private static String[] nombresCategorias = {
        "Animales",
        "Países",
        "Frutas",
        "Películas",
        "Profesiones"
    };
    
    // ========== CLASE: Jugador ==========
    // Representa a un jugador con sus estadísticas
    static class Jugador {
        // Nombre del jugador
        String nombre;
        // Total de juegos jugados
        int juegosJugados;
        // Total de juegos ganados
        int juegosGanados;
        // Total de juegos perdidos
        int juegosPerdidos;
        // Racha actual de victorias
        int rachaVictorias;
        // Racha actual de derrotas
        int rachaDerrotas;
        
        // Constructor: Inicializa un nuevo jugador
        Jugador(String nombre) {
            this.nombre = nombre;
            this.juegosJugados = 0;
            this.juegosGanados = 0;
            this.juegosPerdidos = 0;
            this.rachaVictorias = 0;
            this.rachaDerrotas = 0;
        }
        
        // Método: Registrar una victoria
        void registrarVictoria() {
            juegosJugados++;
            juegosGanados++;
            rachaVictorias++;
            rachaDerrotas = 0; // Reinicia la racha de derrotas
        }
        
        // Método: Registrar una derrota
        void registrarDerrota() {
            juegosJugados++;
            juegosPerdidos++;
            rachaDerrotas++;
            rachaVictorias = 0; // Reinicia la racha de victorias
        }
        
        // Método: Calcular el porcentaje de victorias
        double calcularPorcentajeVictorias() {
            if (juegosJugados == 0) return 0;
            return (juegosGanados * 100.0) / juegosJugados;
        }
        
        // Método: Mostrar estadísticas del jugador
        void mostrarEstadisticas() {
            System.out.printf("┌─────────────────────────────────────────┐\n");
            System.out.printf("│ Jugador: %-30s │\n", nombre);
            System.out.printf("├─────────────────────────────────────────┤\n");
            System.out.printf("│ Juegos jugados: %-23d │\n", juegosJugados);
            System.out.printf("│ Victorias: %-28d │\n", juegosGanados);
            System.out.printf("│ Derrotas: %-29d │\n", juegosPerdidos);
            System.out.printf("│ Porcentaje ganado: %-20.1f%% │\n", calcularPorcentajeVictorias());
            System.out.printf("│ Racha actual: %-25d │\n",
                    (rachaVictorias > 0) ? rachaVictorias : -rachaDerrotas);
            System.out.printf("└─────────────────────────────────────────┘\n");
        }
    }
    
    // ========== VARIABLES GLOBALES ==========
    // Arreglo de jugadores registrados en el sistema
    private static Jugador[] jugadores = new Jugador[MAX_JUGADORES];
    // Contador de jugadores registrados
    private static int totalJugadores = 0;
    // Generador de números aleatorios
    private static Random aleatorio = new Random();
    
    public static void main(String[] args) {
        // ========== INICIALIZACIÓN ==========
        Scanner entrada = new Scanner(System.in);
        boolean sistemaActivo = true;
        
        // Mensaje de bienvenida
        mostrarBienvenida();
        
        // ========== BUCLE PRINCIPAL DEL SISTEMA ==========
        // Estructura de control: Iterativa (Bucle while)
        while (sistemaActivo) {
            // Muestra el menú principal
            int opcion = mostrarMenuPrincipal(entrada);
            
            // Estructura de control: Condicional (switch-case)
            switch (opcion) {
                case 1:
                    // Inicia un nuevo juego
                    iniciarJuego(entrada);
                    break;
                case 2:
                    // Muestra las estadísticas de todos los jugadores
                    mostrarClasificacion();
                    break;
                case 3:
                    // Ver estadísticas de un jugador específico
                    verEstadisticasJugador(entrada);
                    break;
                case 4:
                    // Salir del sistema
                    sistemaActivo = false;
                    System.out.println("\n👋 ¡Gracias por jugar! Hasta luego.");
                    break;
                default:
                    System.out.println("❌ Opción no válida. Intenta de nuevo.");
            }
        }
        
        entrada.close();
    }
    
    // ========== MÉTODO: Mostrar Bienvenida ==========
    private static void mostrarBienvenida() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    🎮 JUEGO DEL AHORCADO AVANZADO 🎮 ║");
        System.out.println("║   Con Sistema de Estadísticas          ║");
        System.out.println("╚════════════════════════════════════════╝");
    }
    
    // ========== MÉTODO: Mostrar Menú Principal ==========
    // Parámetro: Scanner entrada
    // Retorna: int (opción seleccionada)
    // Propósito: Mostrar el menú principal y obtener la opción del usuario
    private static int mostrarMenuPrincipal(Scanner entrada) {
        System.out.println("\n┌────────────────────────────────────────┐");
        System.out.println("│        MENÚ PRINCIPAL                  │");
        System.out.println("├────────────────────────────────────────┤");
        System.out.println("│ 1. Jugar                              │");
        System.out.println("│ 2. Ver clasificación global           │");
        System.out.println("│ 3. Ver mis estadísticas               │");
        System.out.println("│ 4. Salir                              │");
        System.out.println("└────────────────────────────────────────┘");
        System.out.print("Selecciona una opción: ");
        
        int opcion = entrada.nextInt();
        entrada.nextLine(); // Limpia el buffer
        return opcion;
    }
    
    // ========== MÉTODO: Iniciar Juego ==========
    // Parámetro: Scanner entrada
    // Propósito: Ejecutar un juego completo del ahorcado
    private static void iniciarJuego(Scanner entrada) {
        System.out.println("\n┌────────────────────────────────────────┐");
        System.out.println("│     NUEVO JUEGO DEL AHORCADO          │");
        System.out.println("└────────────────────────────────────────┘");
        
        // Solicita el nombre del jugador
        System.out.print("\n¿Cuál es tu nombre? ");
        String nombreJugador = entrada.nextLine();
        
        // Obtiene o crea el jugador
        Jugador jugadorActual = obtenerJugador(nombreJugador);
        
        // Muestra las categorías disponibles
        System.out.println("\n📚 Elige una categoría:");
        for (int i = 0; i < nombresCategorias.length; i++) {
            System.out.println("  " + (i + 1) + ". " + nombresCategorias[i]);
        }
        System.out.print("Opción: ");
        
        int categoriaSeleccionada = entrada.nextInt() - 1;
        entrada.nextLine();
        
        // Valida la categoría seleccionada
        if (categoriaSeleccionada < 0 || categoriaSeleccionada >= nombresCategorias.length) {
            System.out.println("❌ Categoría no válida.");
            return;
        }
        
        // Obtiene la palabra secreta de la categoría elegida
        String palabraSecreta = obtenerPalabraAleatoria(categoriaSeleccionada);
        
        System.out.println("\n✨ Se ha seleccionado una palabra de la categoría: " + nombresCategorias[categoriaSeleccionada]);
        
        // ========== VARIABLES DE JUEGO ==========
        // Arreglo para mostrar el progreso del jugador
        char[] letrasAdivinadas = new char[palabraSecreta.length()];
        // Inicializa con guiones bajos
        for (int i = 0; i < letrasAdivinadas.length; i++) {
            letrasAdivinadas[i] = '_';
        }
        
        // Contador de intentos fallidos
        int intentosFallidos = 0;
        // Bandera que indica si la palabra fue adivinada
        boolean palabraAdivinada = false;
        // Conjunto de letras ya intentadas (representado como String)
        String letrasIntentadas = "";
        
        // ========== BUCLE PRINCIPAL DEL JUEGO ==========
        // Estructura de control: Iterativa (Bucle while)
        while (!palabraAdivinada && intentosFallidos < INTENTOS_MAXIMOS) {
            // Muestra el estado actual del juego
            mostrarEstadoJuego(letrasAdivinadas, intentosFallidos, letrasIntentadas);
            
            // Solicita una letra al jugador
            System.out.print("\nIngresa una letra: ");
            String entrada_texto = entrada.nextLine().toLowerCase();
            
            // Valida que sea una letra válida
            if (entrada_texto.length() != 1 || !Character.isLetter(entrada_texto.charAt(0))) {
                System.out.println("⚠️  Debes ingresar una sola letra válida.");
                continue;
            }
            
            char letraIngresada = entrada_texto.charAt(0);
            
            // Verifica si la letra ya fue intentada
            if (letrasIntentadas.contains(String.valueOf(letraIngresada))) {
                System.out.println("⚠️  Ya habías intentado con esta letra.");
                continue;
            }
            
            // Agrega la letra al conjunto de intentadas
            letrasIntentadas += letraIngresada;
            
            // Busca la letra en la palabra secreta
            boolean letraCorrecta = false;
            
            // Estructura de control: Iterativa (Bucle for)
            for (int i = 0; i < palabraSecreta.length(); i++) {
                if (palabraSecreta.charAt(i) == letraIngresada) {
                    // Revela la letra en el arreglo
                    letrasAdivinadas[i] = letraIngresada;
                    letraCorrecta = true;
                }
            }
            
            // Si la letra no está en la palabra
            if (!letraCorrecta) {
                intentosFallidos++;
                System.out.println("❌ ¡Incorrecto! No hay esa letra.");
                System.out.println("   Intentos restantes: " + (INTENTOS_MAXIMOS - intentosFallidos));
            } else {
                System.out.println("✅ ¡Correcto! La letra está en la palabra.");
            }
            
            // Verifica si se adivinó la palabra completa
            if (String.valueOf(letrasAdivinadas).equals(palabraSecreta)) {
                palabraAdivinada = true;
            }
        }
        
        // ========== FIN DEL JUEGO ==========
        System.out.println("\n╔════════════════════════════════════════╗");
        
        if (palabraAdivinada) {
            // Mensaje de victoria
            System.out.println("║        🎉 ¡GANASTE! 🎉               ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("📝 La palabra era: " + palabraSecreta);
            jugadorActual.registrarVictoria();
        } else {
            // Mensaje de derrota
            System.out.println("║        😢 ¡PERDISTE! 😢              ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("📝 La palabra era: " + palabraSecreta);
            jugadorActual.registrarDerrota();
        }
        
        // Muestra las estadísticas actualizadas
        System.out.println("\n📊 Tus estadísticas:");
        jugadorActual.mostrarEstadisticas();
    }
    
    // ========== MÉTODO: Mostrar Estado del Juego ==========
    // Parámetros: char[] letrasAdivinadas, int intentosFallidos, String letrasIntentadas
    // Propósito: Mostrar el progreso actual del juego
    private static void mostrarEstadoJuego(char[] letrasAdivinadas, int intentosFallidos, String letrasIntentadas) {
        System.out.println("\n┌────────────────────────────────────────┐");
        System.out.print("│ Palabra: ");
        for (char letra : letrasAdivinadas) {
            System.out.print(letra + " ");
        }
        System.out.println("          │");
        System.out.printf("│ Intentos fallidos: %d/%d              │\n", intentosFallidos, INTENTOS_MAXIMOS);
        System.out.print("│ Letras intentadas: " + letrasIntentadas);
        System.out.println("                 │");
        System.out.println("└────────────────────────────────────────┘");
    }
    
    // ========== MÉTODO: Obtener Palabra Aleatoria ==========
    // Parámetro: int categoriaIndex
    // Retorna: String (palabra aleatoria de la categoría)
    // Propósito: Seleccionar una palabra aleatoria de la categoría elegida
    private static String obtenerPalabraAleatoria(int categoriaIndex) {
        // Obtiene el arreglo de palabras de la categoría
        String[] palabras = palabrasPorCategoria[categoriaIndex];
        // Selecciona un índice aleatorio
        int indiceAleatorio = aleatorio.nextInt(palabras.length);
        // Retorna la palabra
        return palabras[indiceAleatorio];
    }
    
    // ========== MÉTODO: Obtener Jugador ==========
    // Parámetro: String nombre
    // Retorna: Jugador (el jugador existente o uno nuevo)
    // Propósito: Obtener un jugador por nombre o crear uno nuevo
    private static Jugador obtenerJugador(String nombre) {
        // Estructura de control: Iterativa (Bucle for)
        // Busca si el jugador ya existe
        for (int i = 0; i < totalJugadores; i++) {
            if (jugadores[i].nombre.equalsIgnoreCase(nombre)) {
                return jugadores[i];
            }
        }
        
        // Si no existe, crea uno nuevo
        if (totalJugadores < MAX_JUGADORES) {
            Jugador nuevoJugador = new Jugador(nombre);
            jugadores[totalJugadores] = nuevoJugador;
            totalJugadores++;
            System.out.println("\n👤 Bienvenido nuevo jugador: " + nombre);
            return nuevoJugador;
        }
        
        return null;
    }
    
    // ========== MÉTODO: Mostrar Clasificación ==========
    // Propósito: Mostrar a todos los jugadores ordenados por victorias
    private static void mostrarClasificacion() {
        if (totalJugadores == 0) {
            System.out.println("\n⚠️  No hay jugadores registrados aún.");
            return;
        }
        
        System.out.println("\n┌──────────────────────────────────────────────────┐");
        System.out.println("│         🏆 CLASIFICACIÓN GLOBAL 🏆              │");
        System.out.println("├────┬─────────────────┬────────┬────────┬────────┤");
        System.out.println("│ #  │ Jugador         │ Juegos │ Ganados│ % Gano │");
        System.out.println("├────┼─────────────────┼────────┼────────┼────────┤");
        
        // Estructura de control: Iterativa (Bucle for)
        // Recorre todos los jugadores
        for (int i = 0; i < totalJugadores; i++) {
            Jugador j = jugadores[i];
            System.out.printf("│ %-2d │ %-15s │ %-6d │ %-6d │ %-5.1f%% │\n",
                    (i + 1),
                    j.nombre,
                    j.juegosJugados,
                    j.juegosGanados,
                    j.calcularPorcentajeVictorias());
        }
        
        System.out.println("└────┴─────────────────┴────────┴────────┴────────┘");
    }
    
    // ========== MÉTODO: Ver Estadísticas Jugador ==========
    // Parámetro: Scanner entrada
    // Propósito: Mostrar estadísticas detalladas de un jugador específico
    private static void verEstadisticasJugador(Scanner entrada) {
        System.out.print("\n¿Cuál es tu nombre? ");
        String nombre = entrada.nextLine();
        
        // Busca el jugador
        Jugador jugador = obtenerJugador(nombre);
        
        if (jugador != null) {
            jugador.mostrarEstadisticas();
        }
    }
}