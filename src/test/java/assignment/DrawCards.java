package assignment;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class DrawCards extends BaseClass {
    public static final String CARDS_NODE = "cards";


    @BeforeMethod
    public void setUpTest(){
        createDeckApiResponse = createDeckCall();
        deck_id = helper.pickDeckId(createDeckCall());
    }

    public Response drawCard(String deckID, int count) {
        String endPointDrawCard = "/deck/" + deckID + "/draw/?count=" + count;
        RequestSpecification request = RestAssured.given();
        Response response = request.get(endPointDrawCard);
        return response;
    }


    @Test
    public void pickCards(){
        Response response = drawCard(deck_id,2);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SUCCESS_CODE, "Status Code Failed!");


        JsonPath jp = response.jsonPath();
        ArrayList<HashMap<String,Object>> cards = jp.get(CARDS_NODE);
        HashMap<String, Object> theFirstCard = cards.get(0);
        HashMap<String, Object> theSecondCard = cards.get(1);
        System.out.println("First Card No: " + theFirstCard.get("code").toString().substring(0,1));
        System.out.println("First Card Type: " + theFirstCard.get("suit"));
        System.out.println("Second Card No: " + theSecondCard.get("code").toString().substring(0,1));
        System.out.println("Second Card Type: " + theSecondCard.get("suit"));
    }



}
