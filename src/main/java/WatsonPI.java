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


    public WatsonPI() {

        username = "7bdf7ef4-8c83-4d92-87e6-a03be90b4caf";
        password = "OhjpzkGkdkNK";
        pi = new PersonalityInsights("2017-10-13",username,password);
    }

    public Profile getProfile(String pathToTexts) {

        try {
            JsonReader jsonReader = new JsonReader(new FileReader(pathToTexts));
            Content content = GsonSingleton.getGson().fromJson(jsonReader, Content.class);
            ProfileOptions profileOptions = new ProfileOptions.Builder().content(content).consumptionPreferences(true).rawScores(true).build();
            Profile profile = pi.profile(profileOptions).execute();

            return profile;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.printf("Error: %s \n", e.getMessage());        }
        return null;
    }

    public static void saveProfile(Profile p) {
        try (FileOutputStream fos = new FileOutputStream("src/main/resources/p");
             ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(p);
            System.out.println("Serialization of Object completed.");
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    public static Profile loadProfile() {
        Profile p = null;
        try (FileInputStream fis = new FileInputStream("src/main/resources/p");
             ObjectInputStream ois = new ObjectInputStream(fis))
        {
            p = (Profile) ois.readObject();
            System.out.println("Deserialization of Object is completed.");
        }
        catch (IOException | ClassNotFoundException exception)
        {
            exception.printStackTrace();
        }

        return p;
    }

}
