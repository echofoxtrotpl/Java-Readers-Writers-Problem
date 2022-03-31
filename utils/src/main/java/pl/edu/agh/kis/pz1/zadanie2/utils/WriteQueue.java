package pl.edu.agh.kis.pz1.zadanie2.utils;

import java.util.LinkedList;
import java.util.Queue;

public class WriteQueue {
    /**
     * Pole pozwalające przechować referencję do
     * kolejki czytelników
     */
    private ReadQueue readQueue;

    /**
     * Pole pozwalające przechować liczbę pisarzy przebywających w czytelni
     */
    private int writersQty = 0;

    /**
     * Flaga pozwalająca sprawdzić czy w czytelni jest pisarz
     */
    private boolean underWriting = false;

    /**
     * Pole pozwalające zapisać kolejność pisarzy w jakiej czekają
     * w kolejce do czytelni
     */
    private Queue<Integer> writers = new LinkedList<>();

    /**
     * Konstruktor przypisujący kolejce pisarzy referencję podaną
     * w parametrze
     * @param readQueue referencja do kolejki pisarzy
     */
    public WriteQueue(ReadQueue readQueue)
    {
        this.readQueue = readQueue;
    }

    /**
     * Metoda dodająca do kolejki pisarza
     * @param id id pisarza dodanego do kolejki
     */
    public void addWriterToQueue(int id){
        writers.add(id);
    }

    /**
     * Metoda wywoływana gdy pisarz wyrazi chęć wejścia do czytelni
     * tak aby zablokować możliwość wejścia do czytelni nowym czytelnikom
     */
    public void writerComing()
    {
        readQueue.setUnderWriting(true);
        writersQty++;
    }

    /**
     * Metoda wywoływana w momencie chęci wejścia
     * pisarza do czytelni. Gdy w czytelni nie będzie już
     * żadnego czytelnika ani pisarza i jest teraz jego kolej
     * to zostaje wpuszczony
     * @param id id pisarza który chce wejść do czytelni
     * @return flaga czy pisarzowi udało się wejść do czytelni
     */
    public synchronized boolean join(int id)
    {
        if(underWriting || getReadersQty() != 0)
        {
            return false;
        }
        else if(!writers.isEmpty() &&writers.element() == id)
        {
            underWriting = true;
            writers.poll();
            return true;
        }
        return false;
    }

    /**
     * Metoda wywoływana w momencie kiedy pisarz opuszcza czytelnię. Odblokowuje
     * możliwość wejścia do czytelni nowego pisarza jeśli czeka w kolejce bądź
     * 5 nowych czytelników
     */
    public void leave()
    {
        underWriting = false;
        writersQty--;
        if(writersQty == 0){
            readQueue.setUnderWriting(false);
        }
    }

    /**
     * @return flaga czy w czytelni przebywa pisarz
     */
    public synchronized boolean underWriting()
    {
        return underWriting;
    }

    /**
     * @return liczba czytelników aktualnie przebywających w czytelni
     */
    public synchronized int getReadersQty()
    {
        return readQueue.getReadersQty();
    }

    /**
     * @return liczba pisarzy czekających na dostęp do czytelni
     */
    public synchronized int getWritersQty() { return writersQty; }
}
