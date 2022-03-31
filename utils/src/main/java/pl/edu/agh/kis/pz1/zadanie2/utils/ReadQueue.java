package pl.edu.agh.kis.pz1.zadanie2.utils;

import java.util.LinkedList;
import java.util.Queue;

public class ReadQueue {
    /**
     * Flaga pozwalająca sprawdzić czy w czytelni jest pisarz
     */
    private boolean underWriting = false;

    /**
     * Pole przechowujące max. liczbę czytelników przebywających naraz w czytelni
     */
    private int maxReaders = 5;

    /**
     * Pole przechowujące liczbę czytelników aktualnie przebywających w czytelni
     */
    private int readersQty = 0;

    /**
     * Pole pozwalające zapisać kolejność czytelników w jakiej czekają
     * w kolejce do czytelni
     */
    private Queue<Integer> readers = new LinkedList<>();

    /**
     * Metoda dodająca czytelnika do kolejki oczekujących czytelników
     * @param id id czytelnika
     */
    public void addReaderToQueue(int id){
        readers.add(id);
    }

    /**
     * @return flaga czy w czytelni przebywa pisarz
     */
    public synchronized boolean underWriting()
    {
        return underWriting;
    }

    /**
     * Metoda ustawiająca flagę czy pisarz przebywa w czytelni
     * @param underWriting flaga
     */
    public void setUnderWriting(boolean underWriting)
    {
        this.underWriting = underWriting;
    }

    /**
     * Metoda wywoływana w momencie chęci wejścia czytelnika do czytelni.
     * Gdy w czytelni będzie mniej niż 5 czytelników, żaden pisarz nie czeka
     * i jest teraz jego kolej to zostaje wpuszczony
     * @param id id czytelnika który chce wejść do czytelni
     * @return flaga czy czytelnikowi udało się wejść do czytelni
     */
    public synchronized boolean join(int id)
    {
        if(underWriting || readersQty == maxReaders)
            return false;
        else if(!readers.isEmpty() && readers.element() == id)
        {
            readersQty++;
            readers.poll();
            return true;
        }
        return false;
    }

    /**
     * Metoda wywoływana w momencie kiedy czytelnik opuszcza czytelnię. Odblokowuje
     * możliwość wejścia do czytelni nowego czytelnika
     */
    public void leave()
    {
        readersQty--;
    }

    /**
     * @return liczba czytelników aktualnie przebywających w czytelni
     */
    public synchronized int getReadersQty()
    {
        return readersQty;
    }
}
