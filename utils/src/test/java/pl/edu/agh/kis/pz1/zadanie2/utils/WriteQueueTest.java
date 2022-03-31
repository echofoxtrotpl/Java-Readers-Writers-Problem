package pl.edu.agh.kis.pz1.zadanie2.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WriteQueueTest {
    ReadQueue readQueue = new ReadQueue();
    WriteQueue writeQueue = new WriteQueue(readQueue);

    @Test
    public void testJoinWhenAnotherWriterIsComing(){
        writeQueue.addWriterToQueue(0);
        writeQueue.writerComing();
        writeQueue.addWriterToQueue(1);
        writeQueue.writerComing();
        assertFalse(writeQueue.join(1));
    }

    @Test
    public void testJoinWhenAnyReader(){
        readQueue.addReaderToQueue(0);
        assertTrue(readQueue.join(0));
        writeQueue.addWriterToQueue(0);
        writeQueue.writerComing();
        assertFalse(writeQueue.join(0));
    }

    @Test
    public void testJoinAfterAllReadersLeave(){
        readQueue.addReaderToQueue(0);
        readQueue.addReaderToQueue(1);
        assertTrue(readQueue.join(0));
        assertTrue(readQueue.join(1));
        writeQueue.addWriterToQueue(0);
        writeQueue.writerComing();
        assertFalse(writeQueue.join(0));
        readQueue.leave();
        readQueue.leave();
        assertTrue(writeQueue.join(0));
    }
}
