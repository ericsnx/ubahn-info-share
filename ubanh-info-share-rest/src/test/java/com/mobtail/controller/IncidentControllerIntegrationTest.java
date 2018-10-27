package com.mobtail.controller;

import com.mobtail.UbanhInfoShareApplicationTest;
import com.mobtail.request.IncidentRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = UbanhInfoShareApplicationTest.class)
@ActiveProfiles("test")
@Transactional
public class IncidentControllerIntegrationTest {

    @Autowired
    private IncidentController incidentController;

    @Test
    @DisplayName("Testing incidents list endpoint")
    public void listIncidentsWithSuccess() {
        given().standaloneSetup(incidentController)
                .contentType(ContentType.JSON)
                .when()
                .get("/ubanh-status-share/incidents")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Save new incident test")
    public void saveIncidentsWithSuccess() {

        final IncidentRequest incidentRequest = IncidentRequest.builder().user("Eric").line("U6").build();
        System.out.println(incidentRequest);

        given().standaloneSetup(incidentController)
                .body(incidentRequest)
                .contentType(ContentType.JSON)
                .when()
                .post("/ubanh-status-share/incidents")
                .then()
                .assertThat()
                .statusCode(201);
    }

}