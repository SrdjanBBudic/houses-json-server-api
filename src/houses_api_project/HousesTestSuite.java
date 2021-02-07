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
	public void housesCheckIfAllKeysArePresentAndThatAllKeysArePopulated() {
		
		String housesRaw = given()
	               .queryParam("price_gte", "200000")
	               .queryParam("price_lte", "900000")
	               .queryParam("city", "Austin")
	               .when().get("houses")
	               .then().assertThat().statusCode(200).extract().response().asString();
		
		
		JSONArray houses = rawStringToJsonArray(housesRaw);

	
		for(int i = 0; i < houses.length(); i++) {
			JSONObject house = houses.getJSONObject(i);
			Object houseID = house.get("id");
			assert houseID != null;
			Object houseMlsId = house.get("mls_id");
			assert houseMlsId != null;
			Object houseMlsListingId = house.get("mls_listing_id");
			assert houseMlsListingId != null;
			Object housePropertType = house.get("property_type");
			assert housePropertType != null;
			Object houseFormattedAdress = house.get("formatted_address");
			assert houseFormattedAdress != null;
			Object houseAdress = house.get("address");
			assert houseAdress != null;
			Object houseZip = house.get("zip");
			assert houseZip != null;
			Object houseCity = house.get("city");
			assert houseCity != null;
			Object houseState = house.get("state");
			assert houseState != null;
			Object houseLocation = house.get("location");
			assert houseLocation != null;
			Object houseBedrooms = house.get("bedrooms");
			assert houseBedrooms != null;
			Object houseBathrooms = house.get("bathrooms");
			assert houseBathrooms != null;
			Object houseListDate = house.get("list_date");
			assert houseListDate != null;
			Object houseMlsUpdateDate = house.get("mls_update_date");
			assert houseMlsUpdateDate != null;
			Object housePriceDisplay = house.get("price_display");
			assert housePriceDisplay != null;
			Object housePrice = house.get("price");
			assert housePrice != null;
			Object houseSquareFeet = house.get("square_feet");
			assert houseSquareFeet != null;
			
			
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
