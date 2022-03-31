# Java Readers Writers problem
**Creation date: 2022-01-14**

## Omówienie zaimplementowanego algorytmu

Problem czytelników i pisarzy to klasyczny informatyczny problem synchronizacji dostępu do jednego zasobu dwóch rodzajów procesów:
dokonujących i niedokonujących w nim zmian. 

Zaimplementowane klasy to Resource, Reader, Writer, ReadQueue, WriteQueue.
W metodzie main jest tworzone 10 obiektów czytelników i 3 pisarzy. W konstruktorach tych obiektów wywoływana jest od razu metoda start()
każdego z wątków, ponieważ klasy te dziedziczą po klasie Thread. Implementacja metody run() to nic innego, jak wywołanie w nieskończonej pętli
próby dostępu do zasobu - metody read() oraz write(). Metody te wyświetlają na standardowe wyjście informajce o tym, jaki wątek chce uzystać dostęp
oraz ilu czytelników/pisarzy jest w kolejce do czytelni a ilu jest w środku. 

Aby piszarze i czytelnicy pojawiali się w kolejce do czytelni w losowych
kolejnościach czas przebywania w czytelni jest losowany w przedziale od 1s do 3s. Zaimplementowana wersja algorytmu zakłada, że jeżeli w kolejce
pojawia się pisarz to blokuje on dostęp nowym czytelnikom i pisarzom dopóki nie wyjdzie z czytelni.

## Sposób uruchomienia programu

Aby uruchomić projekt należy z menu kontekstowego Mavena wybrać kolejno:

root -> lifecycle -> package

Utworzy to paczkę jar z zależnościami, którą można uruchomić za pomocą:

```bash
java -jar main-1.0-jar-with-dependencies.jar
```

