package assignment;

import Utility.Helper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
    public static final String URL = "https://deckofcardsapi.com/api";
    public static final String DECK_END_POINT = "/deck/new";
    public static boolean jokers = true;
    public static final int SUCCESS_CODE = 200;
    public static final int CODE_301 = 301;

    public static String deck_id;
    public static Response createDeckApiResponse;
    public static int count = 6;
    public static Helper helper = new Helper();

    static {
        RestAssured.baseURI = URL;
    }


    public Response createDeckCall() {
        RequestSpecification request = RestAssured.given();
        request.header("jokers_enabled", jokers);
        Response makeDeckResponse = request.get(DECK_END_POINT);
        return makeDeckResponse;
    }

    public Response createDeckPostCall() {
        RequestSpecification request = RestAssured.given();
        request.header("jokers_enabled", jokers);
        Response makeDeckResponse = request.post(DECK_END_POINT);
        return makeDeckResponse;
    }









}
