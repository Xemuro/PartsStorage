package pl.dreem.query.domain.part.api.controller;

import io.restassured.path.json.JsonPath;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import pl.dreem.util.PostgreSQLInitializer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"dev", "test"})
public class MakeQueryControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgreSQLInitializer.getInstance();

    @Test
    public void getModelsByMakeShouldReturnFiatPart() {

        final String expectedModelId = "38e0b4ab-d68a-416c-a69f-2a289571bd18";
        final String expectedPartId = "0b3428ee-df8f-4664-8099-9a6d9ba763d1";

        final JsonPath jsonPath = given().port(port)
                                         .get("/query/v1/make?make=Fiat")
                                         .then()
                                         .statusCode(200)
                                         .body("make", is("Fiat"))
                                         .extract()
                                         .jsonPath();

        final String actualModelId = jsonPath.getString("models[0].modelId");
        final String actualPartId = jsonPath.getString("models[0].parts[0].partId");
        assertThat(actualModelId, is(equalTo(expectedModelId)));
        assertThat(actualPartId, is(equalTo(expectedPartId)));
    }

    @Test
    public void getModelsByMakeShouldReturn3ItemsForBMW() {
        given().port(port)
               .get("/query/v1/make?make=BMW")
               .then()
               .statusCode(200)
               .body("make", is("BMW"))
               .body("models.size()", is(3));
    }

    @Test
    public void getModelsByMakeShouldReturnEmptyResultForNotFoundMake() {
        given().port(port)
               .get("/query/v1/make?make=NotFoundMake")
               .then()
               .statusCode(200)
               .body("make", is("NotFoundMake"))
               .body("models.size()", is(0));
    }

    @Test
    public void getPartsForMakeWithoutFilterShouldReturnAllModels() {
        given().port(port)
               .get("/query/v1/make/parts")
               .then()
               .statusCode(200)
               .body("size()", is(6));
    }

    @Test
    public void getPartsForMakeWithNameFilterShouldReturnPartsFiltered() {
        given().port(port)
               .get("/query/v1/make/parts?part=21")
               .then()
               .statusCode(200)
               .body("[0].parts[0].name", containsString("21"));
    }

    @Test
    public void getPartsForMakeWithDescriptionFilterShouldReturnPartsFiltered() {
        given().port(port)
               .get("/query/v1/make/parts?description=21")
               .then()
               .statusCode(200)
               .body("[0].parts[0].description", containsString("21"));
    }

    @Test
    public void getPartsForMakeWithFilterShouldReturnPartsFiltered() {
        given().port(port)
               .get("/query/v1/make/parts?part=21&description=21")
               .then()
               .statusCode(200)
               .body("[0].parts[0].description", containsString("21"));
    }
}