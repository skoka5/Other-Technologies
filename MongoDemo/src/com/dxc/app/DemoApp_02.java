package com.dxc.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class DemoApp_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean existing = false;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter collection name : ");
		String collectionName = scanner.next();
		
		MongoClient mongoClient = new MongoClient("localhost" , 27017);
		MongoDatabase mongoDatabase= mongoClient.getDatabase("dxc");
		MongoIterable<String> collectionNames = mongoDatabase.listCollectionNames();
		List<String> ListOfCollections = collectionNames.into(new ArrayList<String>());
		for(String colName : ListOfCollections) {
			if(collectionName.equals(colName)) {
				existing=true;
				break;
			}
		}
		
		if(existing) {
			System.out.println("Collection is alrady existing");
		}
		else {
			mongoDatabase.createCollection(collectionName);
			System.out.println("Collection created");
		}
		
		mongoClient.close();
		scanner.close();

	}

}
