package com.project;

import java.util.concurrent.ConcurrentHashMap;

public class Task2 implements Runnable {
    private ConcurrentHashMap<String, Double> saldo;
    public Task2(ConcurrentHashMap<String, Double> datosCompartidos) {
        this.saldo = datosCompartidos;
    }
    @Override
    public void run() {
        System.out.println("Modificando saldo...");

        Double saldoActual=saldo.get("saldo");
        
        if(saldoActual!=null){
            double newSaldo = saldoActual*2;
                        saldo.put("saldo", newSaldo);
            System.out.println("Se aposto el saldo y se dobló= " + newSaldo + " Dirjams");

        }else{
            System.out.println("no se ha encontrado Saldo para apostar.");
        }




    }
}