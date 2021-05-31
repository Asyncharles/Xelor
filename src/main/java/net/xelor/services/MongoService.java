package net.xelor.services;

import net.xelor.logger.Logger;

public class MongoService extends DataService {

    public MongoService() {
        super("Mongo Database");
    }

    @Override
    public void init() {
        Logger.logFine(getName() + " is enabled");
    }

    @Override
    public void shutdown() {

    }
}
