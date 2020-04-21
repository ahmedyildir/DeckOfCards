package assignment;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class CardOfDecksAPI {
    public static final String URL = "https://deckofcardsapi.com/api";

    @Test
    public void createDeck(){
        Response response = createDeckOfCards();
        deckIdGenerator(response);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Status Code Failed!");
        Assert.assertTrue(response.getBody().asString().contains("deck_id"), "Could Not Find Deck of Cards!");
    }


    @Test
    public void pickCards(){
        Response responseID = createDeckOfCards();
        Response response = drawCard(deckIdGenerator(responseID));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Status Code Failed!");


        JsonPath jp = response.jsonPath();
        ArrayList<HashMap<String,Object>> cards = jp.get("cards");
        HashMap<String, Object> theFirstCard = cards.get(0);
        HashMap<String, Object> theSecondCard = cards.get(1);
        System.out.println("First Card No: " + theFirstCard.get("code").toString().substring(0,1));
        System.out.println("First Card Type: " + theFirstCard.get("suit"));
        System.out.println("Second Card No: " + theSecondCard.get("code").toString().substring(0,1));
        System.out.println("Second Card Type: " + theSecondCard.get("suit"));
    }





    public String deckIdGenerator(Response response){
        String responseBody = response.getBody().asString();
        String[] array = responseBody.replaceAll("[{}:,\"]","").split(" ");
        String deckID = array[3];
        System.out.println("Created Deck ID = " + deckID);
        return deckID;
    }



    public Response createDeckOfCards() {
        String endPointCreateDeck = "/deck/new";
        RestAssured.baseURI = URL;
        RequestSpecification request = RestAssured.given();
        request.header("jokers_enabled", true);
        Response response = request.get(endPointCreateDeck);
        return response;
    }

    public Response drawCard(String deckID) {
        String endPointDrawCard = "/deck/" + deckID + "/draw/?count=2";
        RestAssured.baseURI = URL;
        RequestSpecification request = RestAssured.given();
        Response response = request.get(endPointDrawCard);
        return response;
        //Instead of this we can create POJO class to store as well.
    }

}
