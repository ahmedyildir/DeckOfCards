package Utility;

import io.restassured.response.Response;
import org.testng.Assert;

public class Helper {


    public String pickDeckId(Response response) {
        String responseBody = response.getBody().asString();
        if ("".equals(responseBody)) {
            Assert.fail();
        }
        String[] array = responseBody.replaceAll("[{}:,\"]","").split(" ");
        String deckID = array[3];
        System.out.println("Created Deck ID = " + deckID);
        return deckID;
    }


}
