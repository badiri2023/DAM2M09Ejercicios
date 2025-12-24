package com.project;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // Conjunt de dades
        double[] data = {2, 4, 6, 8, 10};

        // Resultats compartits
        Map<String, Double> results = new ConcurrentHashMap<>();

        // Creem la barrera per a 3 fils
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            // Quan tots han acabat, mostrem els resultats
            System.out.println("All tasks finished, showing....");
            System.out.println("Suma = " + results.get("suma"));
            System.out.println("Mitjana = " + results.get("mitjana"));
            System.out.println("Desviació estàndard = " + results.get("desviacio"));
        });

        // Executor amb 3 fils
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Tasca per calcular la suma
        Runnable sumaTask = () -> {
            try {
                double suma = 0;
                for (double d : data) suma += d;
                results.put("suma", suma);
                System.out.println("Càlcul de la suma completat.");
                barrier.await();
            } catch (Exception e) { e.printStackTrace(); }
        };

        // Tasca per calcular la mitjana
        Runnable mitjanaTask = () -> {
            try {
                double suma = 0;
                for (double d : data) suma += d;
                double mitjana = suma / data.length;
                results.put("mitjana", mitjana);
                System.out.println("Càlcul de la mitjana completat.");
                barrier.await();
            } catch (Exception e) { e.printStackTrace(); }
        };

        // Tasca per calcular la desviació estàndard
        Runnable desviacioTask = () -> {
            try {
                double suma = 0;
                for (double d : data) suma += d;
                double mitjana = suma / data.length;

                double sumSquares = 0;
                for (double d : data) sumSquares += Math.pow(d - mitjana, 2);
                double desviacio = Math.sqrt(sumSquares / data.length);

                results.put("desviacio", desviacio);
                System.out.println("Càlcul de la desviació estàndard completat.");
                barrier.await();
            } catch (Exception e) { e.printStackTrace(); }
        };

        // Llançar les tasques
        executor.submit(sumaTask);
        executor.submit(mitjanaTask);
        executor.submit(desviacioTask);

        // Tancar executor
        executor.shutdown();
    }
}
