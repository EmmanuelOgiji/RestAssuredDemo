import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class basics {

    @Test
    public static void Test(){

        RestAssured.baseURI = "https://food2fork.com";

        given().
                param("key","e69f7e431a76291c119787efbe106e6d").
                param("q","pasta").
                header("Content-type","application/json").
                header("Accept","application/json").
                when().
                get("/api/search").
                then().
                assertThat().statusCode(200).contentType(ContentType.HTML);

    }
}
