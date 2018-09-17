import com.fasterxml.jackson.core.JsonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.plus.PlusScopes;
import com.google.api.services.plus.Plus;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class Hangout {

    public Hangout() throws GeneralSecurityException, IOException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream("src/main/resources/intelliJ.json"))
                .createScoped(Collections.singleton(PlusScopes.PLUS_ME));

        Plus plus = new Plus.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("test").build();

        System.out.println(credential.getServiceAccountPrivateKeyId());


    }

}
