import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Dialogflow.authImplicit();

        List<String> texts = new ArrayList<>();

        texts.add("Open an account");
        texts.add("whats your name?");


        try {
            Dialogflow.detectIntentTexts("secondbanking",texts,"abc","en");
        } catch (Exception e) {
            e.printStackTrace();
        }



        /*Doens't work because profile isn't serializable
        Profile p = wpi.getProfile("src/main/resources/profile.json");
        wpi.saveWatson();
        WatsonPI wpi2 = WatsonPI.loadWatson();
        */
    }
}


