package Utility;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

public class Helper {


    public String pickDeckId(Response response) {
        String responseBody = response.getBody().asString();
        if ("".equals(responseBody)) {
            Assert.fail();
        }
        JsonPath jp = response.jsonPath();
//        String[] array = responseBody.replaceAll("[{}:,\"]","").split(" ");
//        String deckID = array[3];
        String deckID = jp.get("deck_id");
        return deckID;
    }


}
