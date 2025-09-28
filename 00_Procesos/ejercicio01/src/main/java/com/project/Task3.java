package com.project;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class Task3 implements Callable<Double> {
     private ConcurrentHashMap<String, Double> saldo;

    public Task3(ConcurrentHashMap<String, Double> datosCompartidos) {
        this.saldo = datosCompartidos;
    }

    @Override
    public Double call() {
        System.out.println("[Leer] Leyendo saldo final...");

        Double saldoActual = saldo.get("saldo");

        if (saldoActual != null) {
            System.out.println("[Leer] El saldo final es = " + saldoActual + " €");
            return saldoActual;
        } else {
            System.out.println("[Leer] No se encontró saldo.");
            return 0.0;
        }
    }
}
