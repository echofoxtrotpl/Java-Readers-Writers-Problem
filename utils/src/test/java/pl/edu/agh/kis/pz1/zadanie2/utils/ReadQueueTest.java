package pl.edu.agh.kis.pz1.zadanie2.utils;

import org.junit.Test;
import static org.junit.Assert.*;


public class ReadQueueTest {
    ReadQueue readQueue = new ReadQueue();

    @Test
    public void testJoinWhenUnderWriting(){
        readQueue.setUnderWriting(true);
        assertFalse(readQueue.join(0));
    }

    @Test
    public void testJoinWhenAlready5Readers(){
        for(int i = 0; i < 5; ++i){
            readQueue.addReaderToQueue(i);
            assertTrue(readQueue.join(i));
        }

        assertFalse(readQueue.join(5));
    }

    @Test
    public void testJoinWhenWrongOrder(){
        readQueue.addReaderToQueue(1);
        readQueue.addReaderToQueue(2);

        assertFalse(readQueue.join(2));
    }
}
