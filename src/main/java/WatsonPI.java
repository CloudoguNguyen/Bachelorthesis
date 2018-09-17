import com.google.gson.stream.JsonReader;
import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Content;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ProfileOptions;
import com.ibm.watson.developer_cloud.util.GsonSingleton;

import java.io.*;

public class WatsonPI implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    PersonalityInsights pi;
    Profile profile;

    public WatsonPI() {

        username = "7bdf7ef4-8c83-4d92-87e6-a03be90b4caf";
        password = "OhjpzkGkdkNK";
        pi = new PersonalityInsights("2017-10-13",username,password);
    }

    public void setProfile(String path) {

        try {
            JsonReader jsonReader = new JsonReader(new FileReader(path));
            Content content = GsonSingleton.getGson().fromJson(jsonReader, Content.class);
            ProfileOptions profileOptions = new ProfileOptions.Builder().content(content).consumptionPreferences(true).rawScores(true).build();
            profile = pi.profile(profileOptions).execute();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.printf("Error: %s \n", e.getMessage());        }

    }

    public void saveWatson() {
        try (FileOutputStream fos = new FileOutputStream("src/main/resources/p");
             ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(this);
            System.out.println("Serialization of Object completed.");
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    public static WatsonPI loadWatson() {
        WatsonPI pi = null;
        try (FileInputStream fis = new FileInputStream("src/main/resources/p");
             ObjectInputStream ois = new ObjectInputStream(fis))
        {
            pi = (WatsonPI) ois.readObject();
            System.out.println("Deserialization of Object is completed.");
        }
        catch (IOException | ClassNotFoundException exception)
        {
            exception.printStackTrace();
        }

        return pi;
    }

    public Profile getProfile() {
        return profile;
    }
}
