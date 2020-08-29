package com.dxc.app;

import java.util.ArrayList;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DemoApp_03 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient("localhost" , 27017);
		MongoDatabase mongoDatabase= mongoClient.getDatabase("dxc");
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter collection name to insert : ");
		String collectionName = scanner.next();
		
		if(mongoDatabase.listCollectionNames().into(new ArrayList<String>()).contains(collectionName)) {
			MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collectionName);
			Document document = new Document()
					                .append("name", "Book")
					                .append("title", "Head First Java and OOAD")
					                .append("level", "Fresher to Advanced")
					                .append("pages", 1255);
			mongoCollection.insertOne(document);
			System.out.println("Document Inserted Successfullys");
		}
		else {
			System.out.println("Collection is not existing.firset you create a collection and then try!!");
		}
		
		mongoClient.close();
	}

}
