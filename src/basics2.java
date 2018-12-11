import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class basics2 {

    @Test
    public void post(){

        RestAssured.baseURI = "https://food2fork.com";

     Response res = given().
                param("key","e69f7e431a76291c119787efbe106e6d").
                param("q","pesto cream").
                header("Content-type","application/json").
                header("Accept","application/json").
                when().
                get("/api/search").
                then().
                assertThat().statusCode(200).contentType(ContentType.HTML).extract().response();

     String output = res.asString();
     System.out.println(output);
     JsonPath js = new JsonPath(output);
     String count = (js.get("count")).toString();
     String recipe_id = (js.get("recipes[0].recipe_id")).toString();
     System.out.println("number of results =" + (count));
     System.out.println("first recipe id =" + (recipe_id));

    }
}
