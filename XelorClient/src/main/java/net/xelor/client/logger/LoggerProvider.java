package net.xelor.client.logger;

import java.util.function.Function;
import java.util.logging.Logger;

public class LoggerProvider {
    private static Function<String, Logger> provider;

    public static void setProvider(final Function<String, Logger> provider) {
        LoggerProvider.provider = provider;
    }

    public static Logger getLogger(final String name) {
        return provider.apply(name);
    }
}
