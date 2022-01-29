package naresh.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;

public class Getter {
	
	static Scanner sc = new Scanner(System.in);

	public static String getBrandName()
	{
		System.out.println("Enter Brand Name : ");
		String temp = sc.next();
		return temp.toLowerCase();
	}
	
	public static String getProductType()
	{
		System.out.println("Enter Product Type : ");
		String temp = sc.next();
		return temp.toUpperCase();
	}
	
	public static int getUnits()
	{
		System.out.println("Enter units : ");
		int temp = sc.nextInt();
		return temp;
	}
	
	static Document get_brand()
	{
		int price;
		int units;
		String brand_name;
	
		Document subdocument;
		
		System.out.print("\nEnter Brand name : ");
		brand_name = sc.next();
		
		System.out.print("\nEnter price 	 : ");
		price = sc.nextInt();
		
		System.out.print("\nEnter units      : ");
		units = sc.nextInt();
		 
		subdocument = new Document("name",brand_name)
					.append("price", price)
					.append("units", units);

		return subdocument;
	}
	
	
	static List<String> getColor()
	{
		List<String> color = new ArrayList<String>();
		String col;
		System.out.print("\n\nEnter colors : \t\t\t *enter '-1' to end*\n");
		while(true )
		{
			col = sc.nextLine();
			if(!col.contentEquals("-1"))
				color.add(col);
			else
				break;
		}
		return color;
	}
	
}
