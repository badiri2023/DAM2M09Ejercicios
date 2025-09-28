package com.project;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Main {
    public static void main(String[] args) {

        System.out.println("=== Inicio del flujo asíncrono con ExecutorService ===");

        // Creamos un pool de hilos con 2 hilos, usamos daemon threads para no retener la JVM
        ExecutorService exec = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);  // hilo daemon, se cierra al terminar la JVM
            return t;
        });

        try {
            // -----------------------------
            // Tarea 1: Validación de datos
            // -----------------------------
            CompletableFuture<Integer> futura1 = CompletableFuture.supplyAsync(() -> {
                System.out.println("[Tarea1] Validando datos de la solicitud...");
                int valorInicial = 10;  // simulamos un dato inicial
                System.out.println("[Tarea1] Datos válidos: " + valorInicial);
                return valorInicial;
            }, exec); // ejecutamos en el pool de hilos

            // ---------------------------------
            // Tarea 2: Procesamiento de datos
            // ---------------------------------
            CompletableFuture<Integer> futura2 = futura1.thenApplyAsync(dato -> {
                System.out.println("[Tarea2] Procesando dato...");
                int resultado = dato * 2; // simulamos un cálculo
                System.out.println("[Tarea2] Resultado procesado: " + resultado);
                return resultado;
            }, exec);

            // ---------------------------------
            // Tarea 3: Mostrar resultado final
            // ---------------------------------
            CompletableFuture<Void> futura3 = futura2.thenAcceptAsync(resultado -> {
                System.out.println("[Tarea3] Enviando respuesta al usuario...");
                System.out.println("[Tarea3] Resultado final = " + resultado);
            }, exec);

            // -----------------------------
            // Esperar a que toda la cadena termine
            // -----------------------------
            futura3.join();

            System.out.println("=== Flujo de procesamiento completado ===");

        } finally {
            // Cerramos el executor para liberar recursos
            exec.shutdown();
        }
    }
}