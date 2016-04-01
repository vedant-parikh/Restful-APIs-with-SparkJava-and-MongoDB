package com.sparkjava.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DBHelper {
	private final MongoClient mongoClient = new MongoClient("localhost", 27017);
	private final MongoDatabase database = mongoClient.getDatabase("egen-java-challenge");
	
	public MongoDatabase getDatabase() {
		return database;
	}
}
