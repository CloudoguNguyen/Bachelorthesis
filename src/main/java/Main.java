import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {
    public static void main(String[] args) {

        try {
            Hangout h = new Hangout();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        /*Doens't work because profile isn't serializable
        Profile p = wpi.getProfile("src/main/resources/profile.json");
        wpi.saveWatson();
        WatsonPI wpi2 = WatsonPI.loadWatson();
        */
    }
}


