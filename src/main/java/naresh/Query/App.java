package naresh.Query;

import java.util.*;
import java.util.Map.Entry;

import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.Document;
import java.time.*;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class App 
{
	
	static Scanner sc = new Scanner(System.in);

	static void printOptions()
	{
		System.out.println("\n\t 1)  Print all the products");
		System.out.println("\n\t 2)  Add new product");
		System.out.println("\n\t 3)  Add color to product");
		System.out.println("\n\t 4)  Add brand to product");
		System.out.println("\n\t 5)  Update price of a brand in product");
		System.out.println("\n\t 6)  Update units of a brand in product");
		System.out.println("\n\t 7)  Remove color from a product");
		System.out.println("\n\t 8)  Remove brand from a product");
		System.out.println("\n\t 9)  Remove product");
		System.out.println("\n\t 10) Print the product details");
		System.out.println("\n\t 11) Total No.Of products in a brand");
		System.out.println("\n\t 12) Get all brands in a product");
		System.out.println("\n\t 13) Search brands");
		System.out.println("\n\t 14) Get units in each brand of a product");
		System.out.println("\n\t 15) Recently updated products (last 1 hr)");
		System.out.println("\n\t 16) Get products by color");
		System.out.println("\n\t 17) Get products by units available");
		System.out.println("\n\t  0) Exit");
	}
	
    public static void main( String[] args )
    {
    	try{
    		
    		Connection.connect();
    		printOptions();
    		int option;
    		
    		while(true) 
    		{
    			System.out.println("\n\nEnter your input : ");
    			option = sc.nextInt();
    			switch(option)
    			{
    				case 1: Print.printCollection(); break;
    				case 2: Add.addItem(); break;
    				case 3: Add.addColor(); break;
    				case 4: Add.addBrand(); break;
    				case 5: update.updatePrice(); break;
    				case 6: update.updateUnits(); break;
    				case 7: Remove.removeColor(); break;
    				case 8: Remove.removeBrand(); break;
    				case 9: Remove.removeItem(); break;
    				case 10: Print.printItem(); break;
    				case 11: Query.totalProducts(); break;
    				case 12: Query.getBrands(); break;
    				case 13: Query.searchBrand(); break;
    				case 14: Query.unitsInBrand(); break;
    				case 15: Query.recentlyUpdated(); break;
    				case 16: Query.getProductByColor(); break;
    				case 17: Query.searchByUnits();; break;
    				case 0 : return;
    				default : System.out.println("invalid input");
    			}
    		}
    		
    	}
    	catch(Exception e) { 
			e.printStackTrace(); 
		} 
      
    }
}

