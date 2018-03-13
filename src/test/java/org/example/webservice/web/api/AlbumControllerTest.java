package org.example.webservice.web.api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.example.webservice.SqlExecutor;
import org.example.webservice.dto.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static com.jayway.restassured.RestAssured.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
/* webEnvironment = RANDOM_PORT: Creates an EmbeddedWebApplicationContext and
sets a server.port=0 Environment property (which usually triggers listening on a random port).
Often used in conjunction with a LocalServerPort injected field on the test. */
@ActiveProfiles("test")
public class AlbumControllerTest {

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
    public void getAllAlbumsWhenDatabaseIsEmpty() {
        //------given : empty database
        //------when
        Response response = get("/albums");
        //------then
        Assert.assertEquals(NO_CONTENT.value(), response.getStatusCode());
    }

    @Test
    public void getAllAlbumsOrderedById() throws Exception {
        //------given
        executor.exec("data/data-test.sql");
        Response response = get("/albums/");
        AlbumDTO albums[] = response.as(AlbumDTO[].class);
        //------when
        Assert.assertEquals(OK.value(), response.getStatusCode());

        //------then
        Long valueToCompare = 1L;
        for (AlbumDTO album : albums) {
            Assert.assertNotNull(album.getId());
            Assert.assertEquals(valueToCompare++, album.getId());
            Assert.assertNotNull(album.getTitle());
            Assert.assertNotNull(album.getYearOfRelease());
            Assert.assertNotNull(album.getArtists());
            Assert.assertNotNull(album.getGenres());
            Assert.assertNotNull(album.getTracks());
        }
    }

    @Test
    public void getAlbumsMatchedByArtistParameterWhenGivenArtistExistInDatabase() throws Exception {
        //------given
        executor.exec("data/data-test.sql");
        //------when
        for (int artistNumb = 1; artistNumb <= 8; artistNumb++) {
            Response response = get("/albums?artist=artist" + artistNumb);
            //------then
            Assert.assertEquals(OK.value(), response.getStatusCode());
            Assert.assertTrue(response.asString().contains("artist" + artistNumb));
        }
    }

    @Test
    public void getAlbumsMatchedByBandParameterWhenGivenBandExistInDatabase() throws Exception {
        //------given
        executor.exec("data/data-test.sql");
        //------when
        for (int bandNumb = 1; bandNumb <= 2; bandNumb++) {
            Response response = get("/albums?band=band" + bandNumb);
            //------then
            Assert.assertEquals(OK.value(), response.getStatusCode());
            Assert.assertTrue(response.asString().contains("band" + bandNumb));
        }
    }

    @Test
    public void getAlbumsMatchedByGenreParameterWhenGivenGenreExistInDatabase() throws Exception {
        //------given
        executor.exec("data/data-test.sql");
        //------when
        for (int genreNumb = 1; genreNumb <= 4; genreNumb++) {
            Response response = get("/albums?genre=genre");
            //------then
            Assert.assertEquals(OK.value(), response.getStatusCode());
            Assert.assertTrue(response.asString().contains("genre" + genreNumb));
        }
    }

    @Test
    public void getAlbumsBetweenSpecifiedYearsOfReleaseWhenAlbumExistInDatabase() throws Exception {
        //------given
        executor.exec("data/data-test.sql");
        //------when
        Response response = get("/albums?fromYear=1999&toYear=2005");
        //------then
        Assert.assertEquals(OK.value(), response.getStatusCode());
        Assert.assertTrue(response.asString().contains("test1"));
        Assert.assertFalse(response.asString().contains("test2"));
    }

    @Test
    public void getAlbumsBetweenSpecifiedYearsOfReleaseWhenStringInsteadOfNumberInParam() throws Exception {
        //------given
        executor.exec("data/data-test.sql");
        //------when
        Response response = get("/albums?fromYear=some&toYear=text");
        //------then
        Assert.assertEquals(NOT_ACCEPTABLE.value(), response.getStatusCode());
    }

    @Test
    public void postAlbumWhenAllDataValid() {
        //------given
        Set<TrackDTO> givenTracks = new HashSet<>();
        givenTracks.add(new TrackDTO().setName("track1t")
                .setDuration(123L)
                .setTrackNumber(1L));
        givenTracks.add(new TrackDTO().setName("track2t")
                .setDuration(123L)
                .setTrackNumber(2L));

        Set<ArtistDTO> givenArtists = new HashSet<>();
        givenArtists.add(new ArtistDTO().setName("artist1"));
        givenArtists.add(new ArtistDTO().setName("artist1"));

        Set<GenreDTO> givenGenres = new HashSet<>();
        givenGenres.add(new GenreDTO().setName("genre1"));
        givenGenres.add(new GenreDTO().setName("genre2"));



        AlbumDTO givenAlbum = new AlbumDTO()
                .setTitle("test3")
                .setYearOfRelease("2000")
                .setTracks(givenTracks)
                .setArtists(givenArtists)
                .setGenres(givenGenres)
                .setBand(new BandDTO().setName("band1"));
        //------when
        Response response = given()
                .when().contentType(ContentType.JSON)
                .body(givenAlbum)
                .post("/albums");

        //------then
        Assert.assertEquals(CREATED.value(), response.getStatusCode());
        Assert.assertNotNull(response.getBody().as(AlbumDTO.class).getId());
        Assert.assertEquals(2, response.getBody().as(AlbumDTO.class).getTracks().size());
    }

    @Test
    public void postAlbumWithoutTracks() {
        //------given
        AlbumDTO givenAlbum = new AlbumDTO()
                .setTitle("test3")
                .setYearOfRelease("2000");
        //------when
        Response response = given()
                .when().contentType(ContentType.JSON)
                .body(givenAlbum)
                .post("/albums");

        //------then
        Assert.assertEquals(CREATED.value(), (response.getStatusCode()));
        Assert.assertNotNull(response.getBody().as(AlbumDTO.class).getId());
    }


    @Test
    public void postAlbumWhenIdIsSetShouldNotCreateNewAlbum() {
        //------given
        Set<TrackDTO> givenTracks = new HashSet<>();
        AlbumDTO givenAlbum = new AlbumDTO()
                .setId(1L)
                .setTitle("test3")
                .setYearOfRelease("2000")
                .setTracks(givenTracks);
        //------when
        Response response = given()
                .when().contentType(ContentType.JSON)
                .body(givenAlbum)
                .post("/albums");

        //------then
        Assert.assertEquals(BAD_REQUEST.value(), response.getStatusCode());
        Assert.assertEquals(2, response.getBody().asString().length());

    }

    @Test
    public void postAlbumWithEmptyTracks() {
        //------given
        Set<TrackDTO> givenTracks = new HashSet<>();
        givenTracks.add(new TrackDTO());
        givenTracks.add(new TrackDTO());
        givenTracks.add(new TrackDTO());
        AlbumDTO givenAlbum = new AlbumDTO()
                .setTracks(givenTracks)
                .setTitle("test3")
                .setYearOfRelease("2000");
        //------when
        Response response = given()
                .when().contentType(ContentType.JSON)
                .body(givenAlbum)
                .post("/albums");
        //------then
        Assert.assertEquals(BAD_REQUEST.value(), response.getStatusCode());
        Assert.assertEquals(2, response.getBody().asString().length());
    }

    @Test
    public void postEmptyAlbum() {
        //------given
        AlbumDTO givenAlbum = new AlbumDTO();
        //------when
        Response response = given()
                .when().contentType(ContentType.JSON)
                .body(givenAlbum)
                .post("/albums");

        //------then
        Assert.assertEquals(BAD_REQUEST.value(), response.getStatusCode());
        Assert.assertEquals(2, response.getBody().asString().length());
    }

    @Test
    public void getAlbumByIdWhenIdInDatabase() throws Exception {
        //------given
        executor.exec("data/data-test.sql");
        Long albumId = 1L;
        //------when
        Response response = given()
                .when().contentType(ContentType.JSON)
                .get("/albums/" + albumId);
        //------then
        AlbumDTO respAlbum = response.getBody().as(AlbumDTO.class);
        Assert.assertEquals(OK.value(), response.getStatusCode());
        Assert.assertEquals(albumId, respAlbum.getId());
    }

    @Test
    public void getAlbumByIdWhenIdNotInDatabase() throws Exception {
        //------given
        executor.exec("data/data-test.sql");
        Long albumId = 999L;
        //------when
        Response response = given()
                .when().contentType(ContentType.JSON)
                .get("/albums/" + albumId);
        //------then
        Assert.assertEquals(NOT_FOUND.value(), response.getStatusCode());
        Assert.assertNull(response.getBody());
    }

    @Test
    public void getAlbumByIdWhenIdIsANegativeNumber() throws Exception {
        //------given
        executor.exec("data/data-test.sql");
        Long albumId = -1L;
        //------when
        Response response = given()
                .when().contentType(ContentType.JSON)
                .get("/albums/" + albumId);
        //------then
        Assert.assertEquals(NOT_FOUND.value(), response.getStatusCode());
        Assert.assertNull(response.getBody());
    }

    @Test
    public void putAlbumWhenAllDataValid() throws Exception {
        //------given
        executor.exec("data/data-test.sql");
        Long albumId = 1L;
        AlbumDTO albumDTO = new AlbumDTO()
                .setId(albumId)
                .setTitle("newTestTitle")
                .setYearOfRelease("1991");
        //------when
        Response response = given()
                .body(albumDTO)
                .when().contentType(ContentType.JSON)
                .put("/albums/" + albumId);
        //------then
        Assert.assertEquals(response.getStatusCode(), CREATED.value());
        AlbumDTO updatedDTO = response.getBody().as(AlbumDTO.class);
        Assert.assertEquals(albumId, updatedDTO.getId());
        Assert.assertEquals(updatedDTO.getTitle(), albumDTO.getTitle());
        Assert.assertEquals(updatedDTO.getYearOfRelease(), albumDTO.getYearOfRelease());
    }

    @Test
    public void putAlbumWhenIdInBodyNotEqualsIdInPath() throws Exception {
        //------given
        executor.exec("data/data-test.sql");
        Long pathId = 2L;
        Long bodyId = 1L;
        AlbumDTO albumDTO = new AlbumDTO()
                .setId(bodyId)
                .setTitle("newTestTitle")
                .setYearOfRelease("1991");
        //------when
        Response response = given()
                .body(albumDTO)
                .when().contentType(ContentType.JSON)
                .put("/albums/" + pathId);
        //------then
        Assert.assertEquals(response.getStatusCode(), BAD_REQUEST.value());
    }

    @Test
    public void putAlbumWhenIdInBodyNull() throws Exception {
        //------given
        executor.exec("data/data-test.sql");
        Long pathId = 2L;
        Long bodyId = null;
        AlbumDTO albumDTO = new AlbumDTO()
                .setId(bodyId)
                .setTitle("newTestTitle")
                .setYearOfRelease("1991");
        //------when
        Response response = given()
                .body(albumDTO)
                .when().contentType(ContentType.JSON)
                .put("/albums/" + pathId);
        //------then
        Assert.assertEquals(response.getStatusCode(), BAD_REQUEST.value());
    }

    @Test
    public void putAlbumWhenEmptyBody() throws Exception {
        //------given
        executor.exec("data/data-test.sql");
        Long pathId = 2L;
        //------when
        Response response = given()
                .when().contentType(ContentType.JSON)
                .put("/albums/" + pathId);
        //------then
        Assert.assertEquals(response.getStatusCode(), BAD_REQUEST.value());
    }

    @Test
    public void deleteWhenAlbumExistId() throws Exception {
        executor.exec("data/data-test.sql");
        Long pathId = 1L;
        //------when
        Response response = delete("/albums/" + pathId);
        //------then
        Assert.assertEquals(response.getStatusCode(), NO_CONTENT.value());
    }


    @Test
    public void deleteWhenNonExistingAlbumId() throws Exception {
        executor.exec("data/data-test.sql");
        Long pathId = 999L;
        //------when
        Response response = delete("/albums/" + pathId);
        //------then
        Assert.assertEquals(response.getStatusCode(), NOT_FOUND.value());
    }
}