package com.Apitest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class renameAssessment {
    ConfigTest configTest = new ConfigTest();

    @Test
    public void getListRoomEvents() throws IOException {
        Response response = configTest.getRoomEvent();

        FileWriter fileWriter = new FileWriter(new File("output.json"));
        fileWriter.write(response.asString());
        fileWriter.close();
    }

    @Test
    public void updateRoomEvent(){

    }
}
