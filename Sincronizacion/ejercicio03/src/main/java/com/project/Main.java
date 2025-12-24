package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class WebPage {
    private final Semaphore semaphore;

    public WebPage(int maxConnections) {
        this.semaphore = new Semaphore(maxConnections);
    }

    public void connect(String userName) {
        try {
            System.out.println(userName + " intenta connectar...");
            semaphore.acquire(); // espera si no hi ha permisos
            System.out.println(userName + " s'ha connectat a la pàgina.");
            // Simulem que l'usuari està connectat un temps
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(String userName) {
        System.out.println(userName + " s'ha desconnectat.");
        semaphore.release(); // allibera un permís
    }
}

public class Main {
    public static void main(String[] args) {
        WebPage page = new WebPage(3); // màxim 3 connexions simultànies
        ExecutorService executor = Executors.newFixedThreadPool(6);

        Runnable userTask = () -> {
            String userName = Thread.currentThread().getName();
            page.connect(userName);
            page.disconnect(userName);
        };

        // Llançar 6 usuaris
        for (int i = 1; i <= 6; i++) {
            executor.submit(userTask);
        }

        executor.shutdown();
    }
}
