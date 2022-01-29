package naresh.Query;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Query {

	
	static void totalProducts()
	{
		String brand = Getter.getBrandName();
		BasicDBObject group = new BasicDBObject();
		BasicDBObject project = new BasicDBObject();
		BasicDBObject match = new BasicDBObject();
		BasicDBObject unwind = new BasicDBObject();
		List <BasicDBObject> list = new ArrayList<BasicDBObject>();
		
		unwind.put("$unwind","$details.brand");
		match.put("$match",new BasicDBObject().append("details.brand.name", brand));
		project.put("$project",new BasicDBObject().append("details.brand.name",brand). append("total", new BasicDBObject()
											  .append("$sum", "$details.brand.units")));
		group.put("$group",new BasicDBObject().append("_id",0).append("total", new BasicDBObject().append("$sum","$total") ));
		
		list.add(unwind);
		list.add(match);
		list.add(project);
		list.add(group);
		AggregationOutput result = Connection.userCollection.aggregate(list);

		System.out.println(result.results());
	}
	
	
	static void searchBrand()
	{
		String brand = Getter.getBrandName();
		BasicDBObject match = new BasicDBObject();
		BasicDBObject unwind = new BasicDBObject();
		BasicDBObject match2 = new BasicDBObject();
		BasicDBObject group = new BasicDBObject();
		List <BasicDBObject> list = new ArrayList<BasicDBObject>();
	
		match.put("$match", new BasicDBObject().append("details.brand.name", new BasicDBObject().append("$regex",brand).append("$options", "i")));
		unwind.put("$unwind","$details.brand");
		match2.put("$match", new BasicDBObject().append("details.brand.name", new BasicDBObject().append("$regex",brand).append("$options", "i")));
		group.put("$group",new BasicDBObject().append("_id", "brands").append("Search_result", new BasicDBObject().append("$addToSet", "$details.brand.name")));
		
		list.add(match);
		list.add(unwind);
		list.add(match2);
		list.add(group);
		AggregationOutput result = Connection.userCollection.aggregate(list);
		
		System.out.println(result.results());
	}
	
	
	static void getBrands()
	{
		String product_type = Getter.getProductType();
		BasicDBObject group = new BasicDBObject();
		BasicDBObject unwind = new BasicDBObject();
		BasicDBObject query = new BasicDBObject();
		List <BasicDBObject> list = new ArrayList<BasicDBObject>();
		
		unwind.put("$unwind","$details.brand");
		query.put("$match",new BasicDBObject().append("_id",product_type));
		group.put("$group",new BasicDBObject().append("_id", "brands").append("Search_result", new BasicDBObject().append("$addToSet", "$details.brand.name")));
		
		list.add(query);
		list.add(unwind);
		list.add(group);
		
		AggregationOutput result = Connection.userCollection.aggregate(list);
		
		System.out.println(result.results());	
	}
	
	
	static void unitsInBrand()
	{
		String product_type = Getter.getProductType();
		BasicDBObject project = new BasicDBObject();
		BasicDBObject match = new BasicDBObject();
		List <BasicDBObject> list = new ArrayList<BasicDBObject>();
		
		match.put("$match",new BasicDBObject().append("_id",product_type));
		project.put("$project", new BasicDBObject().append("brand", "$details.brand.name").append("units", "$details.brand.units"));
		
		list.add(match);
		list.add(project);
		
		AggregationOutput result = Connection.userCollection.aggregate(list);
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonobj = new JSONObject();
		List<String> brands = new LinkedList<String>();
		List<String> units = new LinkedList<String>();
		Map<String,Integer> unitsInBrand = new HashMap<String,Integer>();
		
	    for (DBObject res : result.results()) 
	    {
	    	jsonobj.put("brands", res.get("brand"));
	    	jsonobj.put("units", res.get("units"));
	        jsonArray.add(jsonobj);
	    }
	    brands =(List<String>) jsonobj.get("brands");
	    units =(List<String>) jsonobj.get("units");
	    Iterator b = brands.iterator();
	    Iterator u = units.iterator();
	    
	    while(b.hasNext())
	    	unitsInBrand.put((String)b.next(),(int)u.next());
	    
	   // System.out.println(unitsInBrand);
	    for(Entry<String, Integer> e : unitsInBrand.entrySet() )
	    	System.out.println(e.getKey()+" : "+e.getValue());
	    
	}
	
	
	static void recentlyUpdated()
	{
		BasicDBObject query = new BasicDBObject();
		
		query.put("last_updated",new BasicDBObject().append("$lt",java.time.LocalDateTime.now()).append("$gt", LocalDateTime.now().minusHours(1)));
		
		DBCursor cursor = Connection.userCollection.find(query); 
		Iterator<DBObject> iterator = cursor.iterator();
		
		while (iterator.hasNext()) 
		{
			System.out.println(iterator.next());
		}
	}
	
	static void getProductByColor()
	{
		List <String> color = Getter.getColor();
		BasicDBObject query = new BasicDBObject();
		
		query.put("details.color",new BasicDBObject().append("$in", color));
		
		DBCursor result = Connection.userCollection.find(query);
		Iterator<DBObject> iterator = result.iterator();
		
		while (iterator.hasNext()) 
		{
			System.out.println(iterator.next());
		}
	}
	
	static void searchByUnits()
	{
		BasicDBObject query = new BasicDBObject();
		
		query.put("details.total_units_available",new BasicDBObject().append("$gt", Getter.getUnits()));
		
		DBCursor cursor = Connection.userCollection.find(query); 
		Iterator<DBObject> iterator = cursor.iterator();
		
		while (iterator.hasNext()) 
		{
			System.out.println(iterator.next());
		}
	}
}
