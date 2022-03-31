package pl.edu.agh.kis.pz1.zadanie2.utils;
import java.util.Random;

public class Resource {
    /**
     * Pole pozwalające przechować referencję do
     * generatora liczb pseudolosowych
     */
    private Random randomGenerator = new Random();

    /**
     * Pole pozwalające przechować referencję do
     * kolejki czytelników
     */
    private ReadQueue readQueue;

    /**
     * Pole pozwalające przechować referencję do
     * kolejki pisarzy
     */
    private WriteQueue writeQueue;

    /**
     * Pole pozwalające na inicjalizację loggera
     */
    private CustomLogger logger;

    /**
    * Kontruktor inicjujący dwie osobne kolejki do czytelni
     * czytelników i pisarzy
    */
    public Resource(){
        logger = new CustomLogger("Resource");
        readQueue = new ReadQueue();
        writeQueue = new WriteQueue(readQueue);
    }

    /**
     * Metoda wywoływana przez wątek w momencie gdy dany czytelnik
     * chce wejść do czytelni
     * @param id id czytelnika
     * @throws InterruptedException
     */
    public void read(int id) throws InterruptedException{
        String name = "Czytelnik " + id;

        Thread.sleep(randomTime(1000,3000));

        // Zapisanie kolejności w jakiej czytelnicy poprosili
        // o wejście do czytelni
        readQueue.addReaderToQueue(id);

        if(readQueue.underWriting())
        {
            if(writeQueue.getWritersQty() == 1){
                logger.log(null,name + " chce czytać ale czytelnia jest zajęta przez 1 pisarza");
            } else {
                logger.log(null,name + " chce czytać ale w kolejce oczekuje: " + (writeQueue.getWritersQty() - 1) + " pisarzy");
            }
        }
        else
            logger.log(null,name + " chce czytać. Zasób jest aktualnie czytany przez " + readQueue.getReadersQty() + " czyteników");

        // Próbuj dołączyć do czytelni zgodnie z kolejnością
        // w jakiej czytelnik jest w kolejce czytelników
        while(!readQueue.join(id));

        logger.log(null,name + " zaczął czytać. Zasób jest czytany przez " + readQueue.getReadersQty() + " czytelników");
        Thread.sleep(randomTime(1000,3000));
        logger.log(null,name + " skończył czytać. Zasób jest czytany przez " + (readQueue.getReadersQty()-1) + " czytelników");

        // Czytelnik wychodzi z czytelni
        readQueue.leave();
    }

    /**
     * Metoda wywoływana przez wątek w momencie gdy dany pisarz
     * chce wejść do czytelni
     * @param id id pisarza
     * @throws InterruptedException
     */
    public void write(int id) throws InterruptedException{
        String name = "Pisarz " + id;

        Thread.sleep(randomTime(1000,3000));

        // Zapisanie kolejności w jakiej pisarze poprosili
        // o wejście do czytelni
        writeQueue.addWriterToQueue(id);

        if(writeQueue.underWriting()){
            if(writeQueue.getWritersQty() == 1)
                logger.log(null,name + " chce pisać ale w czytelni przebywa 1 pisarz");
            else
                logger.log(null,name + " chce pisać ale na dostęp do czytelni oczekuje: " + (writeQueue.getWritersQty() -1) + " pisarzy");
        }
        else
            logger.log(null,name + " chce pisać. Zasób jest aktualnie czytany przez " + writeQueue.getReadersQty() + " czyteników");

        // Uniemożliwienie nowym czytelnikom dołączenia dopóki
        // dany pisarz nie opuści czytelni
        writeQueue.writerComing();

        // Próbuj dołączyć do czytelni zgodnie z kolejnością
        // w jakiej pisarz jest w kolejce pisarzy
        while(!writeQueue.join(id));

        logger.log(null,name + " zaczął pisać. Zasób jest aktualnie przepisywany");
        Thread.sleep(randomTime(1000, 3000));
        logger.log(null,name + " skończył pisać. Zasób został zwolniony");

        // Pisarz wychodzi z czytelni
        writeQueue.leave();
        Thread.sleep(randomTime(1000,3000));
    }

    /**
     * Generator losowej liczby milisekund
     * będącej czasem pobytu w czytelni
     * @param randMax górny zakres losowej liczby
     * @param randMin dolny zakres losowej liczby
     * @return losowa liczba milisekund
     */
    public int randomTime(int randMin, int randMax)
    {
        return (randomGenerator.nextInt(randMax-randMin) + randMin);
    }
}
