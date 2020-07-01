package pl.dreem.query.domain.part.api.controller;

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
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"dev", "test"})
public class PartQueryControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgreSQLInitializer.getInstance();

    @Test
    public void getPartAvailabilityShouldReturnResultForIdNotAvailable(){
        given().port(port)
               .get("/query/v1/part/0b3428ee-df8f-4664-8099-9a6d9ba763d1/availability")
               .then()
               .statusCode(200)
               .body("partId", equalTo("0b3428ee-df8f-4664-8099-9a6d9ba763d1"))
               .body("available", is(false))
               .body("numberOfDaysToShip", is(0));
    }

    @Test
    public void getPartAvailabilityShouldReturnResultForIdAvailable(){
        given().port(port)
               .get("/query/v1/part/a6cabe86-cf1f-40b7-ba60-edd1646414a7/availability")
               .then()
               .statusCode(200)
               .body("partId", equalTo("a6cabe86-cf1f-40b7-ba60-edd1646414a7"))
               .body("available", is(true))
               .body("numberOfDaysToShip", is(10));
    }

    @Test
    public void getPartAvailabilityForNonExistPartIdShouldReturn404(){
        given().port(port)
               .get("/query/v1/part/77efce97-68f5-4795-a66e-952deda026e8/availability")
               .then()
               .statusCode(404);
    }
}