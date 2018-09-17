public class Main {
    public static void main(String[] args) {

        WatsonPI wpi = new WatsonPI();

        wpi.setProfile("src/main/resources/profile.json");

        /*Doens't work because profile isn't serializable

        wpi.saveWatson();
        WatsonPI wpi2 = WatsonPI.loadWatson();
        */
    }
}


