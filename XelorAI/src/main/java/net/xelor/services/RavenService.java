package net.xelor.services;

import net.ravendb.client.documents.DocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class RavenService extends DataService {


    public RavenService() {
        super("RavenDB");
    }

    @Override
    public void init(String... credits) throws KeyStoreException {
        KeyStore clientStore = KeyStore.getInstance("PKCS12");
        try {
            clientStore.load((KeyStore.LoadStoreParameter) new FileInputStream("C:\\Users\\final\\AppData\\Local\\Temp\\Rar$DIa28564.6936\\free.charles.client.certificate.pfx"));
            try (DocumentStore store = new DocumentStore()) {
                store.setCertificate(clientStore);
                store.setDatabase("Xelor");
                store.setUrls(new String[]{credits[1]});
            }

        } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {

    }
}
