import files.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Basics3 {
        //xml/json from Resource class to string
        public static String GenerateStringFromResource(String path) throws IOException{
            return new String(Files.readAllBytes(Paths.get(path)));
        }

        Properties prop = new Properties();

        @BeforeTest
        public void getData(){
            try{
                FileInputStream fis = new FileInputStream("src/files/env.properties");
                prop.load(fis);
            }
            catch(FileNotFoundException e){
                System.out.println("File not found");
            }
            catch(IOException e) {
                System.out.println("IO Exception");
            }

        }

        @Test
        public void Test(){

            RestAssured.baseURI = prop.getProperty("HOST");
            Response res = given().log().all().
                    queryParam("key",prop.getProperty("KEY")).
                    queryParam("q","pasta").
                    header("Content-type","application/json").
                    header("Accept","application/json").
                    when().log().all().
                    get(Resources.getGetResource()).
                    then().log().all().
                    assertThat().statusCode(200).contentType(ContentType.HTML).extract().response();

            String output = res.asString();
            System.out.println(output);
        }
}
