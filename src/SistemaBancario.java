import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SistemaBancario {
    
    // Clase interna para representar una cuenta bancaria
    static class Cuenta {
        String numeroCuenta;
        String titular;
        double saldo;
        String tipoCuenta;
        List<String> historialTransacciones;
        
        Cuenta(String numero, String titular, double saldoInicial, String tipo) {
            this.numeroCuenta = numero;
            this.titular = titular;
            this.saldo = saldoInicial;
            this.tipoCuenta = tipo;
            this.historialTransacciones = new ArrayList<>();
            registrarTransaccion("Cuenta creada con saldo inicial: $" + saldoInicial);
        }
        
        void registrarTransaccion(String descripcion) {
            String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            historialTransacciones.add("[" + fecha + "] " + descripcion);
        }
        
        boolean depositar(double monto) {
            if (monto <= 0) return false;
            saldo += monto;
            registrarTransaccion("Depósito: +$" + monto + " | Saldo: $" + saldo);
            return true;
        }
        
        boolean retirar(double monto) {
            if (monto <= 0 || monto > saldo) return false;
            saldo -= monto;
            registrarTransaccion("Retiro: -$" + monto + " | Saldo: $" + saldo);
            return true;
        }
        
        double calcularInteres() {
            double tasaInteres = tipoCuenta.equals("Ahorro") ? 0.05 : 0.02;
            return saldo * tasaInteres;
        }
        
        @Override
        public String toString() {
            return String.format("Cuenta: %s | Titular: %s | Tipo: %s | Saldo: $%.2f", 
                               numeroCuenta, titular, tipoCuenta, saldo);
        }
    }
    
    // Clase interna para representar un préstamo
    static class Prestamo {
        String id;
        String numeroCuenta;
        double montoTotal;
        double montoRestante;
        int plazoMeses;
        int mesesPagados;
        double tasaInteres;
        
        Prestamo(String id, String cuenta, double monto, int plazo, double tasa) {
            this.id = id;
            this.numeroCuenta = cuenta;
            this.montoTotal = monto;
            this.montoRestante = monto * (1 + tasa);
            this.plazoMeses = plazo;
            this.mesesPagados = 0;
            this.tasaInteres = tasa;
        }
        
        double calcularCuotaMensual() {
            return montoRestante / (plazoMeses - mesesPagados);
        }
        
        boolean pagarCuota(double monto) {
            if (monto < calcularCuotaMensual() || mesesPagados >= plazoMeses) return false;
            montoRestante -= monto;
            mesesPagados++;
            return true;
        }
        
        boolean estaLiquidado() {
            return montoRestante <= 0 || mesesPagados >= plazoMeses;
        }
        
        @Override
        public String toString() {
            return String.format("Préstamo %s | Monto Original: $%.2f | Restante: $%.2f | Cuotas: %d/%d", 
                               id, montoTotal, montoRestante, mesesPagados, plazoMeses);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Cuenta> cuentas = new HashMap<>();
        Map<String, Prestamo> prestamos = new HashMap<>();
        int contadorCuentas = 1000;
        int contadorPrestamos = 1;
        
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║    SISTEMA BANCARIO INTEGRAL - JAVA BANKING       ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        boolean salir = false;
        
        while (!salir) {
            System.out.println("\n┌─────────────── MENÚ PRINCIPAL ───────────────┐");
            System.out.println("│ 1. Crear cuenta                              │");
            System.out.println("│ 2. Depositar dinero                          │");
            System.out.println("│ 3. Retirar dinero                            │");
            System.out.println("│ 4. Transferir entre cuentas                  │");
            System.out.println("│ 5. Consultar saldo                           │");
            System.out.println("│ 6. Solicitar préstamo                        │");
            System.out.println("│ 7. Pagar cuota de préstamo                   │");
            System.out.println("│ 8. Calcular intereses de ahorro              │");
            System.out.println("│ 9. Ver historial de transacciones            │");
            System.out.println("│ 10. Listar todas las cuentas                 │");
            System.out.println("│ 11. Estadísticas del sistema                 │");
            System.out.println("│ 0. Salir                                     │");
            System.out.println("└──────────────────────────────────────────────┘");
            System.out.print("Seleccione una opción: ");
            
            int opcion = sc.nextInt();
            sc.nextLine();
            
            switch (opcion) {
                case 1: // Crear cuenta
                    System.out.print("\nNombre del titular: ");
                    String titular = sc.nextLine();
                    System.out.print("Saldo inicial: $");
                    double saldoInicial = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Tipo de cuenta (Corriente/Ahorro): ");
                    String tipo = sc.nextLine();
                    
                    String numeroCuenta = "CC-" + contadorCuentas++;
                    Cuenta nuevaCuenta = new Cuenta(numeroCuenta, titular, saldoInicial, tipo);
                    cuentas.put(numeroCuenta, nuevaCuenta);
                    
                    System.out.println("\n✓ Cuenta creada exitosamente!");
                    System.out.println(nuevaCuenta);
                    break;
                    
                case 2: // Depositar
                    System.out.print("\nNúmero de cuenta: ");
                    String cuentaDep = sc.nextLine();
                    if (!cuentas.containsKey(cuentaDep)) {
                        System.out.println("✗ Cuenta no encontrada");
                        break;
                    }
                    System.out.print("Monto a depositar: $");
                    double montoDep = sc.nextDouble();
                    
                    if (cuentas.get(cuentaDep).depositar(montoDep)) {
                        System.out.println("✓ Depósito realizado correctamente");
                        System.out.println("Saldo actual: $" + cuentas.get(cuentaDep).saldo);
                    } else {
                        System.out.println("✗ Monto inválido");
                    }
                    break;
                    
                case 3: // Retirar
                    System.out.print("\nNúmero de cuenta: ");
                    String cuentaRet = sc.nextLine();
                    if (!cuentas.containsKey(cuentaRet)) {
                        System.out.println("✗ Cuenta no encontrada");
                        break;
                    }
                    System.out.print("Monto a retirar: $");
                    double montoRet = sc.nextDouble();
                    
                    if (cuentas.get(cuentaRet).retirar(montoRet)) {
                        System.out.println("✓ Retiro realizado correctamente");
                        System.out.println("Saldo actual: $" + cuentas.get(cuentaRet).saldo);
                    } else {
                        System.out.println("✗ Fondos insuficientes o monto inválido");
                    }
                    break;
                    
                case 4: // Transferir
                    System.out.print("\nCuenta origen: ");
                    String cuentaOrigen = sc.nextLine();
                    System.out.print("Cuenta destino: ");
                    String cuentaDestino = sc.nextLine();
                    
                    if (!cuentas.containsKey(cuentaOrigen) || !cuentas.containsKey(cuentaDestino)) {
                        System.out.println("✗ Una o ambas cuentas no existen");
                        break;
                    }
                    
                    System.out.print("Monto a transferir: $");
                    double montoTrans = sc.nextDouble();
                    
                    Cuenta origen = cuentas.get(cuentaOrigen);
                    Cuenta destino = cuentas.get(cuentaDestino);
                    
                    if (origen.retirar(montoTrans)) {
                        destino.depositar(montoTrans);
                        origen.registrarTransaccion("Transferencia enviada a " + cuentaDestino + ": -$" + montoTrans);
                        destino.registrarTransaccion("Transferencia recibida de " + cuentaOrigen + ": +$" + montoTrans);
                        System.out.println("✓ Transferencia completada exitosamente");
                    } else {
                        System.out.println("✗ Transferencia fallida - Fondos insuficientes");
                    }
                    break;
                    
                case 5: // Consultar saldo
                    System.out.print("\nNúmero de cuenta: ");
                    String cuentaCons = sc.nextLine();
                    if (cuentas.containsKey(cuentaCons)) {
                        System.out.println("\n" + cuentas.get(cuentaCons));
                    } else {
                        System.out.println("✗ Cuenta no encontrada");
                    }
                    break;
                    
                case 6: // Solicitar préstamo
                    System.out.print("\nNúmero de cuenta: ");
                    String cuentaPrest = sc.nextLine();
                    if (!cuentas.containsKey(cuentaPrest)) {
                        System.out.println("✗ Cuenta no encontrada");
                        break;
                    }
                    System.out.print("Monto del préstamo: $");
                    double montoPrest = sc.nextDouble();
                    System.out.print("Plazo en meses: ");
                    int plazo = sc.nextInt();
                    
                    String idPrestamo = "PREST-" + contadorPrestamos++;
                    Prestamo nuevoPrestamo = new Prestamo(idPrestamo, cuentaPrest, montoPrest, plazo, 0.15);
                    prestamos.put(idPrestamo, nuevoPrestamo);
                    
                    cuentas.get(cuentaPrest).depositar(montoPrest);
                    cuentas.get(cuentaPrest).registrarTransaccion("Préstamo aprobado " + idPrestamo + ": +$" + montoPrest);
                    
                    System.out.println("\n✓ Préstamo aprobado!");
                    System.out.println(nuevoPrestamo);
                    System.out.printf("Cuota mensual: $%.2f\n", nuevoPrestamo.calcularCuotaMensual());
                    break;
                    
                case 7: // Pagar cuota
                    System.out.print("\nID del préstamo: ");
                    String idPago = sc.nextLine();
                    if (!prestamos.containsKey(idPago)) {
                        System.out.println("✗ Préstamo no encontrado");
                        break;
                    }
                    
                    Prestamo prestamo = prestamos.get(idPago);
                    if (prestamo.estaLiquidado()) {
                        System.out.println("✓ Este préstamo ya está liquidado");
                        break;
                    }
                    
                    double cuota = prestamo.calcularCuotaMensual();
                    System.out.printf("Cuota a pagar: $%.2f\n", cuota);
                    System.out.print("Confirmar pago (S/N): ");
                    String confirma = sc.nextLine();
                    
                    if (confirma.equalsIgnoreCase("S")) {
                        Cuenta cuentaPago = cuentas.get(prestamo.numeroCuenta);
                        if (cuentaPago.retirar(cuota)) {
                            prestamo.pagarCuota(cuota);
                            cuentaPago.registrarTransaccion("Pago cuota préstamo " + idPago + ": -$" + cuota);
                            System.out.println("✓ Cuota pagada exitosamente");
                            System.out.println(prestamo);
                        } else {
                            System.out.println("✗ Fondos insuficientes para pagar la cuota");
                        }
                    }
                    break;
                    
                case 8: // Calcular intereses
                    System.out.print("\nNúmero de cuenta: ");
                    String cuentaInt = sc.nextLine();
                    if (cuentas.containsKey(cuentaInt)) {
                        Cuenta cuenta = cuentas.get(cuentaInt);
                        double interes = cuenta.calcularInteres();
                        System.out.printf("\nInterés calculado (anual): $%.2f\n", interes);
                        System.out.print("¿Aplicar interés a la cuenta? (S/N): ");
                        if (sc.nextLine().equalsIgnoreCase("S")) {
                            cuenta.depositar(interes);
                            System.out.println("✓ Interés aplicado!");
                        }
                    } else {
                        System.out.println("✗ Cuenta no encontrada");
                    }
                    break;
                    
                case 9: // Ver historial
                    System.out.print("\nNúmero de cuenta: ");
                    String cuentaHist = sc.nextLine();
                    if (cuentas.containsKey(cuentaHist)) {
                        System.out.println("\n═══ HISTORIAL DE TRANSACCIONES ═══");
                        for (String trans : cuentas.get(cuentaHist).historialTransacciones) {
                            System.out.println(trans);
                        }
                    } else {
                        System.out.println("✗ Cuenta no encontrada");
                    }
                    break;
                    
                case 10: // Listar cuentas
                    System.out.println("\n═══════════ TODAS LAS CUENTAS ═══════════");
                    if (cuentas.isEmpty()) {
                        System.out.println("No hay cuentas registradas");
                    } else {
                        for (Cuenta c : cuentas.values()) {
                            System.out.println(c);
                        }
                    }
                    break;
                    
                case 11: // Estadísticas
                    System.out.println("\n╔════════════ ESTADÍSTICAS DEL SISTEMA ════════════╗");
                    System.out.println("║ Total de cuentas: " + cuentas.size());
                    System.out.println("║ Total de préstamos: " + prestamos.size());
                    
                    double totalDinero = 0;
                    int cuentasAhorro = 0;
                    int cuentasCorriente = 0;
                    
                    for (Cuenta c : cuentas.values()) {
                        totalDinero += c.saldo;
                        if (c.tipoCuenta.equals("Ahorro")) cuentasAhorro++;
                        else cuentasCorriente++;
                    }
                    
                    System.out.printf("║ Capital total en el sistema: $%.2f\n", totalDinero);
                    System.out.println("║ Cuentas de ahorro: " + cuentasAhorro);
                    System.out.println("║ Cuentas corrientes: " + cuentasCorriente);
                    
                    double totalPrestado = 0;
                    for (Prestamo p : prestamos.values()) {
                        totalPrestado += p.montoTotal;
                    }
                    System.out.printf("║ Total prestado: $%.2f\n", totalPrestado);
                    System.out.println("╚═══════════════════════════════════════════════════╝");
                    break;
                    
                case 0:
                    System.out.println("\n¡Gracias por usar el Sistema Bancario! Hasta pronto.");
                    salir = true;
                    break;
                    
                default:
                    System.out.println("✗ Opción inválida");
            }
        }
        
        sc.close();
    }
}