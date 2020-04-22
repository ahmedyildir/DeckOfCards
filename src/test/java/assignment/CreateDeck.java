package assignment;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.Assert;


public class CreateDeck extends BaseClass {

    @BeforeClass
    public void setUpTest(){
        createDeckApiResponse = createDeckCall();
        deck_id = helper.pickDeckId(createDeckCall());
    }

    @Test
    public void testSuccessCreateDeck() {
        int statusCode = createDeckApiResponse.getStatusCode();
        Assert.assertEquals(statusCode, SUCCESS_CODE, "Status Code Failed!");
        Assert.assertTrue(createDeckApiResponse.getBody().asString().contains("deck_id"), "Could Not Find Deck of Cards!");
    }

    @Test
    public void testPostCallFail() {
        Response response = createDeckPostCall();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SUCCESS_CODE);
    }


}
