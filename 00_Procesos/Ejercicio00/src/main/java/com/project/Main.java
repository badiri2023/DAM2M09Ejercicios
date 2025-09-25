package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // Enviar dos tareas para que se ejecuten en paralelo
        executor.submit(new Task());
        executor.submit(new Task2());
        
        executor.shutdown();
        System.out.println("Executor tancat.");
    }
}
