package naresh.Query;

import java.util.Iterator;

import javax.swing.text.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

public class MakeConnection {
	
	static MongoClient mongoClient;
	static DB db;
	static BasicDBObject bO;
	static DBCollection userCollection;
	
	static void createConnection()
	{
		mongoClient = new MongoClient("localhost", 27017); 
	}
	
	static void connectDB(String s) {
		
		 db = mongoClient.getDB(s);
	}
	
	static void createCollection(String s)
	{
		db.createCollection(s, new BasicDBObject());
	}
	
	static void getCollection(String s)
	{
		 userCollection = db.getCollection(s);
		 bO = new BasicDBObject();
	}
	
	static void addItem()
	{
		bO.put("_id", "xyz126");
		bO.put("name", "XYZz");
		bO.put("description", "Data insertion in MongoDB");
		bO.put("friends",new String[] {"nari","ak"});

		// inserting data into collection
		userCollection.insert(bO);
	}
	
	static void updateItem(String primary_key, String primary_value  )
	{
		BasicDBObject queryObj = new BasicDBObject();
		queryObj.put(primary_key, primary_value);
		
		BasicDBObject newObj = new BasicDBObject();
		//newObj.put("name", "ABC");
		
		//for array
		newObj.put("$addToSet", new BasicDBObject().append("friends", "naresh"));
		
		newObj.put("$set", new BasicDBObject().append("name", "nari"));

		// updating data into collection
		userCollection.update(queryObj, newObj);
	}
	
	static void printCollection()
	{
		System.out.println("Print the documents.");

        Iterator<DBObject> cursor =  userCollection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(((BasicDBObject) cursor.next()).toJson());
            }

        }catch(Exception e) { 
			e.printStackTrace(); 
		} 
	}
	public static void main(String[] args) {
		try { 
			
			// code to create the connection
			createConnection();
			
			// code to connect to the database
			connectDB("Appliances");
			
			//create Collection
			//createCollection("user");
			
			// get the User collection from DB
			getCollection("products");
			

			// adding item
			//addItem();
			
			//update item
			updateItem("_id","xyz126");
			
			printCollection();
			
			
			
		} 
		catch(Exception e) { 
			e.printStackTrace(); 
		} 
	}
	
}

