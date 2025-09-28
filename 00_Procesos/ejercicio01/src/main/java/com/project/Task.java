package com.project;

import java.util.concurrent.ConcurrentHashMap;

public class Task implements Runnable {
    private ConcurrentHashMap<String, Double> saldo;

    public Task(ConcurrentHashMap<String, Double> datosCompartidos) {
        this.saldo = datosCompartidos;
    }
    @Override
    public void run() {
        System.out.println("Iniciando introduccion de saldo...");
        saldo.put("saldo", 3.0);
        System.out.println("Saldo cargado exitosamente !!");

    }
}
