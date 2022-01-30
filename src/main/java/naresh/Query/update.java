/**
 *  @author NARESH V
 *  
 */

package naresh.Query;

import java.util.Scanner;

import com.mongodb.BasicDBObject;

public class update {
	
	static App main = new App();
	static Scanner sc = new Scanner(System.in);
	
	public static void updatePrice()
	{
		String brand = Getter.getBrandName();
		String product_type = Getter.getProductType();
		BasicDBObject query = new BasicDBObject();
		BasicDBObject update = new BasicDBObject();
		int newPrice;
		
		System.out.println("\nenter the new price of "+product_type+"-"+ brand+" :");
		newPrice = sc.nextInt();
		
		query.put("_id",product_type);
		query.put("details.brand.name",brand);
		update.put("$set", new BasicDBObject().append("details.brand.$.price",newPrice)
											  .append("last_updated",java.time.LocalDateTime.now()));
		Connection.userCollection.update(query, update);
	}
	
	
	public static void updateUnits()
	{
		String brand = Getter.getBrandName();
		String product_type = Getter.getProductType();
		BasicDBObject query = new BasicDBObject();
		BasicDBObject update = new BasicDBObject();
		int units;
		
		System.out.println("\nenter the no.of units to be added "+product_type+"-"+ brand+" :");
		units = sc.nextInt();
		
		query.put("_id",product_type);
		query.put("details.brand.name",brand);

		update.put("$inc",new BasicDBObject().append("details.total_units_available",units).append("details.brand.$.units",units));	
		update.put("$set", new BasicDBObject().append("last_updated",java.time.LocalDateTime.now() ));
		
		//updating data into collection
		Connection.userCollection.update(query, update);
	}

}
