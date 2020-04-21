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
    public static String deckID;
    public static final String URL = "https://deckofcardsapi.com/api";



    @Test
    public void runTest(){
        createDeckOfCards();
        drawCard();
    }


    public static void createDeckOfCards() {
        String endPointCreateDeck = "/deck/new";
        RestAssured.baseURI = URL;
        RequestSpecification request = RestAssured.given();
        request.header("jokers_enabled", true);
        Response response = request.get(endPointCreateDeck);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Status Code Failed!");
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("deck_id"), "Could Not Find Deck of Cards!");
        String[] array = responseBody.replaceAll("[{}:,\"]","").split(" ");
        deckID = array[3];
        System.out.println("Created Deck ID = " + deckID);
    }

    public static void drawCard() {
        String endPointDrawCard = "/deck/" + deckID + "/draw/?count=2";
        RestAssured.baseURI = URL;
        RequestSpecification request = RestAssured.given();
        Response response = request.get(endPointDrawCard);
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

        //Instead of this we can create POJO class to store as well.
    }

}
