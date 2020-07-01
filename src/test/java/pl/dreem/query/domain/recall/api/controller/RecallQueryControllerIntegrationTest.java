package pl.dreem.query.domain.recall.api.controller;

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
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"dev", "test"})
public class RecallQueryControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgreSQLInitializer.getInstance();

    @Test
    public void getRecallActionShouldReturnOneResultForRange() {
        given().port(port)
               .get("/query/v1/recall?from=2020-01-10&to=2020-01-20")
               .then()
               .statusCode(200)
               .body("size()", is(1));
    }

    @Test
    public void getRecallActionShouldReturnTwoResultForRange() {
        given().port(port)
               .get("/query/v1/recall?from=2020-01-01&to=2020-01-20")
               .then()
               .statusCode(200)
               .body("size()", is(2));
    }

    @Test
    public void getRecallActionShouldReturnZeroResultForRange() {
        given().port(port)
               .get("/query/v1/recall?from=2019-01-01&to=2019-01-20")
               .then()
               .statusCode(200)
               .body("size()", is(0));
    }

    @Test
    public void getRecallActionShouldReturnOneResultForRangeBetween() {
        given().port(port)
               .get("/query/v1/recall?from=2019-01-15&to=2019-01-25")
               .then()
               .statusCode(200)
               .body("size()", is(0));
    }
}
