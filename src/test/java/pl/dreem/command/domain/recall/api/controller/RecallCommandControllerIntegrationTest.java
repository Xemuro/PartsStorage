package pl.dreem.command.domain.recall.api.controller;

import io.restassured.http.ContentType;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import pl.dreem.command.domain.recall.api.request.NewRecallRequest;
import pl.dreem.command.domain.recall.repository.RecallCommandRepository;
import pl.dreem.global.DateRange;
import pl.dreem.global.identity.PartId;
import pl.dreem.util.PostgreSQLInitializer;

import java.time.LocalDate;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"dev", "test"})
public class RecallCommandControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgreSQLInitializer.getInstance();

    @Autowired
    private RecallCommandRepository repository;

    @Test
    public void createNewRecallForExistPartShouldFinishWithSuccess() {

        final DateRange durationOfRecall = DateRange.from(LocalDate.of(2015, 01, 01), LocalDate.of(2015, 02, 01));
        final NewRecallRequest request = NewRecallRequest.from("name", durationOfRecall, PartId.from(
                UUID.fromString("9cfb7277-2a7c-4558-ba3e-cb37651fab00")));

        final String recallId = given().port(port)
                                       .with()
                                       .contentType(ContentType.JSON)
                                       .body(request)
                                       .post("/command/v1/recall")
                                       .then()
                                       .statusCode(200)
                                       .extract()
                                       .jsonPath()
                                       .getString("recallId");

        repository.findById(recallId).orElseThrow(() -> new RuntimeException("Failed test - missing recallId after create"));
    }

    @Test
    public void createNewRecallForNotExistPartShouldFinishWith404() {

        final DateRange durationOfRecall = DateRange.from(LocalDate.of(2015, 01, 01), LocalDate.of(2015, 02, 01));
        final NewRecallRequest request = NewRecallRequest.from("name", durationOfRecall, PartId.from(
                UUID.fromString("d84270d9-c79f-42ee-a123-37e2800da074")));

        given().port(port)
               .with()
               .contentType(ContentType.JSON)
               .body(request)
               .post("/command/v1/recall")
               .then()
               .statusCode(404);
    }
}