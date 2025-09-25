package com.project;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class Task3 implements Callable<Double> {
 
     private final ConcurrentHashMap<String, Double> compte;

    public Task3(ConcurrentHashMap<String, Double> compte) {
        this.compte = compte;
    }
    @Override
    public Double  call() {
        System.out.println("[TASK 3] Llegint saldo final...");
        
        return compte.getOrDefault("saldo", 0.0);

    }
}
