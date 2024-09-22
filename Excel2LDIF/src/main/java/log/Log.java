package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    private static Logger logger = LoggerFactory.getLogger(Log.class.getName());

    public static void printInfo(String info) {
        logger.info(info);
    }

    public static void printError(String info) {
        logger.error(info);
    }
}

