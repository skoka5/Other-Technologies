package com.dxc.app;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class DemoApp_04 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient("localhost" , 27017);
		MongoDatabase mongoDataBase = mongoClient.getDatabase("dxc");
		MongoCollection<Document> mongocollection = mongoDataBase.getCollection("gadget");
		
		//mongoCollection.updateOne(srch_criteria,new data)
		
		//mongocollection.updateOne(Filters.eq("name", "computer"), Updates.set("price", "100"));
		//further specific
		mongocollection.updateOne(
				Filters.and(
						Filters.eq("name", "comp"),
						Filters.eq("brand", "hp")
						),Updates.set("peice", 100)
				        );
		
		System.out.println("Document Updated");
		
		mongoClient.close();
		
	}

}
