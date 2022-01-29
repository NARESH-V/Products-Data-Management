package naresh.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Remove {

	static Scanner sc = new Scanner(System.in);
	
	@SuppressWarnings("deprecation")
	static void removeBrand( )
	{
		String brand = Getter.getBrandName();
		String product_type = Getter.getProductType();
		//REMOVING BRAND
		BasicDBObject query = new BasicDBObject();
		BasicDBObject update = new BasicDBObject();
		List <BasicDBObject> list = new ArrayList<BasicDBObject>();
		
		query.put("_id",product_type);
		update.put("$pull",new BasicDBObject().append("details.brand",new BasicDBObject().append("name",brand)));

		// updating data into collection
		Connection.userCollection.update(query, update);
		
		//GETTING SUM OF UNITS
		update= new BasicDBObject();
		query = new BasicDBObject();
		
		query.put("$match", new BasicDBObject().append("_id", product_type));
		update.put("$project", new BasicDBObject().append("total", new BasicDBObject().append("$sum","$details.brand.units")));
		
		list.add(query);
		list.add(update);
		AggregationOutput result = Connection.userCollection.aggregate(list);
		
		int total_units;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonobj = new JSONObject();
		
	    for (DBObject res : result.results()) 
	    {
	    	jsonobj.put("totalcount", res.get("total"));
	        jsonArray.add(jsonobj);
	    }
	    total_units =(int) jsonobj.get("totalcount");
	     
	    //UPDATE TOTAL UNITS
	    query = new BasicDBObject();
		update = new BasicDBObject();
		
		query.put("_id",product_type);
		update.put("$set", new BasicDBObject().append("details.total_units_available",total_units ).append("last_updated",java.time.LocalDateTime.now() ));
		
		Connection.userCollection.update(query, update);
	}
	
	
	static void removeColor()
	{
		String product_type = Getter.getProductType();
		BasicDBObject query = new BasicDBObject();
		BasicDBObject update = new BasicDBObject();
		List <String> color = new ArrayList<String>();
		
		color = Getter.getColor();
		query.put("_id",product_type);
		
		update.put("$pull",new BasicDBObject().append("details.color",new BasicDBObject().append("$in",color)));
		//update time
		update.put("$set", new BasicDBObject().append("last_updated",java.time.LocalDateTime.now() ));

		// updating data into collection
		Connection.userCollection.update(query, update);
	}
	
	
	static void removeItem()
	{
		String product_type = Getter.getProductType();
		BasicDBObject query = new BasicDBObject();
		query.put("_id",product_type);
		Connection.userCollection.remove(query);
	}
}
