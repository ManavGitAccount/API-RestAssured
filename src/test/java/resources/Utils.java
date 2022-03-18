package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification reqSpec;
    JsonPath js;

    public RequestSpecification requestSpecification() throws IOException {

        if(reqSpec==null){
            PrintStream logData = new PrintStream(new FileOutputStream("logging.txt"));
            reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).
                    addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(logData))
                    .addFilter(ResponseLoggingFilter.logResponseTo(logData))
                    .setContentType(ContentType.JSON).build();
            return reqSpec;
        }

        return reqSpec;

    }

    public static String getGlobalValue(String key) throws IOException {
        Properties propFile = new Properties();
        FileInputStream fileInpSt = new FileInputStream("C:\\Users\\manav\\UltimateRestAssuredProject\\src\\test\\java\\resources\\global.properties");
        propFile.load(fileInpSt);
        return propFile.getProperty(key).toString();
    }

    public String getJsonPath(Response responseObj, String key){
        String respStr = responseObj.asString();
        js = new JsonPath(respStr);
        return js.get(key).toString();
    }
}
