package main;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.cli.Console;
import main.cli.Logging;
import main.utils.Constants;
import main.utils.Data;

public class Main {
    private static final Logger logger = new Logging(Main.class, Constants.LOG_FOLDER_ABS_PATH).getLogger();

    private static void initialChecks() throws UnsupportedEncodingException {
        logger.info("Running initial background checks.");

        if (Constants.MIN_SHOWS_PER_DAY > Constants.MAX_SHOWS_PER_DAY ||
            Constants.MAX_SHOWS_PER_DAY > Constants.MOVIE_MAP.size()) {

            IllegalArgumentException e = new IllegalArgumentException("Invalid MIN/MAX show values.");
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }

        if (!System.getProperty("file.encoding").equalsIgnoreCase("UTF-8")) {
            UnsupportedEncodingException e = new UnsupportedEncodingException("Invalid file encoding format. Switch to UTF-8.");
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }

        if (!System.getProperty("os.name").contains("Windows")) {
            UnsupportedOperationException e = new UnsupportedOperationException("This program is only supported on Windows.");
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public static void main(String... varargs) throws UnsupportedEncodingException {
        initialChecks();

        System.out.println("Starting program execution. Please wait.");

        logger.info("Starting program execution.");

        new Data().init();
        new Console().run();
    }
}
