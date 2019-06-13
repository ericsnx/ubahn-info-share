package com.mobtail.service;

import com.mobtail.DateUtil;
import com.mobtail.repository.IncidentRepository;
import com.mobtail.request.CreateIncidentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;


public class IncidentServiceTest {

    private IncidentRepository incidentRepository = Mockito.mock(IncidentRepository.class);

    private DateUtil dateUtil;

    private IncidentService incidentService;

    @BeforeEach
    void setup() {

        final Clock fixed = Clock.fixed(
                Instant.parse("2018-08-22T10:00:00Z"),
                ZoneOffset.UTC);
        this.dateUtil = new IntegrationTestDateUtil(fixed);
        this.incidentService = new IncidentService(incidentRepository, dateUtil);
    }

    @Test
    public void saveWithSuccesTest() {
        //Testing service should be mostly about rules.
        final CreateIncidentRequest build = CreateIncidentRequest.builder().user("Eric").line("U9").build();
        incidentService.save(build);
    }

    public class IntegrationTestDateUtil implements DateUtil {

        @Autowired
        private Clock clock;

        @Autowired
        public IntegrationTestDateUtil(Clock clock){
            this.clock = clock;
        }

        @Override
        public Instant instantNow() {
            return Instant.now(clock);
        }
    }
}