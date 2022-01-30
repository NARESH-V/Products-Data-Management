# Product Data Management

## Problem Statement
To build a java application, which could moderate the details of products in a showroom/warehouse.

## Summary
The application connects with MongoDB and uses user inputs to perform various functions.

### Class : Print

|Function|Description|
|----|----|
|printCollection()| To print the entire collection|
|printItem()| To print specific products details|

### Class : Add

|Function|Description|
|----|----|
|addItem()| To add a new item|
|addColor()| To add color to the specific product in a specific brand|
|addBrand()| To add brand to the specific product|

### Class : Update

|Function|Description|
|----|----|
|updatePrice()| To update price of the product in a specific brand|
|removeColor()| To remove color of the product in a specific brand|
|removeBrand()| To remove brand of the product in a specific brand|
|removeItem()| To remove a product|

### Class : Query

|Function|Description|
|----|----|
|totalProducts()| To get total available products|
|getBrands()| To get all available brands in a specific products|
|searchBrand()| To get specific available brand details of the products|
|unitsInBrand()| To get total available units in a specific products|
|recentlyUpdated()| To get 10 recently updated products|
|getProductByColor()| To get all available products with specific color|
|searchByUnits()| To get all available products with more than specified units|
