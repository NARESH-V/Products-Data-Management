/**
 *  @author NARESH V
 *  
 */

package naresh.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Connection {

	static MongoClient mongoClient;
	static DB db;
	static BasicDBObject bO;
	public static DBCollection userCollection;
	
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
		userCollection = db.getCollection("Appliances");  
		//userCollection.createIndexOperation(new Document("field", 1), new IndexOptions().unique(true));
		//userCollection.createIndex((DBObject) Indexes.ascending("product_type"), (DBObject) new IndexOptions().unique(true));
	}
	
	
	static void getCollection(String s)
	{
		 userCollection = db.getCollection(s);
		 bO = new BasicDBObject();
	}
	
	static void connect()
	{
		createConnection();
		connectDB("rfpio");
		//createCollection("Appliances");
		getCollection("Appliances");
		
		
	}
}
