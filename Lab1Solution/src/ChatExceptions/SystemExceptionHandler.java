package ChatExceptions;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * System exceptions handler
 * Kindie Nega
 * back-end
 */
public class SystemExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final String LOG_FILE = "system.log";

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true));
            writer.println("Exception in thread \"" + t.getName() + "\":");
            e.printStackTrace(writer);
            writer.close();
        } catch (IOException ioe) {
            System.err.println("Error writing exception to log file: " + ioe.getMessage());
        }
    }
}