package org.example.webservice.web.api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.example.webservice.SqlExecutor;
import org.example.webservice.dto.BandDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public class BandControllerTest {

    @Value("${local.server.port}")
    private int localServerPort;

    @Autowired
    SqlExecutor executor;

    @Before
    public void setup() throws Exception {
        RestAssured.port = localServerPort;
        RestAssured.basePath = "/api";
        executor.exec("schema.sql");
    }

    @Test
    public void getBandForSpecifiedAlbum() throws Exception {
        //------given
        executor.exec("data/data-test.sql");
        Long albumId = 1L;
        //------when
        Response response = get("/albums/" + albumId + "/band");
        //------then
        BandDTO band = response.body().as(BandDTO.class);
        Assert.assertEquals(response.getStatusCode(), OK.value());
        Assert.assertEquals("band1", band.getName());
    }

    @Test
    public void getBandForSpecifiedAlbumWhenGivenWrongAlbumId() throws Exception{
        //------given
        executor.exec("data/data-test.sql");
        Long albumId = Long.MAX_VALUE;
        //------when
        Response response = get("/albums/" + albumId + "/band");
        //------then
        Assert.assertEquals(NOT_FOUND.value(), response.getStatusCode());
    }
//
//    @Test
//    public void
}
