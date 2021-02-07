package houses_api_project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class HousesTestSuite {

	@BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:3000";
    }
	
	@Test
	public void housesCheckCityAndPriceRange() {
		
		String housesRaw = given()
	               .queryParam("price_gte", "200000")
	               .queryParam("price_lte", "900000")
	               .queryParam("city", "Austin")
	               .when().get("houses")
	               .then().assertThat().statusCode(200).extract().response().asString();
		
		
		JSONArray houses = rawStringToJsonArray(housesRaw);

		
		
		for(int i = 0; i < houses.length(); i++) {
			JSONObject house = houses.getJSONObject(i);
			
			assertEquals("Austin", house.get("city"));
			assertTrue(200000 <=  house.getInt("price") &&  house.getInt("price") <= 900000);
		}
	}
	
	
	@Test
	public void housesCheckIfAllKeysArePresent() {
		
		String housesRaw = given()
	               .queryParam("price_gte", "200000")
	               .queryParam("price_lte", "900000")
	               .queryParam("city", "Austin")
	               .when().get("houses")
	               .then().assertThat().statusCode(200).extract().response().asString();
		
		
		JSONArray houses = rawStringToJsonArray(housesRaw);

	
		for(int i = 0; i < houses.length(); i++) {
			JSONObject house = houses.getJSONObject(i);
			house.get("id");
			house.get("mls_id");
			house.get("mls_listing_id");
			house.get("property_type");
			house.get("formatted_address");
			house.get("address");
			house.get("zip");
			house.get("city");
			house.get("state");
			house.get("location");
			house.get("bedrooms");
			house.get("bathrooms");
			house.get("list_date");
			house.get("mls_update_date");
			house.get("price_display");
			house.get("price");
			house.get("square_feet");
			
			
		}
	}
	
	
	@Test
	public void housesCheckIfArrayIsEmptyWhenNoHousesMatchTheParameters() {
		
		String housesRaw = given()
	               .queryParam("price_gte", "200000")
	               .queryParam("price_lte", "210000")
	               .queryParam("city", "Belgrade")
	               .when().get("houses")
	               .then().assertThat().statusCode(200).extract().response().asString();
		
		
		JSONArray houses = rawStringToJsonArray(housesRaw);
		assertEquals(houses.length(), 0);
	
	}
	
	
	
	private static JSONArray rawStringToJsonArray(String input) {
        JSONArray result = null;
        try {
            result = new JSONArray(input);
        }
        catch (JSONException e) {
            System.err.println(e);
        }

        return  result;
    }
	
	
	
}
