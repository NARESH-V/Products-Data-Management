package naresh.Query;

import java.util.Iterator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Print {

	static void printCollection()
	{
		System.out.println("Print the documents.");

        Iterator<DBObject> cursor =  Connection.userCollection.find().iterator();
        try {
        	while (cursor.hasNext()) {
                System.out.println(((BasicDBObject) cursor.next()).toJson());
            }

        }catch(Exception e) { 
			e.printStackTrace(); 
		} 
	}
	
	
	static void printItem()
	{
		String product_type = Getter.getProductType();
		BasicDBObject query = new BasicDBObject();
		
		query.put("_id",product_type);
		Iterator<DBObject> cursor = Connection.userCollection.find(query).iterator();
		
        try {
        	while (cursor.hasNext()) {
                System.out.println(((BasicDBObject) cursor.next()).toJson());
            }

        }catch(Exception e) { 
			e.printStackTrace(); 
		} 
	}
}
