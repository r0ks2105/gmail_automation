package RestAssured;

import io.restassured.RestAssured;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

public class RestAssuredInit {
    private final String BaseURL = "https://api.trello.com/1";
    private final String BoardId = ReadProperties.getInstance().getPropertyValue("BoardId");
    private final String trelloKey = ReadProperties.getInstance().getPropertyValue("trelloKey");
    private final String trelloToken = ReadProperties.getInstance().getPropertyValue("trelloToken");
    private final String CardId = ReadProperties.getInstance().getPropertyValue("cardId");
    private final String ID = "6064524ad441f83d1d8e695a";

    public RestAssuredInit() throws IOException {
    }

    @BeforeClass
    public void setBaseURL() {
        RestAssured.baseURI = BaseURL;
    }


    @Test
    public void getListsOfIDs() {
        List<String> namesList = new ArrayList<>();
        namesList.add("To Do");
        namesList.add("Doing");
        namesList.add("Done");
        RestAssured.given()
                .when()
                .get("/boards/"+BoardId+"/lists?filter=open&fields=all&key="+trelloKey+"&token="+trelloToken)
                .then()
                        .body("name", equalTo(namesList))
                .statusCode(200)
                .extract().response().getBody().prettyPrint();
    }
    @Test
    public void createNewBoard() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("name","NewTestBoard2");
        reqBody.put("defaultLists","true");
                RestAssured.given().contentType("application/json")
                        .body(reqBody)
                .when()
                .post("/boards/?key="+trelloKey+"&token="+trelloToken)
                .then()
                        .body("name", equalTo("NewTestBoard2"))
                .statusCode(200)
                .extract().response().getBody().prettyPrint();
    }
    @Test
    public void updateCard() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("name","New card NAME2");
        reqBody.put("desc","My Test description");
        reqBody.put("address","UA, L'viv ");
        RestAssured.given().contentType("application/json")
                .body(reqBody)
                .when()
                .put("/cards/"+CardId+"?key="+trelloKey+"&token="+trelloToken)
                .then()
                .body("name", equalTo("New card NAME2"))
                .statusCode(200)
                .extract().response().getBody().prettyPrint();
    }
    @Test
    public void deleteBoard() {

        RestAssured.given().contentType("application/json")
                .when()
                .delete("/boards/"+ID+"?key="+trelloKey+"&token="+trelloToken)
                .then()
                .statusCode(200)
                .extract().response().getBody().prettyPrint();
    }

}
