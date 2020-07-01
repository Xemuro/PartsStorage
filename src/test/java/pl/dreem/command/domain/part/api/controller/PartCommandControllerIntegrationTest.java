package pl.dreem.command.domain.part.api.controller;

import io.restassured.http.ContentType;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import pl.dreem.command.domain.part.api.dto.NewDescriptionRequest;
import pl.dreem.command.domain.part.repository.PartCommandRepository;
import pl.dreem.global.entity.PartEntity;
import pl.dreem.util.PostgreSQLInitializer;

import static io.restassured.RestAssured.given;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"dev", "test"})
public class PartCommandControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgreSQLInitializer.getInstance();

    @Autowired
    private PartCommandRepository commandRepository;

    @Test
    public void changePartDescriptionShouldChangeDescriptionForExistId() {
        final PartEntity part = commandRepository.findById("64500eed-ea83-4e39-b878-25d84710d46e")
                                                 .orElseThrow(() -> new RuntimeException("Missing partId"));

        final String expectedDescription = "newDescription";

        final NewDescriptionRequest request = NewDescriptionRequest.from(expectedDescription);

        given().port(port)
               .with()
               .contentType(ContentType.JSON)
               .body(request)
               .put("/command/v1/part/" + part.getId() + "/description")
               .then()
               .statusCode(200);

        final PartEntity updatedPart = commandRepository.findById(part.getId())
                                                        .orElseThrow(() -> new RuntimeException("Missing partId"));

        final String actualDescription = updatedPart.getDescription();

        assertThat(actualDescription, is(equalTo(expectedDescription)));
    }

    @Test
    public void changePartDescriptionForNotExistPartShouldReturn200() {
        final String expectedPartId = "16ee67d6-f9f5-42eb-9ab6-59efdd6e1b4";


        final String expectedDescription = "newDescription";
        final NewDescriptionRequest request = NewDescriptionRequest.from(expectedDescription);

        given().port(port)
               .with()
               .contentType(ContentType.JSON)
               .body(request)
               .put("/command/v1/part/" + expectedPartId + "/description")
               .then()
               .statusCode(200);

        final boolean isExist = commandRepository.existsById(expectedPartId);
        assertThat(isExist, is(false));
    }

    @Transactional
    @Test
    public void deleteSalesArgumentsShouldReturn204ForSuccess() {
        final String partId = "18da03a2-bd46-4317-bfda-f58402b3dcc1";
        final PartEntity part = commandRepository.findById(partId)
                                                 .orElseThrow(() -> new RuntimeException("Missing part for test"));

        final int salesArgumentSizeBeforeDelete = part.getSalesArguments().size();

        given().port(port)
               .with()
               .delete("/command/v1/part/" + partId + "/sales")
               .then()
               .statusCode(204);

        assertThat(salesArgumentSizeBeforeDelete, is(3));
        await().until(() -> commandRepository.findById(partId).get().getSalesArguments().size(), is(0));
    }
}
