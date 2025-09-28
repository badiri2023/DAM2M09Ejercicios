package com.project;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        
        ConcurrentHashMap<String, Double> saldo = new ConcurrentHashMap<>();

        ExecutorService executor = Executors.newFixedThreadPool(3);
        Runnable Task = new Task(saldo);
        Runnable Task2 = new Task2(saldo);

            
        try {
            // Lanzamos las 2 tareas Runnable
            executor.execute(new Task(saldo));
            executor.execute(new Task2(saldo));
            
            Callable<Double> tasca3 = new Task3(saldo);

            Future<Double> resultado = executor.submit(tasca3);


            // Esperamos al resultado y lo mostramos
            Double saldoFinal = resultado.get();
            System.out.println("[Main] Resultado final de la operación bancaria = " + saldoFinal + " €");


        } catch (Exception e) {
                System.out.println("Uy Uy Noty Noty.");

        }finally{
            executor.shutdown();
            System.out.println("Executor tancat.");
        }
 
    }
}
