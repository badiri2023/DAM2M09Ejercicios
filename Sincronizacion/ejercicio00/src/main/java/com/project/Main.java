package com.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // Llista compartida per guardar els resultats parcials
        List<String> results = Collections.synchronizedList(new ArrayList<>());

        // Creem la barrera per a 3 fils
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            // Quan tots han acabat, combinem els resultats
            System.out.println("Tots els microserveis han acabat. Resultat final:");
            String finalResult = String.join(" + ", results);
            System.out.println(finalResult);
        });

        // Executor amb 3 fils
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Definim les tasques
        Runnable microservice1 = () -> {
            try {
                System.out.println("Microservei 1 processant dades...");
                Thread.sleep(1000);
                results.add("Resultat 1");
                System.out.println("Microservei 1 completat.");
                barrier.await();
            } catch (Exception e) { e.printStackTrace(); }
        };

        Runnable microservice2 = () -> {
            try {
                System.out.println("Microservei 2 processant dades...");
                Thread.sleep(1500);
                results.add("Resultat 2");
                System.out.println("Microservei 2 completat.");
                barrier.await();
            } catch (Exception e) { e.printStackTrace(); }
        };

        Runnable microservice3 = () -> {
            try {
                System.out.println("Microservei 3 processant dades...");
                Thread.sleep(2000);
                results.add("Resultat 3");
                System.out.println("Microservei 3 completat.");
                barrier.await();
            } catch (Exception e) { e.printStackTrace(); }
        };

        // Llançar les tasques
        executor.submit(microservice1);
        executor.submit(microservice2);
        executor.submit(microservice3);

        // Tancar executor
        executor.shutdown();
    }
}
