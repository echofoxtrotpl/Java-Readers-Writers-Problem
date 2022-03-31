package pl.edu.agh.kis.pz1.zadanie2.utils;

import java.util.logging.Level;

public class Reader extends Thread{
    /**
     * Pole pozwalające przechować id czytelnika
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
     * i uruchamiający wątek czytelnika
     * @param id id czytelnika
     * @param resource zasób czytelni
     */
    public Reader(int id, Resource resource)
    {
        logger = new CustomLogger("Reader");
        this.id = id;
        this.resource = resource;
        logger.log(null,"Rozpoczęto wątek czytelnika: " + id);
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
            while(true){
                resource.read(id);
            }
        } catch(InterruptedException e)
        {
            logger.log(Level.WARNING,"Wątek czytelnika " + id + " spowodował problem - " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        logger.log(null,"Wątek czytelnika " + id +  " zakończył się");
    }
}
