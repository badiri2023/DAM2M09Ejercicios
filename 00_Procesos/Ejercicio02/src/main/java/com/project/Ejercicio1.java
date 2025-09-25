package com.project;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.project.Task;
import com.project.Task2;

public class Ejercicio1 {
    public static void main(String[] args) {
        
        ConcurrentHashMap<String, Double> compte = new ConcurrentHashMap<>();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Enviar dos tareas para que se ejecuten en paralelo
        executor.submit(new Task());
        executor.submit(new Task2());
        Callable<Double> tasca3 = new Task3(compte);
        
        executor.shutdown();
        System.out.println("Executor tancat.");
    }
}
