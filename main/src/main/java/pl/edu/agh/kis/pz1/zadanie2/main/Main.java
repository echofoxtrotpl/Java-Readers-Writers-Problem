package pl.edu.agh.kis.pz1.zadanie2.main;

import pl.edu.agh.kis.pz1.zadanie2.utils.*;

import java.util.logging.Level;

public class Main {

    public static void main(String[] args){
        CustomLogger logger = new CustomLogger("Main");
        Resource resource = new Resource();
        Reader[] readers = new Reader[10];
        Writer[] writers = new Writer[3];

        for (int i = 0; i < 10; ++i){
            readers[i] = new Reader(i, resource);
        }

        for (int i = 0; i < 3; ++i){
            writers[i] = new Writer(i, resource);
        }

        try{
            // Oczekiwanie na zakończenie się wszystkich wątków
            for(Reader reader: readers){
                reader.join();
            }
            for(Writer writer: writers){
                writer.join();
            }
        } catch (InterruptedException e){
            logger.log(Level.WARNING, e.getMessage());
            Thread.currentThread().interrupt();
        }

        logger.log(null, "Koniec");
    }

}
