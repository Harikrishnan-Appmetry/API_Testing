package com.Apitest;
import utility.TokenProvider;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

import java.util.Map;

public class ConfigTest {
    ResponseSpecification responseSpecification;

    @BeforeClass
    public static void beforeClass(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://nhimumobll.execute-api.us-west-2.amazonaws.com/development").
                addHeader("Authorization","bearer" + TokenProvider.getToken()).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    public Response getRoomEvent(){
        Header header1 = new Header("page","0");
        Header header2 = new Header("limit","50");
        Header header3 = new Header("classType","private");
        Headers multipleHeaders = new Headers(header1,header2,header3);

        return RestAssured.given().
                headers(multipleHeaders).
        when().
                 get("room-events").
        then().spec(responseSpecification).
                extract().response();
    }

    public Response updateRoomName(String id, Map <String,String> requestBody){
        return (Response) RestAssured.given().
                body(requestBody).
        when().
                put("/room-events/" + id).
        then().spec(responseSpecification).
                extract().response();
    }
}
