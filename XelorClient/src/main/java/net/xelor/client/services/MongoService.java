package net.xelor.client.services;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import net.xelor.client.structures.DataComponent;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoService extends DataService {
    private MongoClient mongoClient;

    public MongoService() {
        super("Mongo Database");
    }

    @Override
    public void init(String... credits) {
        final CodecRegistry pojoCodecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true)
                        .register(DataComponent.class)
                        .build())
        );

        final ConnectionString connectionString = new ConnectionString(credits[0]);
        final MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .applyConnectionString(connectionString)
                .applicationName("Xelor Data Service")
                .retryReads(true)
                .retryWrites(true)
                .build();

        this.mongoClient = MongoClients.create(settings);

        XelorModule.LOGGER.info(getName() + " is enabled");
    }

    @Override
    public void shutdown() {

    }
}
