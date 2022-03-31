package pl.edu.agh.kis.pz1.zadanie2.utils;

import java.util.logging.Level;

public class Writer extends Thread{
    /**
     * Pole pozwalające przechować id pisarza
     */
    private int id;

    /**
     * Pole pozwalające przechować referencję do
     * zasobu
     */
    private Resource resource;

    /**
     * Pole pozwalające na inicjalizację loggera
     */
    private CustomLogger logger;

    /**
     * Kontruktor inicjujący pola podane w parametrze
     * i uruchamiający wątek pisarza
     * @param id id pisarza
     * @param resource zasób czytelni
     */
    public Writer(int id, Resource resource)
    {
        logger = new CustomLogger("Writer");
        this.id = id;
        this.resource = resource;
        logger.log(null,"Rozpoczęto wątek pisarza: " + id);
        start();
    }

    /**
     * Implementacja metody run() ze względu na dziedziczenie
     * po klasie Thread
     */
    @Override
    public void run()
    {
        try
        {
            while (true){
                resource.write(id);
            }
        } catch(InterruptedException e)
        {
            logger.log(Level.WARNING,"Wątek pisarza " + id +  " spowodował problem - " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        logger.log(null,"Wątek pisarza " + id + " zakończył się");
    }
}
