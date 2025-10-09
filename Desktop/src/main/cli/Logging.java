package main.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogRecord;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;

import lombok.Getter;

public class Logging {
    @Getter private final Logger logger;

    private String file = null;
    private boolean clearedOnce = false;

    private static class CustomFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {
            String time = String.format("%1$tT.%1$tL", record.getMillis());
            String level = record.getLevel().getName();
            String className = record.getSourceClassName() != null ? record.getSourceClassName() : "unknown";
            String methodName = record.getSourceMethodName() != null ? record.getSourceMethodName() : "unknown";
            String message = formatMessage(record);

            return String.format("[%s] [%s @ %s.%s] %s%n", time, level, className, methodName, message);
        }
    }

    public Logging(Class<?> class_, String path) {
        this.file = Paths.get(path, String.format("%s.log", class_.getSimpleName())).toString();
        this.logger = Logger.getLogger(class_.getName());

        try {
            init(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init(String path) throws IOException {
        Files.createDirectories(Paths.get(path));

        if (logger.getHandlers().length == 0) {
            if (!clearedOnce) {
                Files.deleteIfExists(Path.of(file));
                clearedOnce = true;
            }

            FileHandler fileHandler = new FileHandler(file, true);

            fileHandler.setFormatter(new CustomFormatter());

            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
            logger.setUseParentHandlers(false);
        }
    }
}
