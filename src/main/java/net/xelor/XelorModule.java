package net.xelor;

import net.xelor.logger.Logger;
import net.xelor.services.DataService;

public class XelorModule {
    private static XelorModule instance;

    public static void start() {
        if (instance == null) {
            instance = new XelorModule();
        }
    }

    public static XelorModule getInstance() {
        return instance;
    }

    public static boolean shutdown(ShutdownType type, String err) {
        if (instance != null) {
            if (type == ShutdownType.KILL && err.isBlank()) {
                err = "Error 509";
            }
            if (type == ShutdownType.KILL || type == ShutdownType.HARD) {
                Logger.severe(err);
                instance = null;
            } else {
                Logger.info(err.isBlank() && err.isEmpty() ? "Shutdown" : "Shutdown" + err);
                instance = null;
            }
            return true;
        }
        Logger.warn("Unable to shutdown instance");
        return false;
    }

    private XelorModule() {
        Logger.logFine("XELOR Loading...");
        DataService.registerServices();
        DataService.getServices().forEach(DataService::init);
        Logger.logFine("XELOR Enabled");
    }

}
enum ShutdownType {
    SOFT("Soft Shutdown"),
    HARD("Hard Shutdown"),
    KILL("System killed");

    private final String shutdownReason;

    ShutdownType(final String reason) {
        this.shutdownReason = reason;
    }

    public String getShutdownReason() {
        return shutdownReason;
    }
}
