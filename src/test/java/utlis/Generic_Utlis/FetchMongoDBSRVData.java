package utlis.Generic_Utlis;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import com.mongodb.client.FindIterable;

public class FetchMongoDBSRVData {

    public static void main(String[] args) {
        
       
        // Connect to the MongoDB+SRV database
        MongoClientURI uri = new MongoClientURI("mongodb+srv://nimbly-admin:6HC640DohEFcP9vB@nimbly-cluster-back.pzwft.gcp.mongodb.net/nimbly?authSource=admin&replicaSet=atlas-je91dd-shard-0&readPreference=primary&ssl=true");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("nimbly");
        MongoCollection<Document> collection = database.getCollection("users");
        
//        // Retrieve data from the MongoDB+SRV collection
//        FindIterable<Document> cursor = collection.find();
//        for (Document document : cursor) {
//            String organizationID = document.getString("organizationID");
//            String name = document.getString("name");
//            String data = "organizationID: " + organizationID + ", name: " + name;            
//            System.out.println(data);
//        }
        
// 		Execute a complex query on the MongoDB+SRV collection
      Document query = new Document("$and", new ArrayList<>(
          Arrays.asList(
              new Document("organizationID","sustainnovation"),
              new Document("status", "active"),
              new Document("userID", "b8kzLeGHhNP27BoJvXf1pyOdAVK2")
                  )
          )
      );
      
      // Define the JSON writer settings
      JsonWriterSettings writerSettings = JsonWriterSettings.builder()
              .indent(true)
              .build();
      
      // Execute the query and retrieve all documents
      FindIterable<Document> cursor = collection.find(query);
      List<Document> documents = new ArrayList<>();
      cursor.into(documents);
      
      // Print the documents
      for (Document document : documents) {
          System.out.println(document.toJson(writerSettings));
      }
      
//   // Define the update query
//      Document update = new Document("$set", new Document("location", "Canada"));
//
//      // Update the documents in the collection
//      collection.updateMany(query, update);

        mongoClient.close();
    }
    }
