package houses_api_project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

public class HousesTestSuite {
	@Test
	public void PropertiesPositiveTests()
	{
		RestAssured.baseURI= "http://localhost:3000";
		//Base URI of the json server
		
		String getPropertyType = given().log().all().queryParam("price_gte", "200000").queryParam("price_lte", "900000").queryParam("city", "Austin")
				.when().get("houses")
				.then().assertThat().extract().response().asString();
		// Populating a String "getPropertyType" with objects that fulfill the needed parameters
		
        try {
        	
            JSONArray propertyTypeArray = new JSONArray(getPropertyType);
            
            for (int i = 0; i < propertyTypeArray.length(); i++)
            {
                JSONObject propertyTypeObjectForParsing = propertyTypeArray.getJSONObject(i);
                String propertyCityName = propertyTypeObjectForParsing.getString("city");
                assertEquals("Austin", propertyCityName);
                int propertyPriceValue = propertyTypeObjectForParsing.getInt("price");
                assertTrue(200000 <= propertyPriceValue && propertyPriceValue <= 900000);
            }
        }
        catch (Exception e) {
        	System.out.println("Property price is out of range");
        }
	}
}
