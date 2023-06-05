package wolfcub.main;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Firebase {
//    public static void main(String[] args) {
//        // Initialize Firebase app
//        initializeFirebaseApp();
//
//        // Add a new document
//        String collection = "players"; // Replace with your desired collection name
//        Map<String, Object> data = new HashMap<>();
//        data.put("username", "jenny");
//        data.put("player_isActive", true);
//        data.put("role_id", getRoleReference("roles", "1"));
//
//        // Add new document
//        addDocument(collection, data);
//
//        // Update the document
//        updateDocument(collection, documentId, "player_isActive", false);
//
//        // Delete the document
//        deleteDocument(collection, documentId);
//
//        // Display the document
//        displayDocument(collection, documentId);
//    }

    public static void initializeFirebaseApp() {
        try {
            FileInputStream serviceAccount = new FileInputStream("C:/Users/Acer/Documents/SEM2-YEAR3/WIA3004/wolfcubs/wolfcubs-2023-firebase-adminsdk-dtu4e-0e7f622e92.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            System.out.println("Error initializing Firebase app: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void addDocument(String collection, Map<String, Object> data) {
        try {
            // Get Firestore instance
            Firestore firestore = FirestoreClient.getFirestore();

            // Get the document ID counter
            DocumentReference counterRef = firestore.collection("counters").document("documentCounter");
            ApiFuture<DocumentSnapshot> counterFuture = counterRef.get();
            DocumentSnapshot counterSnapshot = counterFuture.get();
            final long currentCount; // Declare as final

            if (counterSnapshot.exists()) {
                // Retrieve the current count value
                currentCount = counterSnapshot.getLong("count") + 1;
            } else {
                currentCount = 1; // Default initial value
            }

            // Set the updated count value
            counterRef.set(new HashMap<String, Object>() {{
                put("count", currentCount);
            }});

            // Create the document ID with the incremented count value
            final String documentId = String.valueOf(currentCount); // Declare as final

            // Add the document to Firestore with the generated document ID
            DocumentReference documentRef = firestore.collection(collection).document(documentId);
            ApiFuture<WriteResult> writeResultFuture = documentRef.set(data);
            ApiFutures.addCallback(writeResultFuture, new ApiFutureCallback<WriteResult>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("Error adding document: " + throwable.getMessage());
                    // Handle the error
                }

                @Override
                public void onSuccess(WriteResult writeResult) {
                    System.out.println("Document added successfully. Document ID: " + documentId);
                    // Perform further actions on success
                }
            });

            // Wait for the write operation to complete
            writeResultFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void updateDocument(String collection, String documentId, String field, Object value) {
        try {
            // Get Firestore instance
            Firestore firestore = FirestoreClient.getFirestore();

            // Get a reference to the document
            DocumentReference documentRef = firestore.collection(collection).document(documentId);

            // Update the document
            Map<String, Object> updates = new HashMap<>();
            updates.put(field, value);
            ApiFuture<WriteResult> updateFuture = documentRef.update(updates);

            ApiFutures.addCallback(updateFuture, new ApiFutureCallback<WriteResult>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("Error updating document: " + throwable.getMessage());
                    // Handle the error
                }

                @Override
                public void onSuccess(WriteResult writeResult) {
                    System.out.println("Document updated successfully. Document ID: " + documentId);
                    // Perform further actions on success
                }
            });

            // Wait for the update operation to complete
            updateFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void deleteDocument(String collection, String documentId) {
        try {
            // Get Firestore instance
            Firestore firestore = FirestoreClient.getFirestore();

            // Get a reference to the document
            DocumentReference documentRef = firestore.collection(collection).document(documentId);

            // Delete the document
            ApiFuture<WriteResult> deleteFuture = documentRef.delete();

            ApiFutures.addCallback(deleteFuture, new ApiFutureCallback<WriteResult>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("Error deleting document: " + throwable.getMessage());
                    // Handle the error
                }

                @Override
                public void onSuccess(WriteResult writeResult) {
                    System.out.println("Document deleted successfully. Document ID: " + documentId);
                    // Perform further actions on success
                }
            });

            // Wait for the delete operation to complete
            deleteFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void displayDocument(String collection, String documentId) {
        try {
            // Get Firestore instance
            Firestore firestore = FirestoreClient.getFirestore();

            // Get a reference to the document
            DocumentReference documentRef = firestore.collection(collection).document(documentId);

            // Get the document data
            ApiFuture<DocumentSnapshot> documentFuture = documentRef.get();

            ApiFutures.addCallback(documentFuture, new ApiFutureCallback<DocumentSnapshot>() {
                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("Error retrieving document: " + throwable.getMessage());
                    // Handle the error
                }

                @Override
                public void onSuccess(DocumentSnapshot document) {
                    if (document.exists()) {
                        System.out.println("Document retrieved successfully:");
                        Map<String, Object> documentData = document.getData();
                        for (Map.Entry<String, Object> entry : documentData.entrySet()) {
                            System.out.println(entry.getKey() + ": " + entry.getValue());
                        }
                    } else {
                        System.out.println("Document does not exist. Document ID: " + documentId);
                    }
                }
            });

            // Wait for the document retrieval to complete
            documentFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static DocumentReference getRoleReference(String collection, String documentId) {
        // Get the reference to the role document in the specified collection
        Firestore firestore = FirestoreClient.getFirestore();
        return firestore.collection(collection).document(documentId);
    }
}
