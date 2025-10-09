import java.util.Scanner;

public class SistemaTienda {
    // ========== CONSTANTES DEL SISTEMA ==========
    // Número máximo de productos que puede almacenar la tienda
    private static final int CAPACIDAD_INVENTARIO = 100;
    // Opción para salir del menú
    private static final int OPCION_SALIR = 6;
    
    // ========== VARIABLES GLOBALES (Arreglos) ==========
    // Arreglo para almacenar los códigos únicos de cada producto
    private static String[] codigosProductos = new String[CAPACIDAD_INVENTARIO];
    // Arreglo para almacenar los nombres de los productos
    private static String[] nombresProductos = new String[CAPACIDAD_INVENTARIO];
    // Arreglo para almacenar el precio de cada producto
    private static double[] preciosProductos = new double[CAPACIDAD_INVENTARIO];
    // Arreglo para almacenar la cantidad disponible de cada producto
    private static int[] cantidadesProductos = new int[CAPACIDAD_INVENTARIO];
    // Contador que indica cuántos productos están registrados actualmente
    private static int totalProductos = 0;
    
    // Arreglo para registrar las ventas realizadas
    private static double[] ventasRegistradas = new double[500];
    // Contador de cuántas ventas se han realizado
    private static int totalVentas = 0;
    
    public static void main(String[] args) {
        // ========== INICIALIZACIÓN DEL SISTEMA ==========
        Scanner entrada = new Scanner(System.in);
        // Variable para controlar si se debe continuar en el menú principal
        boolean sistemaActivo = true;
        
        // Mensaje de bienvenida del sistema
        mostrarBienvenida();
        
        // ========== BUCLE PRINCIPAL DEL SISTEMA ==========
        // Estructura de control: Iterativa (Bucle while)
        // Se ejecuta mientras el usuario no elija salir
        while (sistemaActivo) {
            // Muestra el menú principal y obtiene la opción del usuario
            int opcionUsuario = mostrarMenuPrincipal(entrada);
            
            // Estructura de control: Condicional (switch-case)
            // Ejecuta diferentes acciones según la opción seleccionada
            switch (opcionUsuario) {
                case 1:
                    // Opción: Agregar un nuevo producto al inventario
                    agregarProducto(entrada);
                    break;
                case 2:
                    // Opción: Mostrar todos los productos registrados
                    mostrarInventario();
                    break;
                case 3:
                    // Opción: Realizar una venta de productos
                    realizarVenta(entrada);
                    break;
                case 4:
                    // Opción: Buscar un producto por código
                    buscarProducto(entrada);
                    break;
                case 5:
                    // Opción: Ver el reporte de ventas del día
                    mostrarReporteVentas();
                    break;
                case 6:
                    // Opción: Salir del sistema
                    sistemaActivo = false;
                    System.out.println("\n👋 ¡Hasta luego! Gracias por usar el sistema.");
                    break;
                default:
                    // Si el usuario ingresa una opción no válida
                    System.out.println("❌ Opción no válida. Intenta de nuevo.");
            }
        }
        
        // Cierra el Scanner para liberar recursos
        entrada.close();
    }
    
    // ========== MÉTODO: Mostrar Bienvenida ==========
    // Método: Se ejecuta una sola vez al iniciar el programa
    // Propósito: Mostrar un mensaje de bienvenida al usuario
    private static void mostrarBienvenida() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     SISTEMA DE GESTIÓN DE TIENDA        ║");
        System.out.println("║          Versión 1.0 - 2025            ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println();
    }
    
    // ========== MÉTODO: Mostrar Menú Principal ==========
    // Parámetro: Scanner entrada (para recibir la opción del usuario)
    // Retorna: int (la opción seleccionada por el usuario)
    // Propósito: Mostrar el menú y obtener la opción del usuario
    private static int mostrarMenuPrincipal(Scanner entrada) {
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println("│          MENÚ PRINCIPAL                 │");
        System.out.println("├──────────────────────────────────────────┤");
        System.out.println("│ 1. Agregar nuevo producto               │");
        System.out.println("│ 2. Mostrar inventario completo          │");
        System.out.println("│ 3. Realizar una venta                   │");
        System.out.println("│ 4. Buscar un producto                   │");
        System.out.println("│ 5. Ver reporte de ventas                │");
        System.out.println("│ 6. Salir del sistema                    │");
        System.out.println("└──────────────────────────────────────────┘");
        System.out.print("Selecciona una opción: ");
        
        // Obtiene la opción ingresada por el usuario
        int opcion = entrada.nextInt();
        entrada.nextLine(); // Limpia el buffer de entrada
        return opcion;
    }
    
    // ========== MÉTODO: Agregar Producto ==========
    // Parámetro: Scanner entrada (para recibir datos del producto)
    // Propósito: Agregar un nuevo producto al inventario
    private static void agregarProducto(Scanner entrada) {
        // Verifica que no se haya alcanzado la capacidad máxima del inventario
        if (totalProductos >= CAPACIDAD_INVENTARIO) {
            System.out.println("❌ El inventario está lleno. No se pueden agregar más productos.");
            return;
        }
        
        System.out.println("\n📦 --- AGREGAR NUEVO PRODUCTO ---");
        
        // Solicita el código del producto
        System.out.print("Ingresa el código del producto: ");
        String codigo = entrada.nextLine();
        
        // Valida que el código no esté duplicado
        if (buscarIndiceProducto(codigo) != -1) {
            System.out.println("❌ Error: Ya existe un producto con ese código.");
            return;
        }
        
        // Solicita el nombre del producto
        System.out.print("Ingresa el nombre del producto: ");
        String nombre = entrada.nextLine();
        
        // Solicita el precio del producto
        System.out.print("Ingresa el precio unitario: $");
        double precio = entrada.nextDouble();
        
        // Valida que el precio sea válido (no negativo)
        if (precio < 0) {
            System.out.println("❌ Error: El precio no puede ser negativo.");
            return;
        }
        
        // Solicita la cantidad inicial disponible
        System.out.print("Ingresa la cantidad disponible: ");
        int cantidad = entrada.nextInt();
        entrada.nextLine(); // Limpia el buffer
        
        // Valida que la cantidad sea válida (no negativa)
        if (cantidad < 0) {
            System.out.println("❌ Error: La cantidad no puede ser negativa.");
            return;
        }
        
        // Almacena los datos del producto en los arreglos
        codigosProductos[totalProductos] = codigo;
        nombresProductos[totalProductos] = nombre;
        preciosProductos[totalProductos] = precio;
        cantidadesProductos[totalProductos] = cantidad;
        
        // Incrementa el contador de productos
        totalProductos++;
        
        // Mensaje de confirmación
        System.out.println("✅ Producto agregado exitosamente!");
    }
    
    // ========== MÉTODO: Mostrar Inventario ==========
    // Propósito: Mostrar todos los productos registrados en el sistema
    private static void mostrarInventario() {
        // Verifica que haya al menos un producto registrado
        if (totalProductos == 0) {
            System.out.println("\n⚠️  El inventario está vacío.");
            return;
        }
        
        System.out.println("\n📦 --- INVENTARIO ACTUAL ---");
        System.out.println("┌────┬───────────┬─────────────────────┬──────────┬─────────────┐");
        System.out.println("│ #  │ Código    │ Nombre              │ Precio   │ Cantidad    │");
        System.out.println("├────┼───────────┼─────────────────────┼──────────┼─────────────┤");
        
        // Estructura de control: Iterativa (Bucle for)
        // Recorre todos los productos para mostrarlos
        for (int i = 0; i < totalProductos; i++) {
            // Usa printf para alinear los datos en columnas
            System.out.printf("│ %-2d │ %-9s │ %-19s │ $%-7.2f │ %-11d │\n",
                    (i + 1),
                    codigosProductos[i],
                    nombresProductos[i],
                    preciosProductos[i],
                    cantidadesProductos[i]);
        }
        
        System.out.println("└────┴───────────┴─────────────────────┴──────────┴─────────────┘");
        
        // Calcula y muestra el valor total del inventario
        double valorTotal = calcularValorInventario();
        System.out.printf("💰 Valor total del inventario: $%.2f\n", valorTotal);
    }
    
    // ========== MÉTODO: Realizar Venta ==========
    // Parámetro: Scanner entrada (para recibir datos de la venta)
    // Propósito: Procesar una venta de productos
    private static void realizarVenta(Scanner entrada) {
        System.out.println("\n💳 --- REALIZAR VENTA ---");
        
        // Solicita el código del producto a vender
        System.out.print("Ingresa el código del producto: ");
        String codigo = entrada.nextLine();
        
        // Busca el índice del producto en los arreglos
        int indice = buscarIndiceProducto(codigo);
        
        // Verifica que el producto exista
        if (indice == -1) {
            System.out.println("❌ Error: Producto no encontrado.");
            return;
        }
        
        // Muestra los detalles del producto
        System.out.println("\n📦 Producto encontrado:");
        System.out.println("   Nombre: " + nombresProductos[indice]);
        System.out.println("   Precio: $" + preciosProductos[indice]);
        System.out.println("   Cantidad disponible: " + cantidadesProductos[indice]);
        
        // Solicita la cantidad a vender
        System.out.print("\nIngresa la cantidad a vender: ");
        int cantidadVenta = entrada.nextInt();
        entrada.nextLine(); // Limpia el buffer
        
        // Valida que la cantidad sea válida
        if (cantidadVenta <= 0) {
            System.out.println("❌ Error: La cantidad debe ser mayor a cero.");
            return;
        }
        
        // Valida que haya stock suficiente
        if (cantidadVenta > cantidadesProductos[indice]) {
            System.out.println("❌ Error: No hay suficiente stock.");
            System.out.println("   Stock disponible: " + cantidadesProductos[indice]);
            return;
        }
        
        // Calcula el monto total de la venta
        double montoVenta = cantidadVenta * preciosProductos[indice];
        
        // Reduce la cantidad disponible del producto
        cantidadesProductos[indice] -= cantidadVenta;
        
        // Registra la venta en el arreglo de ventas
        ventasRegistradas[totalVentas] = montoVenta;
        totalVentas++;
        
        // Muestra el resumen de la venta
        System.out.println("\n✅ --- VENTA REALIZADA ---");
        System.out.printf("   Cantidad: %d unidades\n", cantidadVenta);
        System.out.printf("   Precio unitario: $%.2f\n", preciosProductos[indice]);
        System.out.printf("   TOTAL: $%.2f\n", montoVenta);
        System.out.println("   Stock restante: " + cantidadesProductos[indice]);
    }
    
    // ========== MÉTODO: Buscar Producto ==========
    // Parámetro: Scanner entrada (para recibir el código a buscar)
    // Propósito: Buscar un producto específico por su código
    private static void buscarProducto(Scanner entrada) {
        System.out.println("\n🔍 --- BUSCAR PRODUCTO ---");
        System.out.print("Ingresa el código del producto: ");
        String codigo = entrada.nextLine();
        
        // Busca el índice del producto
        int indice = buscarIndiceProducto(codigo);
        
        // Verifica si el producto existe
        if (indice == -1) {
            System.out.println("❌ Producto no encontrado.");
            return;
        }
        
        // Muestra los detalles del producto encontrado
        System.out.println("\n✅ --- PRODUCTO ENCONTRADO ---");
        System.out.println("   Código: " + codigosProductos[indice]);
        System.out.println("   Nombre: " + nombresProductos[indice]);
        System.out.printf("   Precio: $%.2f\n", preciosProductos[indice]);
        System.out.println("   Cantidad disponible: " + cantidadesProductos[indice]);
    }
    
    // ========== MÉTODO: Mostrar Reporte de Ventas ==========
    // Propósito: Mostrar estadísticas de las ventas realizadas
    private static void mostrarReporteVentas() {
        // Verifica que se hayan realizado ventas
        if (totalVentas == 0) {
            System.out.println("\n⚠️  No hay ventas registradas aún.");
            return;
        }
        
        System.out.println("\n📊 --- REPORTE DE VENTAS ---");
        
        // Variables para almacenar cálculos
        double ventaTotal = 0;
        double ventaMaxima = ventasRegistradas[0];
        double ventaMinima = ventasRegistradas[0];
        
        // Estructura de control: Iterativa (Bucle for)
        // Recorre todas las ventas para calcular estadísticas
        for (int i = 0; i < totalVentas; i++) {
            // Suma todas las ventas
            ventaTotal += ventasRegistradas[i];
            
            // Encuentra la venta más alta
            if (ventasRegistradas[i] > ventaMaxima) {
                ventaMaxima = ventasRegistradas[i];
            }
            
            // Encuentra la venta más baja
            if (ventasRegistradas[i] < ventaMinima) {
                ventaMinima = ventasRegistradas[i];
            }
        }
        
        // Calcula el promedio de ventas
        double ventaPromedio = ventaTotal / totalVentas;
        
        // Muestra las estadísticas
        System.out.println("┌──────────────────────────────────────┐");
        System.out.printf("│ Total de ventas: %-20d │\n", totalVentas);
        System.out.printf("│ Venta total: $%-24.2f │\n", ventaTotal);
        System.out.printf("│ Venta promedio: $%-22.2f │\n", ventaPromedio);
        System.out.printf("│ Venta máxima: $%-23.2f │\n", ventaMaxima);
        System.out.printf("│ Venta mínima: $%-23.2f │\n", ventaMinima);
        System.out.println("└──────────────────────────────────────┘");
    }
    
    // ========== MÉTODO AUXILIAR: Buscar Índice de Producto ==========
    // Parámetro: String codigo (el código del producto a buscar)
    // Retorna: int (el índice del producto en los arreglos, o -1 si no existe)
    // Propósito: Buscar un producto en los arreglos por su código
    private static int buscarIndiceProducto(String codigo) {
        // Estructura de control: Iterativa (Bucle for)
        // Recorre todos los productos buscando el código
        for (int i = 0; i < totalProductos; i++) {
            // equals() compara si dos String son idénticos
            if (codigosProductos[i].equals(codigo)) {
                return i; // Retorna el índice si encuentra el producto
            }
        }
        return -1; // Retorna -1 si no encuentra el producto
    }
    
    // ========== MÉTODO AUXILIAR: Calcular Valor del Inventario ==========
    // Retorna: double (el valor total del inventario)
    // Propósito: Calcular cuánto dinero hay invertido en inventario
    private static double calcularValorInventario() {
        // Variable acumuladora para sumar el valor de todos los productos
        double valorTotal = 0;
        
        // Estructura de control: Iterativa (Bucle for)
        // Recorre todos los productos
        for (int i = 0; i < totalProductos; i++) {
            // Suma el precio por cantidad de cada producto
            valorTotal += preciosProductos[i] * cantidadesProductos[i];
        }
        
        return valorTotal;
    }
}