package pl.edu.agh.kis.pz1.zadanie2.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger {
    private Logger logger;

    public CustomLogger(String className){
        logger = Logger.getLogger(className);
    }

    public void log(Level level, String message){
        if(level == null){
            System.out.println(message);
        } else {
            logger.log(level, message);
        }
    }
}
