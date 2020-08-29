package com.dxc.app;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DemoApp_032 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		char rep;
		System.out.println("Welcome");
		MongoClient mongoClient = new MongoClient("localhost" , 27017);
		MongoDatabase mongoDataBase = mongoClient.getDatabase("dxc");
		MongoCollection<Document> collection = mongoDataBase.getCollection("gadget");
		Document document = new Document();
		do {
			System.out.println("Enter field name : ");
			String key = scanner.next();
			System.out.println("Enter value to "+key+" : ");
			String value = scanner.next();
			document.append(key, value);
			System.out.println();
			System.out.println("Do you have any more fields?");
			rep = scanner.next().charAt(0);
		}while (rep!='n');

		System.out.println("End of the program");
		mongoClient.close();
	}

}
