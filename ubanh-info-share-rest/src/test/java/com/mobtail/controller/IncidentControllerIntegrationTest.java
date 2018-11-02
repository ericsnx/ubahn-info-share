package com.mobtail.controller;

import com.mobtail.UbanhInfoShareApplicationTest;
import com.mobtail.entity.Incident;
import com.mobtail.request.CreateIncidentRequest;
import com.mobtail.request.UpdateIncidentRequest;
import com.mobtail.response.IncidentResponse;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.response.ResponseBody;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;

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

        final CreateIncidentRequest incidentRequest = CreateIncidentRequest.builder().user("Eric").line("U6").build();
        incidentController.save(incidentRequest);

        final MockMvcResponse response = given().standaloneSetup(incidentController).contentType(ContentType.JSON).get("/ubanh-status-share/incidents");
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(response.getBody().jsonPath().getString("user")).isEqualToIgnoringCase("[Eric]");
        Assertions.assertThat(response.getBody().jsonPath().getString("line")).isEqualToIgnoringCase("[U6]");
    }

    @Test
    @DisplayName("Testing the find by incident id endpoint")
    public void findByIncidentIdWithSuccess() {

        final CreateIncidentRequest incidentRequest = CreateIncidentRequest.builder().user("Eric").line("U6").build();
        incidentController.save(incidentRequest);
        final UUID id = incidentController.list().stream().findFirst().map(IncidentResponse::getId).orElse(null);

        final MockMvcResponse response = given().standaloneSetup(incidentController).contentType(ContentType.JSON).get("/ubanh-status-share/incidents/" + id);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(response.getBody().jsonPath().getString("user")).isEqualToIgnoringCase("[Eric]");
        Assertions.assertThat(response.getBody().jsonPath().getString("line")).isEqualToIgnoringCase("[U6]");
    }

    @Test
    @DisplayName("Save new incident test")
    public void saveIncidentsWithSuccess() {

        final CreateIncidentRequest incidentRequest = CreateIncidentRequest.builder().user("Eric").line("U6").build();

        given().standaloneSetup(incidentController)
                .body(incidentRequest)
                .contentType(ContentType.JSON)
                .when()
                .post("/ubanh-status-share/incidents")
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    @DisplayName("Save new incident fails given missing required data test")
    public void saveIncidentsInvalidPayloadFails() {

        final CreateIncidentRequest incidentRequest = CreateIncidentRequest.builder().line("U6").build();


        final MockMvcResponse response = given().standaloneSetup(incidentController)
                .body(incidentRequest)
                .contentType(ContentType.JSON)
                .when()
                .post("/ubanh-status-share/incidents")
                .thenReturn();

        assertThat(response.getStatusCode()).isEqualTo(400);

    }

    @Test
    @DisplayName("Update incident test")
    public void updateIncidentsInvalidPayloadFails() {

        //Persist incident
        final CreateIncidentRequest incidentRequest = CreateIncidentRequest.builder().user("Eric").line("U6").build();
        incidentController.save(incidentRequest);

        //Fetch incidentId
        final UUID id = incidentController.list().stream().map(IncidentResponse::getId).findFirst().orElse(null);//replace by findLatest
        final UpdateIncidentRequest updateIncidentRequest = UpdateIncidentRequest.builder().id(id).line("U2").user("Bruno").build();

        final MockMvcResponse response = given().standaloneSetup(incidentController)
                .body(updateIncidentRequest)
                .contentType(ContentType.JSON)
                .when()
                .put("/ubanh-status-share/incidents")
                .thenReturn();

        assertThat(response.getStatusCode()).isEqualTo(200);


    }

}