package net.xelor.configuration;

public class SettingsModule {
    private final AIMode mode;

    public SettingsModule(AIMode mode) {
        this.mode = mode;
    }

    public AIMode getMode() {
        return mode;
    }
}

enum AIMode {
    ACTIVE,
    PASSIVE
}
