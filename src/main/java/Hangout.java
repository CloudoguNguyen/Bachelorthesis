import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.chat.v1.HangoutsChat;
import com.google.api.services.plus.PlusScopes;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class Hangout {

    private HttpTransport httpTransport;
    private JacksonFactory jsonFactory;
    HangoutsChat chat;

    public Hangout() throws GeneralSecurityException, IOException {
         httpTransport = GoogleNetHttpTransport.newTrustedTransport();
         jsonFactory = JacksonFactory.getDefaultInstance();

        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream("src/main/resources/intelliJ.json"))
                .createScoped(Collections.singleton(PlusScopes.PLUS_ME));

        /*Plus plus = new Plus.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("test").build();*/

        chat = new HangoutsChat.Builder(httpTransport,jsonFactory,null).build();

    }

}
