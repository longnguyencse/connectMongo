package dev.at.mikorn.com;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

/**
 * Author Mikorn vietnam
 * Created on 24-Sep-18.
 */

public class App {
    protected MongoClient mongoClient;

    public static void main(String[] args) {
//        MongoClient mongoClient = new MongoClient("localhost", 27017);
//        MongoClient mongoClient = new MongoClient("localhost", 27017);
        // mongodb://datanow:test@192.168.1.161:27017/tasks
        App  app = new App();
        MongoCredential credential = MongoCredential.createCredential("datanow",
                "proto_datanow_dev","test".toCharArray());

//        app.mongoClient = new MongoClient(
//                new MongoClientURI("mongodb://datanow:test@192.168.1.161:27017"), Arrays.asList(credential));
        ServerAddress addr = new ServerAddress("192.168.1.161", 27017);
        app.mongoClient = new MongoClient(addr, Arrays.asList(credential));

//        app.mongoClient = new MongoClient(
//                new MongoClientURI("mongodb://datanow:test@192.168.1.161:27017/proto_datanow_dev"));

        MongoDatabase db = app.mongoClient.getDatabase("proto_datanow_dev");
//        boolean auth = db.("username", "pwd".toCharArray());

        MongoCollection<Document> songs = db.getCollection("tasks");
        System.out.println(songs.toString());

        for (String name : db.listCollectionNames()) {

            System.out.println(name);
        }
       app.mongoClient.close();
    }
}
