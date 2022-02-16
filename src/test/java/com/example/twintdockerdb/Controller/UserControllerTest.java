package com.example.twintdockerdb.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

class UserControllerTest {

    @Disabled
    @BeforeAll
    public static void startServer() throws IOException {

        ProcessBuilder pb = new ProcessBuilder( "C:/Program Files/Common Files/Oracle/Java/javapath/java.exe", "-jar", "TwintDockerDb-0.0.1-SNAPSHOT.jar");
        pb.directory(new File("C:/Users/krzys/IdeaProjects/TwintDockerDb/target/"));
        Process p = pb.start();


    }

    @Disabled
    @Test
    public void givenServerRunningReturnHttp_200()
            throws IOException {

        //given
        URL url = new URL("http://localhost:8087/testEndpoint");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Assertions.assertEquals(200, connection.getResponseCode());
    }

    @Disabled
    @Test
    public void givenServerRunningReturnOne(){


    }


}