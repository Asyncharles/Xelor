package net.xelor;

import net.xelor.devices.DeviceManager;
import net.xelor.house.HouseManager;
import net.xelor.logger.LoggerProvider;
import net.xelor.services.DataService;
import net.xelor.weather.WeatherDataProvider;
import net.xelor.structures.energy.light.LightDataHandler;

import java.security.KeyStoreException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class XelorModule {
    private static XelorModule instance;
    public static Logger LOGGER;

    private final WeatherDataProvider weatherDataProvider;

    private final HouseManager houseManager;
    private final DeviceManager deviceManager;

    private final LightDataHandler lightDataHandler;

    public static void start() {
        if (instance == null) {
            instance = new XelorModule();
            Debug.debug();
        }
    }

    public static XelorModule getInstance() {
        return instance;
    }

    public static boolean shutdown(ShutdownType type, String err) {
        if (instance != null) {
            if (type == ShutdownType.KILL && err.isEmpty()) {
                err = "Error 509";
            }
            if (type == ShutdownType.KILL || type == ShutdownType.HARD) {
                LOGGER.severe(err);
                instance = null;
            } else {
                LOGGER.info(err.isEmpty() ? "Shutdown" : "Shutdown" + err);
                instance = null;
            }
            return true;
        }
        LOGGER.warning("Unable to shutdown instance");
        return false;
    }

    private XelorModule() {
        initLogger();

        LOGGER.info("XELOR Loading...");

        DataService.registerServices();

        DataService.getServices().forEach(ds -> {
            try {
                ds.init();
            } catch (KeyStoreException exception) {
                exception.printStackTrace();
            }
        });


        weatherDataProvider = new WeatherDataProvider("987be881e33aef71576d950f86296613", "London", true);

        houseManager = new HouseManager("Test House");
        deviceManager = new DeviceManager();

        lightDataHandler = new LightDataHandler();

        LOGGER.fine("XELOR Enabled");
    }

    private void initLogger() {
        LoggerProvider.setProvider(s -> {
            final ConsoleHandler consoleHandler = new ConsoleHandler() {{
                setLevel(Level.ALL);
                setFormatter(new Formatter() {
                    private static final String PATTERN = "[dd/MM/yyyy HH:mm:ss]";

                    @Override
                    public String format(final LogRecord record) {
                        return String.format("%1$s %2$-10s %3$-10s %4$s\n",
                                new SimpleDateFormat(PATTERN).format(new Date(record.getMillis())),
                                "[" + record.getLoggerName() + "]",
                                record.getLevel().getName(),
                                formatMessage(record));
                    }
                });
            }};
            final Logger logger = Logger.getLogger(s);
            logger.setUseParentHandlers(false);
            logger.setLevel(Level.ALL);
            logger.addHandler(consoleHandler);
            return logger;
        });
        XelorModule.LOGGER = LoggerProvider.getLogger("Xelor");
    }

    public WeatherDataProvider getWeatherDataProvider() {
        return weatherDataProvider;
    }

    public HouseManager getHouseManager() {
        return houseManager;
    }

    public DeviceManager getDeviceManager() {
        return deviceManager;
    }

    public LightDataHandler getLightDataHandler() {
        return lightDataHandler;
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
