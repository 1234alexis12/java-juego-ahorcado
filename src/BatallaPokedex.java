import java.util.Random;
import java.util.Scanner;

public class BatallaPokedex {
    // ========== CONSTANTES DEL SISTEMA ==========
    // Número máximo de pokémon que cada jugador puede tener
    private static final int EQUIPO_MAXIMO = 6;
    // Puntos de salud mínimos para un pokémon
    private static final int VIDA_MINIMA = 20;
    // Puntos de salud máximos para un pokémon
    private static final int VIDA_MAXIMA = 100;
    
    // ========== CLASE INTERNA: Pokémon ==========
    // Esta clase representa a un pokémon con sus atributos
    static class Pokemon {
        // Nombre del pokémon
        String nombre;
        // Tipo de pokémon (fuego, agua, planta, eléctrico, normal)
        String tipo;
        // Puntos de salud actuales
        int vidaActual;
        // Puntos de salud máximos
        int vidaMaxima;
        // Potencia de ataque
        int ataque;
        // Capacidad de defensa
        int defensa;
        // Velocidad (determina quién ataca primero)
        int velocidad;
        // Nivel del pokémon
        int nivel;
        // Experiencia acumulada
        int experiencia;
        
        // Constructor: Inicializa un nuevo pokémon
        Pokemon(String nombre, String tipo, int ataque, int defensa, int velocidad) {
            this.nombre = nombre;
            this.tipo = tipo;
            this.vidaMaxima = 80;
            this.vidaActual = vidaMaxima;
            this.ataque = ataque;
            this.defensa = defensa;
            this.velocidad = velocidad;
            this.nivel = 1;
            this.experiencia = 0;
        }
        
        // Método: Obtener información del pokémon
        void mostrarInfo() {
            System.out.printf("  %s [Nivel %d] - Tipo: %s\n", nombre, nivel, tipo);
            System.out.printf("  ❤️  Vida: %d/%d | Ataque: %d | Defensa: %d | Velocidad: %d\n",
                    vidaActual, vidaMaxima, ataque, defensa, velocidad);
        }
        
        // Método: Verificar si el pokémon está vivo
        boolean estaVivo() {
            return vidaActual > 0;
        }
        
        // Método: Subir de nivel
        void subirNivel() {
            nivel++;
            ataque += 2;
            defensa += 1;
            vidaMaxima += 10;
            vidaActual = vidaMaxima;
            System.out.println("⬆️  " + nombre + " subió a nivel " + nivel + "!");
        }
        
        // Método: Ganar experiencia
        void ganarExperiencia(int exp) {
            experiencia += exp;
            if (experiencia >= 100) {
                subirNivel();
                experiencia = 0;
            }
        }
    }
    
    // ========== VARIABLES GLOBALES ==========
    // Arreglo para los pokémon del jugador 1
    private static Pokemon[] equipoJugador1 = new Pokemon[EQUIPO_MAXIMO];
    // Contador de pokémon del jugador 1
    private static int totalPokemonJ1 = 0;
    
    // Arreglo para los pokémon del jugador 2
    private static Pokemon[] equipoJugador2 = new Pokemon[EQUIPO_MAXIMO];
    // Contador de pokémon del jugador 2
    private static int totalPokemonJ2 = 0;
    
    // Generador de números aleatorios para ataques y eventos
    private static Random aleatorio = new Random();
    
    public static void main(String[] args) {
        // ========== INICIALIZACIÓN ==========
        Scanner entrada = new Scanner(System.in);
        
        // Mensaje de bienvenida
        mostrarBienvenida();
        
        // ========== PREPARACIÓN DE EQUIPOS ==========
        System.out.println("\n┌────────────────────────────────────────┐");
        System.out.println("│   PREPARACIÓN DE EQUIPOS POKÉMON      │");
        System.out.println("└────────────────────────────────────────┘");
        
        // El jugador 1 elige su equipo
        System.out.println("\n👤 JUGADOR 1 - Elige tu equipo:");
        prepararEquipo(entrada, 1);
        
        // El jugador 2 elige su equipo
        System.out.println("\n👥 JUGADOR 2 - Elige tu equipo:");
        prepararEquipo(entrada, 2);
        
        // ========== INICIO DE LA BATALLA ==========
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║    ¡COMIENZA LA BATALLA POKÉMON!      ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        // Índices de los pokémon activos en batalla
        int pokemonActivoJ1 = 0;
        int pokemonActivoJ2 = 0;
        
        // Bandera para controlar si la batalla continúa
        boolean batallaActiva = true;
        
        // ========== BUCLE PRINCIPAL DE BATALLA ==========
        // Estructura de control: Iterativa (Bucle while)
        while (batallaActiva) {
            // Muestra el estado actual de ambos pokémon
            System.out.println("\n┌────────── ESTADO DE BATALLA ──────────┐");
            System.out.println("│ JUGADOR 1:                            │");
            equipoJugador1[pokemonActivoJ1].mostrarInfo();
            System.out.println("├───────────────────────────────────────┤");
            System.out.println("│ JUGADOR 2:                            │");
            equipoJugador2[pokemonActivoJ2].mostrarInfo();
            System.out.println("└───────────────────────────────────────┘");
            
            // Determina quién ataca primero basado en velocidad
            Pokemon atacante, defensor;
            int turnoJugador;
            
            if (equipoJugador1[pokemonActivoJ1].velocidad >= equipoJugador2[pokemonActivoJ2].velocidad) {
                atacante = equipoJugador1[pokemonActivoJ1];
                defensor = equipoJugador2[pokemonActivoJ2];
                turnoJugador = 1;
            } else {
                atacante = equipoJugador2[pokemonActivoJ2];
                defensor = equipoJugador1[pokemonActivoJ1];
                turnoJugador = 2;
            }
            
            // Solicita la acción del jugador atacante
            System.out.print("\n[Turno Jugador " + turnoJugador + "] Elige acción:\n");
            System.out.println("  1. Atacar");
            System.out.println("  2. Cambiar pokémon");
            System.out.println("  3. Ver información");
            System.out.print("Opción: ");
            
            int accion = entrada.nextInt();
            entrada.nextLine(); // Limpia el buffer
            
            // Estructura de control: Condicional (switch-case)
            switch (accion) {
                case 1:
                    // Realiza un ataque
                    realizarAtaque(atacante, defensor);
                    break;
                case 2:
                    // Permite cambiar de pokémon
                    if (turnoJugador == 1) {
                        pokemonActivoJ1 = cambiarPokemon(entrada, 1);
                    } else {
                        pokemonActivoJ2 = cambiarPokemon(entrada, 2);
                    }
                    break;
                case 3:
                    // Muestra información detallada
                    System.out.println("\n📋 --- INFORMACIÓN DEL EQUIPO ---");
                    if (turnoJugador == 1) {
                        mostrarEquipo(1);
                    } else {
                        mostrarEquipo(2);
                    }
                    continue; // No cuenta como turno
                default:
                    System.out.println("❌ Opción no válida.");
                    continue;
            }
            
            // Verifica si el pokémon defensor fue derrotado
            if (!defensor.estaVivo()) {
                System.out.println("💥 " + defensor.nombre + " ha sido derrotado!");
                
                // El atacante gana experiencia
                atacante.ganarExperiencia(30);
                
                // Busca el siguiente pokémon disponible del equipo derrotado
                if (turnoJugador == 1) {
                    pokemonActivoJ2 = encontrarPokemonVivo(equipoJugador2, totalPokemonJ2);
                    if (pokemonActivoJ2 == -1) {
                        // Jugador 2 no tiene más pokémon
                        batallaActiva = false;
                        System.out.println("\n╔════════════════════════════════════════╗");
                        System.out.println("║     🏆 ¡JUGADOR 1 HA GANADO! 🏆      ║");
                        System.out.println("╚════════════════════════════════════════╝");
                    }
                } else {
                    pokemonActivoJ1 = encontrarPokemonVivo(equipoJugador1, totalPokemonJ1);
                    if (pokemonActivoJ1 == -1) {
                        // Jugador 1 no tiene más pokémon
                        batallaActiva = false;
                        System.out.println("\n╔════════════════════════════════════════╗");
                        System.out.println("║     🏆 ¡JUGADOR 2 HA GANADO! 🏆      ║");
                        System.out.println("╚════════════════════════════════════════╝");
                    }
                }
            }
        }
        
        // ========== FIN DEL PROGRAMA ==========
        System.out.println("\n👋 ¡Gracias por jugar! Hasta pronto.");
        entrada.close();
    }
    
    // ========== MÉTODO: Mostrar Bienvenida ==========
    private static void mostrarBienvenida() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     ⚔️  BATALLA POKÉMON 2025 ⚔️      ║");
        System.out.println("║   Sistema de Combate por Turnos        ║");
        System.out.println("╚════════════════════════════════════════╝");
    }
    
    // ========== MÉTODO: Preparar Equipo ==========
    // Parámetro: Scanner entrada, int numeroJugador
    // Propósito: Permitir que cada jugador elija sus pokémon
    private static void prepararEquipo(Scanner entrada, int numeroJugador) {
        // Crea una lista de pokémon disponibles
        Pokemon[] pokemonDisponibles = crearPokemonDisponibles();
        
        // Variable para contar pokémon seleccionados
        int contador = 0;
        
        // Estructura de control: Iterativa (Bucle while)
        while (contador < EQUIPO_MAXIMO) {
            System.out.println("\n📝 Pokémon disponibles:");
            
            // Estructura de control: Iterativa (Bucle for)
            for (int i = 0; i < pokemonDisponibles.length; i++) {
                System.out.printf("  %d. %s [%s] - Ataque: %d, Defensa: %d, Velocidad: %d\n",
                        (i + 1),
                        pokemonDisponibles[i].nombre,
                        pokemonDisponibles[i].tipo,
                        pokemonDisponibles[i].ataque,
                        pokemonDisponibles[i].defensa,
                        pokemonDisponibles[i].velocidad);
            }
            System.out.println("  " + (pokemonDisponibles.length + 1) + ". Finalizar selección");
            
            System.out.print("Elige un pokémon (has seleccionado " + contador + "/" + EQUIPO_MAXIMO + "): ");
            int opcion = entrada.nextInt();
            entrada.nextLine();
            
            // Verifica si el usuario quiere finalizar
            if (opcion == pokemonDisponibles.length + 1) {
                if (contador == 0) {
                    System.out.println("❌ Debes seleccionar al menos 1 pokémon.");
                    continue;
                }
                break;
            }
            
            // Valida la opción
            if (opcion < 1 || opcion > pokemonDisponibles.length) {
                System.out.println("❌ Opción no válida.");
                continue;
            }
            
            // Agrega el pokémon al equipo del jugador
            Pokemon pokemonSeleccionado = pokemonDisponibles[opcion - 1];
            
            if (numeroJugador == 1) {
                equipoJugador1[contador] = new Pokemon(
                        pokemonSeleccionado.nombre,
                        pokemonSeleccionado.tipo,
                        pokemonSeleccionado.ataque,
                        pokemonSeleccionado.defensa,
                        pokemonSeleccionado.velocidad
                );
                totalPokemonJ1++;
            } else {
                equipoJugador2[contador] = new Pokemon(
                        pokemonSeleccionado.nombre,
                        pokemonSeleccionado.tipo,
                        pokemonSeleccionado.ataque,
                        pokemonSeleccionado.defensa,
                        pokemonSeleccionado.velocidad
                );
                totalPokemonJ2++;
            }
            
            System.out.println("✅ " + pokemonSeleccionado.nombre + " agregado a tu equipo!");
            contador++;
        }
    }
    
    // ========== MÉTODO: Crear Pokémon Disponibles ==========
    // Retorna: Array de pokémon disponibles para seleccionar
    // Propósito: Inicializar los pokémon que los jugadores pueden elegir
    private static Pokemon[] crearPokemonDisponibles() {
        Pokemon[] disponibles = new Pokemon[8];
        
        disponibles[0] = new Pokemon("Charizard", "Fuego", 12, 8, 10);
        disponibles[1] = new Pokemon("Blastoise", "Agua", 10, 12, 8);
        disponibles[2] = new Pokemon("Venusaur", "Planta", 11, 10, 9);
        disponibles[3] = new Pokemon("Pikachu", "Eléctrico", 13, 6, 14);
        disponibles[4] = new Pokemon("Dragonite", "Dragón", 14, 11, 11);
        disponibles[5] = new Pokemon("Gengar", "Fantasma", 12, 7, 12);
        disponibles[6] = new Pokemon("Lapras", "Agua/Hielo", 11, 14, 7);
        disponibles[7] = new Pokemon("Articuno", "Hielo/Volador", 12, 13, 9);
        
        return disponibles;
    }
    
    // ========== MÉTODO: Realizar Ataque ==========
    // Parámetros: Pokemon atacante, Pokemon defensor
    // Propósito: Calcular el daño y aplicarlo al defensor
    private static void realizarAtaque(Pokemon atacante, Pokemon defensor) {
        // Calcula el daño base del ataque
        int danoBase = atacante.ataque;
        
        // Aplica un multiplicador aleatorio entre 0.8 y 1.2 para más variedad
        double multiplicador = 0.8 + (aleatorio.nextDouble() * 0.4);
        
        // Calcula la defensa del oponente que reduce el daño
        int defensa = defensor.defensa;
        
        // Fórmula simplificada de daño: ataque - (defensa/2) + variación
        int danioFinal = Math.max(1, (int)((danoBase - defensa / 2) * multiplicador));
        
        // Existe una probabilidad de golpe crítico (20%)
        if (aleatorio.nextInt(100) < 20) {
            danioFinal *= 2;
            System.out.println("⚡ ¡GOLPE CRÍTICO!");
        }
        
        // Aplica el daño al defensor
        defensor.vidaActual -= danioFinal;
        
        // Asegura que la vida no sea negativa
        if (defensor.vidaActual < 0) {
            defensor.vidaActual = 0;
        }
        
        // Muestra el resultado del ataque
        System.out.printf("\n💥 %s ataca a %s!\n", atacante.nombre, defensor.nombre);
        System.out.printf("   Daño infligido: %d puntos\n", danioFinal);
        System.out.printf("   %s ahora tiene %d de vida.\n", defensor.nombre, defensor.vidaActual);
    }
    
    // ========== MÉTODO: Cambiar Pokémon ==========
    // Parámetros: Scanner entrada, int numeroJugador
    // Retorna: int (índice del nuevo pokémon activo)
    // Propósito: Permitir cambiar de pokémon durante la batalla
    private static int cambiarPokemon(Scanner entrada, int numeroJugador) {
        Pokemon[] equipo = (numeroJugador == 1) ? equipoJugador1 : equipoJugador2;
        int totalPokemon = (numeroJugador == 1) ? totalPokemonJ1 : totalPokemonJ2;
        
        System.out.println("\n📋 --- ELIGE UN POKÉMON ---");
        
        // Estructura de control: Iterativa (Bucle for)
        for (int i = 0; i < totalPokemon; i++) {
            String estado = equipo[i].estaVivo() ? "✅ Vivo" : "❌ Derrotado";
            System.out.printf("  %d. %s - Vida: %d/%d [%s]\n",
                    (i + 1),
                    equipo[i].nombre,
                    equipo[i].vidaActual,
                    equipo[i].vidaMaxima,
                    estado);
        }
        
        System.out.print("Selecciona un pokémon: ");
        int opcion = entrada.nextInt();
        entrada.nextLine();
        
        // Valida la opción
        if (opcion < 1 || opcion > totalPokemon) {
            System.out.println("❌ Opción no válida.");
            return 0;
        }
        
        // Verifica que el pokémon esté vivo
        if (!equipo[opcion - 1].estaVivo()) {
            System.out.println("❌ Ese pokémon está derrotado.");
            return 0;
        }
        
        System.out.println("✅ ¡" + equipo[opcion - 1].nombre + " entra en batalla!");
        return opcion - 1;
    }
    
    // ========== MÉTODO: Mostrar Equipo ==========
    // Parámetro: int numeroJugador
    // Propósito: Mostrar todos los pokémon del equipo
    private static void mostrarEquipo(int numeroJugador) {
        Pokemon[] equipo = (numeroJugador == 1) ? equipoJugador1 : equipoJugador2;
        int totalPokemon = (numeroJugador == 1) ? totalPokemonJ1 : totalPokemonJ2;
        
        for (int i = 0; i < totalPokemon; i++) {
            System.out.println("\n" + (i + 1) + ". ");
            equipo[i].mostrarInfo();
        }
    }
    
    // ========== MÉTODO: Encontrar Pokémon Vivo ==========
    // Parámetros: Array de pokémon, total de pokémon
    // Retorna: int (índice del primer pokémon vivo, o -1 si no hay)
    // Propósito: Buscar el siguiente pokémon disponible en el equipo
    private static int encontrarPokemonVivo(Pokemon[] equipo, int total) {
        // Estructura de control: Iterativa (Bucle for)
        for (int i = 0; i < total; i++) {
            if (equipo[i].estaVivo()) {
                return i;
            }
        }
        return -1; // No hay pokémon vivos
    }
}