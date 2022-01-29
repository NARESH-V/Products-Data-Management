package naresh.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.BasicDBObject;

public class Add {
	
	static Scanner sc = new Scanner(System.in);
	
	
	static void addItem()
	{
		BasicDBObject query = new BasicDBObject();
		String product;
		Document brand ;
		int available_units=0;
		int option;
		List<String> color = new ArrayList<String>(); 
		List<Document> brands = new ArrayList<Document>(); 
		
		
		product = Getter.getProductType();
		
		query.put("_id", product);
		query.put("product_type", product);
		
		while(true)
		{
			brand = Getter.get_brand();
			brands.add(brand);
			available_units += brand.getInteger("units");
			
			System.out.print("\n\nWant to add more brands? \n1)Yes \n2)No \n");
			option= sc.nextInt();
			if(option == 2) break;
		}
		
		color = Getter.getColor();
		Document document = new Document("total_units_available", available_units) 
							.append("color", color);
	
		document.append("brand", brands);
		
		query.put("details",document);
		query.put("last_updated", java.time.LocalDateTime.now());
		
		// inserting data into collection
		Connection.userCollection.insert(query);
	}
	
	
	static void addColor()
	{
		String product_type = Getter.getProductType();
		BasicDBObject query = new BasicDBObject();
		BasicDBObject update = new BasicDBObject();
		List<String> color = new ArrayList<String>();
		
		color = Getter.getColor();
		query.put("_id", product_type);

		//update colors list
		update.put("$push", new BasicDBObject().append("details.color",new BasicDBObject().append("$each", color)));
		
		//update time
		update.put("$set", new BasicDBObject().append("last_updated",java.time.LocalDateTime.now() ));

		// updating data into collection
		Connection.userCollection.update(query, update);
	}
	
	
	static void addBrand()
	{
		String product_type = Getter.getProductType();
		BasicDBObject query = new BasicDBObject();
		BasicDBObject update = new BasicDBObject();
		
		query.put("_id", product_type);

		Document brand = Getter.get_brand();	
		//update brand list
		update.put("$addToSet", new BasicDBObject().append("details.brand", brand));
		update.put("$inc",new BasicDBObject().append("details.total_units_available",(int)brand.get("units")));
		
		//update time
		update.put("$set", new BasicDBObject().append("last_updated",java.time.LocalDateTime.now() ));

		// updating data into collection
		Connection.userCollection.update(query, update);
	}
	

}
