import com.google.api.gax.paging.Page;
import com.google.auth.appengine.AppEngineCredentials;
import com.google.auth.oauth2.ComputeEngineCredentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.v2.*;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Dialogflow {

    /**
     * Returns the result of detect intent with texts as inputs.
     *
     * Using the same `session_id` between requests allows continuation of the conversation.
     * @param projectId Project/Agent Id.
     * @param texts The text intents to be detected based on what a user says.
     * @param sessionId Identifier of the DetectIntent session.
     * @param languageCode Language code of the query.
     */
    public static void detectIntentTexts(String projectId, List<String> texts, String sessionId,
                                         String languageCode) throws Exception {
        // Instantiates a client
        try (SessionsClient sessionsClient = SessionsClient.create()) {

            // Set the session name using the sessionId (UUID) and projectID (my-project-id)
            SessionName session = SessionName.of(projectId, sessionId);

            System.out.println("Session Path: " + session.toString());

            // Detect intents for each text input
            for (String text : texts) {
                // Set the text (hello) and language code (en-US) for the query
                TextInput.Builder textInput = TextInput.newBuilder().setText(text).setLanguageCode(languageCode);

                // Build the query with the TextInput
                QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

                // Performs the detect intent request
                DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);

                // Display the query result
                QueryResult queryResult = response.getQueryResult();



                System.out.println("====================");
                System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
                System.out.format("Detected Intent: %s (confidence: %f)\n",
                        queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
                System.out.format("Fulfillment Text: '%s'\n", queryResult.getFulfillmentText());
            }
        }
    }

    static void authExplicit(String jsonPath) throws IOException {
        // You can specify a credential file by providing a path to GoogleCredentials.
        // Otherwise credentials are read from the GOOGLE_APPLICATION_CREDENTIALS environment variable.
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        System.out.println("Buckets:");
        Page<Bucket> buckets = storage.list();
        for (Bucket bucket : buckets.iterateAll()) {
            System.out.println(bucket.toString());
        }


    }

    static void authImplicit() {
        // If you don't specify credentials when constructing the client, the client library will
        // look for credentials via the environment variable GOOGLE_APPLICATION_CREDENTIALS.
        Storage storage = StorageOptions.getDefaultInstance().getService();

        System.out.println("Buckets:");
        Page<Bucket> buckets = storage.list();
        for (Bucket bucket : buckets.iterateAll()) {
            System.out.println(bucket.toString());
        }
    }

    static void authAppEngineStandard() throws IOException {
        // Explicitly request service account credentials from the app engine standard instance.
        GoogleCredentials credentials = AppEngineCredentials.getApplicationDefault();
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        System.out.println("Buckets:");
        Page<Bucket> buckets = storage.list();
        for (Bucket bucket : buckets.iterateAll()) {
            System.out.println(bucket.toString());
        }
    }
}
