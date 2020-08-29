package com.dxc.app;

import java.util.Set;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class DemoApp_01 {
	
	public static void main(String[] args) {
		//Client -> Database -> Collection -> Document -> key & value
		MongoClient mongoClient = new MongoClient("localhost" , 27017);
		MongoDatabase mongoDataBase = mongoClient.getDatabase("dxc");
		MongoCollection<Document> collection = mongoDataBase.getCollection("gadget");
		//TO findAll
		//MongoCursor<Document> mongoCursor = collection.find().iterator();
		//To find specific object 
		//MongoCursor<Document> mongoCursor = collection
				                           //.find(new Document().append("name", "phone"))
				                           //.iterator();
		//Further filteration
		MongoCursor<Document> mongoCursor = collection
                .find(new Document().append("name", "phone").append("brand", "samsung"))
                .iterator();
		//mongoCursor.forEachRemaining((doc)->display(doc));
		mongoCursor.forEachRemaining( DemoApp_01::display );
		mongoClient.close();
	}
	
	public static void display(Document document) {
		Set<String> keys = document.keySet();
		for(String key : keys) {
			Object value = document.get(key);
			System.out.println(key+" : "+value);
		}
		System.out.println("-----------------------------");
	}

}
