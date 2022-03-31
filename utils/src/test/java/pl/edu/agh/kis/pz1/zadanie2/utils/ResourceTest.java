package pl.edu.agh.kis.pz1.zadanie2.utils;

import org.junit.Test;

import java.util.logging.Level;

import static org.junit.Assert.*;

public class ResourceTest {

    Resource resource = new Resource();
    CustomLogger logger = new CustomLogger("Test");

    @Test
    public void testRandom(){
        int random = resource.randomTime(1000, 2000);
        assertTrue(random <= 2000 && random >= 1000);
    }

    @Test
    public void testRead(){
        try{
            resource.read(0);
        } catch (InterruptedException e){
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    @Test
    public void testWrite(){
        try{
            resource.write(0);
        } catch (InterruptedException e){
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}
