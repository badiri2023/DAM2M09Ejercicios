package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class ParkingLot {
    private final Semaphore semaphore;

    public ParkingLot(int capacity) {
        this.semaphore = new Semaphore(capacity);
    }

    public void enter(String carName) throws InterruptedException {
        System.out.println(carName + " intenta entrar...");
        semaphore.acquire();
        System.out.println(carName + " ha entrat.");
    }

    public void exit(String carName) {
        System.out.println(carName + " surt.");
        semaphore.release();
    }
}

public class Main {
    public static void main(String[] args) {
        ParkingLot parking = new ParkingLot(2); // capacitat de 2 places
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Runnable carTask = () -> {
            String carName = Thread.currentThread().getName();
            try {
                parking.enter(carName);
                Thread.sleep(2000); // simula temps aparcat
                parking.exit(carName);
            } catch (InterruptedException e) { e.printStackTrace(); }
        };

        for (int i = 1; i <= 5; i++) {
            executor.submit(carTask);
        }

        executor.shutdown();
    }
}
